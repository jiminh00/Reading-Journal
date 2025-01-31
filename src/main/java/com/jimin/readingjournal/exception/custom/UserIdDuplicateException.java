package com.jimin.readingjournal.exception.custom;

public class UserIdDuplicateException extends RuntimeException {
    public UserIdDuplicateException(String message) {
        super(message);
    }
}
