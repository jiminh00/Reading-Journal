package com.jimin.readingjournal.domain.auth.service;

import com.jimin.readingjournal.domain.auth.dto.UserDto;
import com.jimin.readingjournal.domain.auth.mapper.AuthMapper;
import com.jimin.readingjournal.domain.auth.request.SigninReq;
import com.jimin.readingjournal.domain.auth.request.SignupReq;
import com.jimin.readingjournal.global.exception.custom.PasswordMismatchException;
import com.jimin.readingjournal.global.exception.custom.UnauthorizedUserException;
import com.jimin.readingjournal.global.exception.custom.UserIdDuplicateException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;

    private final AuthMapper mapper;

    @Override
    @Transactional
    public void signup(SignupReq signupReq) {
        // 비밀번호 불일치 예외
        if (!signupReq.getPassword().equals(signupReq.getConfirmPassword())) {
            throw new PasswordMismatchException();
        }

        // 아이디 중복 예외
        if (mapper.selectUserByUserId(signupReq.getId()) != null) {
            throw new UserIdDuplicateException();
        }

        String encodedPassword = passwordEncoder.encode(signupReq.getPassword());

        UserDto userDto = new UserDto();
        userDto.setUserId(signupReq.getId());
        userDto.setUserPw(encodedPassword);

        mapper.insertUser(userDto);
    }

    @Override
    public boolean signin(SigninReq signinReq, HttpSession session) {
        UserDto userDto = mapper.selectUserByUserId(signinReq.getId());
        log.info("stored password {}", userDto.getUserPw());
        if (userDto == null || !passwordEncoder.matches(signinReq.getPassword(), userDto.getUserPw())) {
            return false;
        }

        User user = new User(signinReq.getId(), "", new ArrayList<>());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authToken);

        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("[LOGIN] Principal Type: {}", authentication.getPrincipal().getClass());
        log.info("[LOGIN] Principal Value: {}", authentication.getPrincipal());

        return true;
    }

    @Override
    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedUserException();
        }

        Object principal = authentication.getPrincipal();

        log.info("[getUserId] Principal Type: {}", principal.getClass());
        log.info("[getUserId] Principal Value: {}", principal);


        if (principal instanceof String) {
            return (String) principal;
        }

        if (principal instanceof User) {
            return ((User) principal).getUsername();
        }

        throw new UnauthorizedUserException();
    }
}
