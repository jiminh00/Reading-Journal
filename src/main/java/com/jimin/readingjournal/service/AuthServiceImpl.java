package com.jimin.readingjournal.service;

import com.jimin.readingjournal.dto.UserDto;
import com.jimin.readingjournal.exception.custom.PasswordMismatchException;
import com.jimin.readingjournal.exception.custom.UserIdDuplicateException;
import com.jimin.readingjournal.mapper.ReadingJournalMapper;
import com.jimin.readingjournal.request.SignupReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;

    private final ReadingJournalMapper mapper;

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
        userDto.setPassword(encodedPassword);

        mapper.insertUser(userDto);
    }
}
