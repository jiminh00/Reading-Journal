package com.jimin.readingjournal.service;

import com.jimin.readingjournal.request.SignupReq;

public interface AuthService {
    void signup(SignupReq signupReq);
}
