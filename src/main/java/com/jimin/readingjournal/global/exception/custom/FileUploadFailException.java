package com.jimin.readingjournal.global.exception.custom;

import com.jimin.readingjournal.global.exception.code.ErrorCode;

public class FileUploadFailException extends ReadingJournalException {
    public FileUploadFailException() {
        super(ErrorCode.FILE_UPLOAD_FAILED.getMessage(), ErrorCode.FILE_UPLOAD_FAILED.name(), "image");
    }
}
