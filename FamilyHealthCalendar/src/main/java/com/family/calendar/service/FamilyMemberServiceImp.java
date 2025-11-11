package com.family.calendar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.family.calendar.dtos.FamilyMemberDto;
import com.family.calendar.mapper.FamilyMemberMapper;

@Service
public class FamilyMemberServiceImp implements FamilyMemberService {

    @Autowired
    private FamilyMemberMapper mapper;

    @Override
    public List<FamilyMemberDto> getMembersByUser(int user_id) {
        return mapper.getMembersByUser(user_id);
    }

    @Override
    public FamilyMemberDto getMemberDetail(Long member_id) {
        return mapper.getMemberDetail(member_id);
    }

    @Override
    public void insertMember(FamilyMemberDto dto) {
        mapper.insertMember(dto);
    }

    @Override
    public void updateMember(FamilyMemberDto dto) {
        mapper.updateMember(dto);
    }

    @Override
    public void deleteMember(Long member_id) {
        mapper.deleteMember(member_id);
    }
}