package com.jimin.readingjournal.domain.journal.controller;

import com.jimin.readingjournal.domain.journal.response.BookCardRes;
import com.jimin.readingjournal.domain.journal.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/reading-journal")
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping
    public String openIndex(Model model) {
        List<BookCardRes> bookCards = mainService.getBookCardsByUserId();

        if (bookCards != null) {
            model.addAttribute("bookCards", bookCards);
        }
        return "index";
    }
}
