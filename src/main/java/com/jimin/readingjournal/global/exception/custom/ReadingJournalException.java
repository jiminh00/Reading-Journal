package com.jimin.readingjournal.global.exception.custom;

import lombok.Getter;

@Getter
public class ReadingJournalException extends RuntimeException {
    private final String errorCode;
    private final String field;

    public ReadingJournalException(String message, String errorCode, String field) {
        super(message);
        this.errorCode = errorCode;
        this.field = field;
    }
}
