package com.jimin.readingjournal.domain.journal.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemorablePhraseReq {
    private String phrase;
    private int page;
}
