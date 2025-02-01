package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.auth.service.AuthService;
import com.jimin.readingjournal.domain.journal.response.BookCardRes;
import com.jimin.readingjournal.domain.journal.mapper.JournalMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
    private final AuthService authService;
    private final JournalMapper mapper;

    @Override
    public List<BookCardRes> getBookCardsByUserId(HttpSession session) {
        String userId = authService.getUserId(session);
        if (userId == null) {
            return null;
        }

        return mapper.getBookCardsByUserId(userId);
    }
}
