package com.jimin.readingjournal.domain.journal.response;

import lombok.Data;

@Data
public class BookCardRes {
    private Long bookId;
    private String bookTitle;
    private String bookImageUrl;
}
