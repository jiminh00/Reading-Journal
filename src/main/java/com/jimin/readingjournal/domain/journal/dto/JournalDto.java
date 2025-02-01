package com.jimin.readingjournal.domain.journal.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JournalDto {
    private Long journalId;
    private int startPage;
    private int endPage;
    private String review;
    private String userId;
    private Long bookId;
    private LocalDate createdAt;
}
