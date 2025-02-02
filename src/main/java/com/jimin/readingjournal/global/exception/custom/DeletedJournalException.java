package com.jimin.readingjournal.global.exception.custom;

import com.jimin.readingjournal.global.exception.code.ErrorCode;

public class DeletedJournalException extends ReadingJournalException {
    public DeletedJournalException() {
        super(ErrorCode.DELETED_JOURNAL.getMessage(), ErrorCode.DELETED_JOURNAL.name(), null);
    }
}
