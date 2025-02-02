package com.jimin.readingjournal.global.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 사용자 인증
    UNAUTHORIZED_USER("허용되지 않는 접근입니다."),
    PASSWORD_MISMATCH("비밀번호와 일치하지 않습니다. 다시 확인해주세요."),
    USERID_DUPLICATE("이미 등록된 사용자 아이디입니다."),

    // 파일 저장
    EMPTY_FILE("파일이 비어 있습니다."),
    INVALID_FILE_NAME("파일명이 올바르지 않습니다."),
    INVALID_FILE_TYPE("지원하지 않는 이미지 형식입니다."),
    FILE_UPLOAD_FAILED("파일 저장에 실패했습니다."),

    // 삭제된 감상평
    DELETED_JOURNAL("삭제된 감상평입니다.");

    private final String message;
}