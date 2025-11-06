package com.family.calendar.service;

import java.util.List;
import com.family.calendar.dtos.FamilyMemberDto;

public interface FamilyMemberService {

    List<FamilyMemberDto> getMembersByUser(Long user_id);

    FamilyMemberDto getMemberDetail(Long member_id);

    void insertMember(FamilyMemberDto dto, Long user_id);

    void updateMember(FamilyMemberDto dto);

    void deleteMember(Long member_id);
}
