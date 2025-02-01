package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

public interface JournalService {
    void registerBook(BookRegisterReq bookRegisterReq, MultipartFile image, HttpSession session);
}
