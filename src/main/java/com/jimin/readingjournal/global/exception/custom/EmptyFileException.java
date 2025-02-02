package com.jimin.readingjournal.global.exception.custom;

import com.jimin.readingjournal.global.exception.code.ErrorCode;

public class EmptyFileException extends ReadingJournalException {
    public EmptyFileException() {
        super(ErrorCode.EMPTY_FILE.getMessage(), ErrorCode.EMPTY_FILE.name(), "image");
    }
}
