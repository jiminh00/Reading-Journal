package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.auth.service.AuthService;
import com.jimin.readingjournal.domain.journal.dto.BookDto;
import com.jimin.readingjournal.domain.journal.dto.BookImageDto;
import com.jimin.readingjournal.domain.journal.dto.JournalDto;
import com.jimin.readingjournal.domain.journal.mapper.JournalMapper;
import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
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
    public List<JournalDto> getJournalsByBookId(HttpSession session, Long bookId) {
        String loginedUserId = authService.getUserId(session);
        String requestedUserId = mapper.getUserIdByBookId(bookId);

        if (loginedUserId == null || !loginedUserId.equals(requestedUserId)) {
            throw new UnauthorizedUserException();
        }

        return mapper.getJournalsByBookId(bookId);
    }
}
