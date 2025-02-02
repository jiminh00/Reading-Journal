package com.jimin.readingjournal.global.exception.handler;

import com.jimin.readingjournal.domain.auth.request.SignupReq;
import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
import com.jimin.readingjournal.global.exception.custom.*;
import com.jimin.readingjournal.global.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    // 허용되지 않는 접근
    @ExceptionHandler(UnauthorizedUserException.class)
    public String handleUnauthorizedUserException(UnauthorizedUserException e, RedirectAttributes redirectAttributes) {
        log.error("Error occurred - Code: {}", e.getErrorCode(), e);

        redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
        return "redirect:/reading-journal";
    }

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

    // 삭제된 감상평
    @ExceptionHandler(DeletedJournalException.class)
    public String handleDeletedJournalException(DeletedJournalException e, RedirectAttributes redirectAttributes) {
        log.error("Error occurred - Code: {}", e.getErrorCode(), e);

        redirectAttributes.addFlashAttribute("alertMessage", e.getMessage());
        return "redirect:/reading-journal";
    }
}
