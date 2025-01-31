package com.jimin.readingjournal.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupReq {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 5, message = "아이디는 최소 5자 이상이어야 합니다.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String confirmPassword;
}