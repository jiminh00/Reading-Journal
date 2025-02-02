package com.jimin.readingjournal.global.exception.custom;

import com.jimin.readingjournal.global.exception.code.ErrorCode;

public class InvalidFileTypeException extends ReadingJournalException{
    public InvalidFileTypeException() {
        super(ErrorCode.INVALID_FILE_TYPE.getMessage(), ErrorCode.INVALID_FILE_TYPE.name(), "name");
    }
}
