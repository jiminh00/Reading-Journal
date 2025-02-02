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
import com.jimin.readingjournal.global.exception.custom.DeletedJournalException;
import com.jimin.readingjournal.global.exception.custom.UnauthorizedUserException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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
    public Long insertJournalAndGetJournalId(HttpSession session, JournalWriteReq req) {
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
            MemorablePhraseDto memorablePhraseDto = new MemorablePhraseDto();
            memorablePhraseDto.setPhrase(phraseReq.getPhrase());
            memorablePhraseDto.setPage(phraseReq.getPage());
            memorablePhraseDto.setJournalId(journalDto.getJournalId());
            mapper.insertMemorablePhrase(memorablePhraseDto);
        }

        return journalDto.getJournalId();
    }

    @Override
    public JournalDetailRes getJournalDetail(HttpSession session, Long journalId) {
        String userId = authService.getUserId(session);
        String requestedUserId = mapper.getUserIdByJournalId(journalId);

        if (userId == null || !userId.equals(requestedUserId)) {
            throw new UnauthorizedUserException();
        }

        JournalDto journalDto = mapper.getJournalByJournalId(journalId);
        List<MemorablePhraseDto> memorablePhraseDto = Optional.ofNullable(mapper.getMemorablePhraseByJournalId(journalId))
                .orElseGet(ArrayList::new);

        if (journalDto.isDeleted()) {
            throw new DeletedJournalException();
        }

        return new JournalDetailRes(journalDto, memorablePhraseDto);
    }

    @Override
    @Transactional
    public Long deleteJournalAndGetBookId(HttpSession session, Long journalId) {
        String userId = authService.getUserId(session);
        String requestedUserId = mapper.getUserIdByJournalId(journalId);

        if (userId == null || !userId.equals(requestedUserId)) {
            throw new UnauthorizedUserException();
        }

        mapper.deleteJournalByJournalId(journalId);
        mapper.deleteMemorablePhraseByJournalId(journalId);

        JournalDto journalDto = mapper.getJournalByJournalId(journalId);
        return journalDto.getBookId();
    }

    @Override
    public void updateJoural(HttpSession session, JournalDetailRes journalDetail, Long journalId) {
        log.info("==== Updated phrases {}", journalDetail.getMemorablePhraseResList());
        String userId = authService.getUserId(session);
        String requestedUserId = mapper.getUserIdByJournalId(journalId);

        if (userId == null || !userId.equals(requestedUserId)) {
            throw new UnauthorizedUserException();
        }

        // 기존 감상평 및 인상 깊은 문장 조회
        JournalDto originalJournal = mapper.getJournalByJournalId(journalId);
        List<MemorablePhraseDto> originalPhraseList = mapper.getMemorablePhraseByJournalId(journalId);

        // 감상평 수정
        if (!originalJournal.equals(journalDetail.getJournal())) {
            log.info("Updating journal: {}", journalDetail.getJournal());
            journalDetail.getJournal().setJournalId(journalId);
            mapper.updateJournal(journalDetail.getJournal());
        }

        // 새 인상 깊은 문장
        List<MemorablePhraseDto> newPhraseList = journalDetail.getMemorablePhraseResList();
        for (MemorablePhraseDto newPhrase : newPhraseList) {
            Long phraseId = newPhrase.getMemorablePhraseId();

            if (phraseId != null) {
                // 기존 문장 처리
                if (newPhrase.getIsDeleted()) {
                    // 삭제 요청된 문장
                    log.info("Deleting memorable phrase: {}", newPhrase);
                    mapper.deleteMemorablePhraseById(phraseId);
                } else {
                    // 기존 문장이며 수정된 경우 업데이트
                    MemorablePhraseDto originalPhrase = originalPhraseList.stream()
                            .filter(op -> op.getMemorablePhraseId().equals(phraseId))
                            .findFirst()
                            .orElse(null);

                    if (originalPhrase != null && !originalPhrase.equals(newPhrase)) {
                        log.info("Updating memorable phrase: {}", newPhrase);
                        newPhrase.setJournalId(journalId); // Journal ID 설정
                        mapper.updateMemorablePhraseById(newPhrase);
                    }
                }
            } else {
                // 새로운 문장 처리
                if (!newPhrase.getIsDeleted()) {
                    // 추가 요청된 문장
                    log.info("Inserting new memorable phrase: {}", newPhrase);
                    newPhrase.setJournalId(journalId); // Journal ID 설정
                    mapper.insertMemorablePhrase(newPhrase);
                }
            }
        }
    }
}
