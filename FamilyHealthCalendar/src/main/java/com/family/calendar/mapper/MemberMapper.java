package com.family.calendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.family.calendar.dtos.UserDto;

@Mapper
public interface MemberMapper {

    // 회원가입
    int insertUser(UserDto dto);

    // 로그인
    UserDto findUserByUsernameAndPassword(@Param("username") String username,
                                          @Param("password") String password);
}