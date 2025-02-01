package com.jimin.readingjournal.domain.journal.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JournalListRes {
    private Long journalId;
    private int startPage;
    private int endPage;
    private String review;
    private LocalDate createdAt;
}
