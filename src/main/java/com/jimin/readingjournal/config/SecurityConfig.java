package com.jimin.readingjournal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ CSRF 비활성화 (REST API 용도)
                .securityMatcher("/**")  // ✅ 모든 요청에 대해 보안 필터 적용 (모든 요청 허용)
                .formLogin(form -> form.disable())  // ✅ 기본 로그인 비활성화
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/")); // ✅ 로그아웃 설정

        return http.build();
    }
}
