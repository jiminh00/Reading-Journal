package com.jimin.readingjournal.exception.handler;

import com.jimin.readingjournal.exception.custom.PasswordMismatchException;
import com.jimin.readingjournal.exception.custom.UserIdDuplicateException;
import com.jimin.readingjournal.request.SignupReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordMismatchException.class)
    public String handlePasswordMismatchException(PasswordMismatchException e, Model model) {
        log.error("Password mismatch exception", e);

        model.addAttribute("passwordError", e.getMessage());
        model.addAttribute("signupReq", new SignupReq());

        return "signup";
    }

    @ExceptionHandler(UserIdDuplicateException.class)
    public String handleUserIdDuplicateException(UserIdDuplicateException e, Model model) {
        log.error("Password mismatch exception", e);

        model.addAttribute("userIdDuplicateError", e.getMessage());
        model.addAttribute("signupReq", new SignupReq());

        return "signup";
    }
}
