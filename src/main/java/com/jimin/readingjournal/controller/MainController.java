package com.jimin.readingjournal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/reading-journal")
public class MainController {
    @GetMapping
    public String openIndex() {
        return "index";
    }
}
