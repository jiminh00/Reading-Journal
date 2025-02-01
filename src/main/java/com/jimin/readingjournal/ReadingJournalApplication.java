package com.jimin.readingjournal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.jimin.readingjournal.domain.auth.mapper", "com.jimin.readingjournal.domain.journal.mapper"})
public class ReadingJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingJournalApplication.class, args);
	}

}
