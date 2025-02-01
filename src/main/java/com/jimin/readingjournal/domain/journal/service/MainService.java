package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.journal.response.BookCardRes;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface MainService {
    List<BookCardRes> getBookCardsByUserId(HttpSession session);
}
