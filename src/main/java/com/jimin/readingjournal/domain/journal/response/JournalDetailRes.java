package com.jimin.readingjournal.domain.journal.response;

import com.jimin.readingjournal.domain.journal.dto.JournalDto;
import com.jimin.readingjournal.domain.journal.dto.MemorablePhraseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JournalDetailRes {
    private JournalDto journal;
    private List<MemorablePhraseDto> memorablePhraseResList;
}
