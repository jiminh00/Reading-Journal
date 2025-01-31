package com.jimin.readingjournal.service;

import com.jimin.readingjournal.request.SigninReq;
import com.jimin.readingjournal.request.SignupReq;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    void signup(SignupReq signupReq);

    boolean signin(SigninReq signinReq, HttpSession session);
}
