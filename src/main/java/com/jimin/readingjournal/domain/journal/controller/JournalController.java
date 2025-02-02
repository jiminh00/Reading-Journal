package com.jimin.readingjournal.domain.journal.controller;

import com.jimin.readingjournal.domain.journal.request.BookRegisterReq;
import com.jimin.readingjournal.domain.journal.request.JournalWriteReq;
import com.jimin.readingjournal.domain.journal.response.JournalDetailRes;
import com.jimin.readingjournal.domain.journal.response.JournalListRes;
import com.jimin.readingjournal.domain.journal.service.JournalService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping("/journal-list")
    public String journalList(@RequestParam(name="bookId") Long bookId, HttpSession session, Model model) {
        List<JournalListRes> journals = journalService.getJournalsByBookId(session, bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("journals", journals);
        return "journal-list";
    }

    @GetMapping("/journal-write")
    public String openJournalWrite(@RequestParam(name="bookId") Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        model.addAttribute("journalWriteReq", new JournalWriteReq());
        return "journal-write";
    }

    @PostMapping("/journal-write")
    public String journalWrite(@ModelAttribute("journalWriteReq") JournalWriteReq req, HttpSession session) {
        Long journalId = journalService.insertJournalAndGetJournalId(session, req);

        return "redirect:/reading-journal/journal-detail?journalId=" + journalId;
    }

    @GetMapping("/journal-detail")
    public String openJournalDetail(@RequestParam(name="journalId") Long journalId, Model model, HttpSession session) {
        JournalDetailRes journalDetailRes = journalService.getJournalDetail(session, journalId);
        model.addAttribute("journalDetail", journalDetailRes);
        return "journal-detail";
    }

    @PostMapping("/journal-delete/{journalId}")
    public String deleteJournal(@PathVariable(name="journalId") Long journalId, HttpSession session, RedirectAttributes redirectAttributes) {
        Long bookId = journalService.deleteJournalAndGetBookId(session, journalId);

        redirectAttributes.addFlashAttribute("alertMessage", "감상평이 삭제되었습니다.");
        return "redirect:/reading-journal/journal-list?bookId=" + bookId;
    }

    @GetMapping("/journal-update")
    public String openJournalUpdate(@RequestParam(name="journalId") Long journalId, HttpSession session, Model model) {
        JournalDetailRes journalDetailRes = journalService.getJournalDetail(session, journalId);

        log.info("openJournalUpdate - journalDetailRes: {}", journalDetailRes);

        model.addAttribute("journalDetail", journalDetailRes);
        return "journal-update";
    }

    @PostMapping("/journal-update/{journalId}")
    public String journalUpdate(@PathVariable(name="journalId") Long journalId, HttpSession session,
                                @ModelAttribute("journalDetail") JournalDetailRes journalDetail) {
        journalService.updateJoural(session, journalDetail, journalId);

        return "redirect:/reading-journal/journal-detail?journalId=" + journalId;
    }
}
