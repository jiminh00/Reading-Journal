package com.jimin.readingjournal.exception.custom;

import com.jimin.readingjournal.exception.code.ErrorCode;

public class PasswordMismatchException extends ReadingJournalException {
    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH.getMessage(), ErrorCode.PASSWORD_MISMATCH.toString(), "confirmPassword");
    }
}
