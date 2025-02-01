package com.jimin.readingjournal.global.exception.custom;

import com.jimin.readingjournal.global.exception.code.ErrorCode;

public class InvalidFileNameException extends ReadingJournalException{
    public InvalidFileNameException() {
        super(ErrorCode.INVALID_FILE_NAME.getMessage(), ErrorCode.INVALID_FILE_NAME.name(), "image");
    }
}
