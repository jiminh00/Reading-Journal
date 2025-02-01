package com.jimin.readingjournal.domain.journal.mapper;

import com.jimin.readingjournal.domain.journal.dto.BookCardDto;
import com.jimin.readingjournal.domain.journal.dto.BookDto;
import com.jimin.readingjournal.domain.journal.dto.BookImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JournalMapper {
    void insertBook(BookDto bookDto);

    void insertBookImage(BookImageDto bookImageDto);

    List<BookCardDto> getBookCardsByUserId(String userId);
}
