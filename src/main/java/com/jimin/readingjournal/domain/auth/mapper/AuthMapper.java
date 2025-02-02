package com.jimin.readingjournal.domain.auth.mapper;

import com.jimin.readingjournal.domain.auth.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    void insertUser(UserDto userDto);

    UserDto selectUserByUserId(String id);
}
