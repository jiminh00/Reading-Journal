package com.jimin.readingjournal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reading-journal")
public class JournalController {
    @GetMapping("/journal-list")
    public String journalList() {
        return "journal-list";
    }

    @GetMapping("/journal-detail")
    public String journalDetail() {
        return "journal-detail";
    }
}
