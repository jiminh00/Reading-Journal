package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.journal.response.BookCardRes;

import java.util.List;

public interface MainService {
    List<BookCardRes> getBookCardsByUserId();
}
