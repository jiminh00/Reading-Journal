package com.jimin.readingjournal.domain.auth.service;

import com.jimin.readingjournal.domain.auth.dto.UserDto;
import com.jimin.readingjournal.global.exception.custom.PasswordMismatchException;
import com.jimin.readingjournal.global.exception.custom.UserIdDuplicateException;
import com.jimin.readingjournal.domain.auth.mapper.AuthMapper;
import com.jimin.readingjournal.domain.auth.request.SigninReq;
import com.jimin.readingjournal.domain.auth.request.SignupReq;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            log.info("User with id {} login failed", signinReq.getId());
            // dblvhY789!
            return false;
        }

        log.info("User with id {} login succeed", signinReq.getId());
        session.setAttribute("userId", userDto.getUserId());

        return true;
    }
}
