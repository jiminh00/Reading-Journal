package com.jimin.readingjournal.domain.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemorablePhraseDto {
    private Long memorablePhraseId;
    private String phrase;
    private Integer page;
    private Long journalId;
    private Boolean isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemorablePhraseDto that = (MemorablePhraseDto) o;

        return page == that.page &&
                isDeleted == that.isDeleted &&
                Objects.equals(phrase, that.phrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, phrase, isDeleted);
    }
}
