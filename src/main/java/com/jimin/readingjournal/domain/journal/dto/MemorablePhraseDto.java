package com.jimin.readingjournal.domain.journal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemorablePhraseDto {
    private Long memorablePhraseId;
    private String phrase;
    private int page;
    private Long journalId;
}
