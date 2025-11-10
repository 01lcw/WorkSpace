package com.family.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.family.calendar.dtos.UserDto;
import com.family.calendar.mapper.MemberMapper;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    private MemberMapper mapper;

    @Override
    public void register(UserDto dto) {
        mapper.insertUser(dto);
    }

    @Override
    public UserDto login(String username, String password) {
        return mapper.findUserByUsernameAndPassword(username, password);
    }
}