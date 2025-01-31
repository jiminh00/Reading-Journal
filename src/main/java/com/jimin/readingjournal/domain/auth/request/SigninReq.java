package com.jimin.readingjournal.domain.auth.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninReq {
    private String id;
    private String password;
}
