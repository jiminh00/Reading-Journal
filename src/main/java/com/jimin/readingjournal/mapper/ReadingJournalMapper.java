package com.jimin.readingjournal.mapper;

import com.jimin.readingjournal.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadingJournalMapper {
    void insertUser(UserDto userDto);

    List<UserDto> selectUserByUserId(String id);
}
