package com.jimin.readingjournal.exception.handler;

import com.jimin.readingjournal.exception.custom.PasswordMismatchException;
import com.jimin.readingjournal.exception.custom.ReadingJournalException;
import com.jimin.readingjournal.exception.custom.UserIdDuplicateException;
import com.jimin.readingjournal.exception.response.ErrorResponse;
import com.jimin.readingjournal.request.SignupReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({PasswordMismatchException.class, UserIdDuplicateException.class})
    public String handleSignupException(ReadingJournalException e, Model model) {
        log.error("Error occurred - Code: {}", e.getErrorCode(), e);

        ErrorResponse error = ErrorResponse.errorToErrorResponse(e);

        model.addAttribute("error", error);
        model.addAttribute("signupReq", new SignupReq());

        return "signup";
    }
}
