package com.jimin.readingjournal.domain.journal.mapper;

import com.jimin.readingjournal.domain.journal.dto.*;
import com.jimin.readingjournal.domain.journal.response.BookCardRes;
import com.jimin.readingjournal.domain.journal.response.JournalListRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JournalMapper {
    void insertBook(BookDto bookDto);

    void insertBookImage(BookImageDto bookImageDto);

    List<BookCardRes> getBookCardsByUserId(String userId);

    String getUserIdByBookId(Long bookId);

    List<JournalListRes> getJournalsByBookId(Long bookId);

    void insertJournal(JournalDto journalDto);

    void insertMemorablePhrase(MemorablePhraseDto memorablePhraseDto);
}
