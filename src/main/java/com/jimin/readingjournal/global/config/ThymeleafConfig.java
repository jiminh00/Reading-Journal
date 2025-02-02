package com.jimin.readingjournal.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Controller
public class ThymeleafConfig {
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
