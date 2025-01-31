package com.jimin.readingjournal.global.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PASSWORD_MISMATCH("비밀번호와 일치하지 않습니다. 다시 확인해주세요."),
    USERID_DUPLICATE("이미 등록된 사용자 아이디입니다.");

    private final String message;
}