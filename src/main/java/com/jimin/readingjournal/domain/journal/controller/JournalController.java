package com.jimin.readingjournal.domain.journal.controller;

import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
import com.jimin.readingjournal.domain.journal.service.JournalService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/reading-journal")
@RequiredArgsConstructor
public class JournalController {
    private final JournalService journalService;

    // 책 등록 페이지
    @GetMapping("/book-register")
    public String openBookRegister(Model model) {
        model.addAttribute("bookRegisterReq", new BookRegisterReq());
        return "book-register";
    }

    // 책 등록
    @PostMapping("/book-register")
    public String registerBook(@ModelAttribute("bookRegisterReq") BookRegisterReq bookRegisterReq,
                               @RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes,
                               HttpSession session) {
        log.info("book register request: {}", bookRegisterReq);
        journalService.registerBook(bookRegisterReq, image, session);

        redirectAttributes.addFlashAttribute("alertMessage", "책 등록이 완료되었습니다.");
        return "redirect:/reading-journal";
    }
}
