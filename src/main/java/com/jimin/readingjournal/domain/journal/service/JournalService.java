package com.jimin.readingjournal.domain.journal.service;

import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
import com.jimin.readingjournal.domain.journal.request.JournalWriteReq;
import com.jimin.readingjournal.domain.journal.response.JournalDetailRes;
import com.jimin.readingjournal.domain.journal.response.JournalListRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JournalService {
    void registerBook(BookRegisterReq bookRegisterReq, MultipartFile image);

    List<JournalListRes> getJournalsByBookId(Long bookId);

    Long insertJournalAndGetJournalId(JournalWriteReq req);

    JournalDetailRes getJournalDetail(Long journalId);

    Long deleteJournalAndGetBookId(Long journalId);

    void updateJoural(JournalDetailRes journalDetail, Long journalId);
}
