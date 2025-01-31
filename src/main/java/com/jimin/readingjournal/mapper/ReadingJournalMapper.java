package com.jimin.readingjournal.mapper;

import com.jimin.readingjournal.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadingJournalMapper {
    void insertUser(UserDto userDto);
}
