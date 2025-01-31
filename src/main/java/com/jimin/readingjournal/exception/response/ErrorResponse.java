package com.jimin.readingjournal.exception.response;

import com.jimin.readingjournal.exception.custom.ReadingJournalException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String field;

    public static ErrorResponse errorToErrorResponse(ReadingJournalException e) {
        return ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .message(e.getMessage())
                .field(e.getField())
                .message(e.getMessage())
                .build();
    }
}
