package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
import com.jimin.readingjournal.domain.journal.request.JournalWriteReq;
import com.jimin.readingjournal.domain.journal.response.JournalDetailRes;
import com.jimin.readingjournal.domain.journal.response.JournalListRes;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JournalService {
    void registerBook(BookRegisterReq bookRegisterReq, MultipartFile image, HttpSession session);

    List<JournalListRes> getJournalsByBookId(HttpSession session, Long bookId);

    Long insertJournalAndGetJournalId(HttpSession session, JournalWriteReq req);

    JournalDetailRes getJournalDetail(HttpSession session, Long journalId);

    Long deleteJournalAndGetBookId(HttpSession session, Long journalId);

    void updateJoural(HttpSession session, JournalDetailRes journalDetail, Long journalId);
}
