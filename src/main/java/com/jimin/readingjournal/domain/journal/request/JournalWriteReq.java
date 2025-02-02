package com.jimin.readingjournal.domain.journal.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class JournalWriteReq {
    private int startPage;
    private int endPage;
    private String review;
    private Long bookId;
    private List<MemorablePhraseReq> phraseList = new ArrayList<>();
}
