package com.jimin.readingjournal.domain.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemorablePhraseDto {
    private Long memorablePhraseId;
    private String phrase;
    private int page;
    private Long journalId;
}
