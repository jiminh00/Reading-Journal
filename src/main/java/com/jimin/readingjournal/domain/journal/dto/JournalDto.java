package com.jimin.readingjournal.domain.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
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
    private boolean isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalDto that = (JournalDto) o;

        return startPage == that.startPage &&
                endPage == that.endPage &&
                Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPage, endPage, review);
    }
}
