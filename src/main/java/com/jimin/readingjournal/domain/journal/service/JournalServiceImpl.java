package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.auth.service.AuthService;
import com.jimin.readingjournal.domain.journal.dto.BookDto;
import com.jimin.readingjournal.domain.journal.dto.BookImageDto;
import com.jimin.readingjournal.domain.journal.dto.JournalDto;
import com.jimin.readingjournal.domain.journal.dto.MemorablePhraseDto;
import com.jimin.readingjournal.domain.journal.mapper.JournalMapper;
import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
import com.jimin.readingjournal.domain.journal.request.JournalWriteReq;
import com.jimin.readingjournal.domain.journal.request.MemorablePhraseReq;
import com.jimin.readingjournal.domain.journal.response.JournalDetailRes;
import com.jimin.readingjournal.domain.journal.response.JournalListRes;
import com.jimin.readingjournal.domain.journal.utils.FileUtils;
import com.jimin.readingjournal.global.exception.custom.UnauthorizedUserException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    private final JournalMapper mapper;
    private final AuthService authService;
    private final FileUtils fileUtils;

    @Override
    @Transactional
    public void registerBook(BookRegisterReq bookRegisterReq, MultipartFile image, HttpSession session) {
        String bookImageUrl = fileUtils.saveFile(image);

        String userId = authService.getUserId(session);

        BookDto bookDto = BookDto.builder()
                .bookTitle(bookRegisterReq.getTitle())
                .bookAuthor(bookRegisterReq.getAuthor())
                .userId(userId)
                .build();

        mapper.insertBook(bookDto);

        BookImageDto bookImageDto = BookImageDto.builder()
                .bookImageUrl(bookImageUrl)
                .bookId(bookDto.getBookId())
                .userId(userId)
                .bookImageName(image.getOriginalFilename())
                .build();

        mapper.insertBookImage(bookImageDto);
    }

    @Override
    public List<JournalListRes> getJournalsByBookId(HttpSession session, Long bookId) {
        String loginedUserId = authService.getUserId(session);
        String requestedUserId = mapper.getUserIdByBookId(bookId);

        if (loginedUserId == null || !loginedUserId.equals(requestedUserId)) {
            throw new UnauthorizedUserException();
        }

        return mapper.getJournalsByBookId(bookId);
    }

    @Override
    @Transactional
    public void insertJournal(HttpSession session, JournalWriteReq req) {
        String userId = authService.getUserId(session);
        if (userId == null) {
            throw new UnauthorizedUserException();
        }

        JournalDto journalDto = new JournalDto();
        journalDto.setStartPage(req.getStartPage());
        journalDto.setEndPage(req.getEndPage());
        journalDto.setReview(req.getReview());
        journalDto.setUserId(userId);
        journalDto.setBookId(req.getBookId());

        mapper.insertJournal(journalDto);

        for (MemorablePhraseReq phraseReq : req.getPhraseList()) {
//            MemorablePhraseDto memorablePhraseDto = MemorablePhraseDto.builder()
//                    .phrase(phraseReq.getPhrase())
//                    .page(phraseReq.getPage())
//                    .journalId(journalDto.getJournalId())
//                    .build();

            MemorablePhraseDto memorablePhraseDto = new MemorablePhraseDto();
            memorablePhraseDto.setPhrase(phraseReq.getPhrase());
            memorablePhraseDto.setPage(phraseReq.getPage());
            memorablePhraseDto.setJournalId(journalDto.getJournalId());
            mapper.insertMemorablePhrase(memorablePhraseDto);
        }
    }

    @Override
    public JournalDetailRes getJournalDetail(HttpSession session, Long journalId) {
        String userId = authService.getUserId(session);
        String requestedUserId = mapper.getUserIdByJournalId(journalId);

        if (userId == null || !userId.equals(requestedUserId)) {
            throw new UnauthorizedUserException();
        }

        JournalDto journalDto = mapper.getJournalByJournalId(journalId);
        List<MemorablePhraseDto> memorablePhraseDto = mapper.getMemorablePhraseByJournalId(journalId);

        log.info("journal detail with journal id {}", journalDto.getJournalId());
        log.info("journal {}", journalDto);
        log.info("memorable phrase {}", memorablePhraseDto);

        return new JournalDetailRes(journalDto, memorablePhraseDto);
    }
}
