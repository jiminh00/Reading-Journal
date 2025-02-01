package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.journal.dto.BookCardDto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface MainService {
    List<BookCardDto> getBookCardsByUserId(HttpSession session);
}
