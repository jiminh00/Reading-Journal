package com.jimin.readingjournal.domain.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JournalDto {
    private Long journalId;
    private int startPage;
    private int endPage;
    private String review;
    private String userId;
    private Long bookId;
    private LocalDate createdAt;
}
