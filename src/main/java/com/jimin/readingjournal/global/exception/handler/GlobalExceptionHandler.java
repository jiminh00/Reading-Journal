package com.jimin.readingjournal.global.exception.handler;

import com.jimin.readingjournal.domain.auth.request.SignupReq;
import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
import com.jimin.readingjournal.global.exception.custom.*;
import com.jimin.readingjournal.global.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    // 사용자 인증
    @ExceptionHandler({PasswordMismatchException.class, UserIdDuplicateException.class})
    public String handleSignupException(ReadingJournalException e, Model model) {
        log.error("Error occurred - Code: {}", e.getErrorCode(), e);

        ErrorResponse error = ErrorResponse.errorToErrorResponse(e);

        model.addAttribute("error", error);
        model.addAttribute("signupReq", new SignupReq());

        return "signup";
    }

    // 책 등록
    @ExceptionHandler({EmptyFileException.class, InvalidFileNameException.class,
                        InvalidFileTypeException.class, FileUploadFailException.class})
    public String handlerBookRegisterException(ReadingJournalException e, Model model) {
        log.error("Error occurred - Code: {}", e.getErrorCode(), e);

        ErrorResponse error = ErrorResponse.errorToErrorResponse(e);

        model.addAttribute("error", error);
        model.addAttribute("bookRegisterReq", new BookRegisterReq());

        return "book-register";
    }
}
