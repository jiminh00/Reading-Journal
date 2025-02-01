package com.jimin.readingjournal.domain.journal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JournalDto {
    private Long journalId;
    private int startPage;
    private int endPage;
    private String review;
    private LocalDate createdAt;
}
