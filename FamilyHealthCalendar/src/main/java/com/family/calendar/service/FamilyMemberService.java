package com.family.calendar.service;

import java.util.List;
import com.family.calendar.dtos.FamilyMemberDto;

public interface FamilyMemberService {

    // 가족 구성원 전체 조회 (유저별)
    List<FamilyMemberDto> getMembersByUser(int user_id);

    // 가족 구성원 상세 조회
    FamilyMemberDto getMemberDetail(Long member_id);

    // 가족 구성원 등록
    void insertMember(FamilyMemberDto dto);

    // 가족 구성원 수정
    void updateMember(FamilyMemberDto dto);

    // 가족 구성원 삭제
    void deleteMember(Long member_id);
}