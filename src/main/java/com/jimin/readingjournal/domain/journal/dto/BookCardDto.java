package com.jimin.readingjournal.domain.journal.dto;

import lombok.Data;

@Data
public class BookCardDto {
    private Long bookId;
    private String bookTitle;
    private String bookImageUrl;
}
