package com.jimin.readingjournal.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (필요시 활성화 가능)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/reading-journal", "/reading-journal/signin", "/reading-journal/signup", "/css/**", "/js/**", "/images/**").permitAll() // 로그인, 회원가입, 정적 리소스는 모두 허용
                        .requestMatchers(HttpMethod.POST, "/reading-journal/signup").permitAll()    // 회원가입 모두 허용
                        .anyRequest().authenticated() // 나머지는 로그인된 사용자만 접근 가능
                )
                .formLogin(form -> form.disable()) // Spring Security 기본 로그인 비활성화
                .logout(logout -> logout
                        .logoutUrl("/reading-journal/signout") // 로그아웃 URL
                        .logoutSuccessUrl("/reading-journal") // 로그아웃 성공 후 이동할 페이지
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
                )
                .sessionManagement(session -> session
                        .sessionFixation().newSession() // 로그인 시 새로운 세션 생성
                        .maximumSessions(1) // 한 명의 사용자가 하나의 세션만 유지 가능
                        .maxSessionsPreventsLogin(false) // 기존 세션 만료 시 새 로그인 허용
                );

        return http.build();
    }
}
