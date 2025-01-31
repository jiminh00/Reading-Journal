package com.jimin.readingjournal.domain.auth.service;

import com.jimin.readingjournal.domain.auth.request.SigninReq;
import com.jimin.readingjournal.domain.auth.request.SignupReq;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    void signup(SignupReq signupReq);

    boolean signin(SigninReq signinReq, HttpSession session);
}
