package com.jimin.readingjournal.exception.handler;

import com.jimin.readingjournal.exception.custom.PasswordMismatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordMismatchException.class)
    public String handlePasswordMismatchException(PasswordMismatchException e, Model model) {
        model.addAttribute("passwordError", e.getMessage());
        return "/reading-journal/signup";
    }
}
