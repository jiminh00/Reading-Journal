package com.jimin.readingjournal.domain.journal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private Long bookId;
    private String userId;
    private String bookTitle;
    private String bookAuthor;
}