package com.family.calendar.service;

import com.family.calendar.dtos.UserDto;

public interface MemberService {

    // 회원가입
    void register(UserDto dto);

    // 로그인
    UserDto login(String username, String password);
}
