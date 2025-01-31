package com.jimin.readingjournal.exception.custom;

import com.jimin.readingjournal.exception.code.ErrorCode;

public class UserIdDuplicateException extends ReadingJournalException {
    public UserIdDuplicateException() {
        super(ErrorCode.USERID_DUPLICATE.getMessage(), ErrorCode.USERID_DUPLICATE.toString(), "userId");
    }
}
