package com.jimin.readingjournal.controller;

import com.jimin.readingjournal.request.SigninReq;
import com.jimin.readingjournal.request.SignupReq;
import com.jimin.readingjournal.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/reading-journal")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String openSignup(Model model) {
        model.addAttribute("signupReq", new SignupReq());

        return "signup";
    }

    // 회원가입 절차
    @PostMapping("/signup")
    public String submitSignup(@ModelAttribute("signupReq") @Valid SignupReq signupReq,
                             BindingResult result, Model model,
                             RedirectAttributes redirectAttributes) {
        // 유효성 검사에 실패했을 경우
        if (result.hasErrors()) {
            return "signup";
        }

        authService.signup(signupReq);

        log.info("회원가입 성공");

        redirectAttributes.addFlashAttribute("alertMessage", "회원가입이 완료되었습니다!");

        return "redirect:/reading-journal";
    }

    // 로그인 페이지
    @GetMapping("/signin")
    public String openSignin(Model model) {
        model.addAttribute("signinReq", new SigninReq());
        return "signin";
    }

    // 로그인
    @PostMapping("/signin")
    public String submitSignin(@ModelAttribute("signinReq") SigninReq signinReq,
                               HttpSession session, RedirectAttributes redirectAttributes) {
        if (!authService.signin(signinReq, session)) {
            redirectAttributes.addFlashAttribute("alertMessage", "로그인에 실패했습니다.");
            return "redirect:/reading-journal/signin";
        }

        redirectAttributes.addFlashAttribute("alertMessage", "로그인에 성공하였습니다.");
        return "redirect:/reading-journal";
    }

    // 로그아웃
    @PostMapping("/signout")
    public String signout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("alertMessage", "로그아웃 되었습니다.");
        return "redirect:/reading-journal";
    }
}
