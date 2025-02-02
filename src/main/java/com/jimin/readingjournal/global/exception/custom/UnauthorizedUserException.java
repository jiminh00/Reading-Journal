package com.jimin.readingjournal.global.exception.custom;

import com.jimin.readingjournal.global.exception.code.ErrorCode;

public class UnauthorizedUserException extends ReadingJournalException{
    public UnauthorizedUserException() {
        super(ErrorCode.UNAUTHORIZED_USER.getMessage(), ErrorCode.UNAUTHORIZED_USER.name(), null);
    }
}
