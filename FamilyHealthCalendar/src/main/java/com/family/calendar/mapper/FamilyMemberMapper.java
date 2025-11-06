package com.family.calendar.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.family.calendar.dtos.FamilyMemberDto;

@Mapper
public interface FamilyMemberMapper {

    List<FamilyMemberDto> getMembersByUser(Long user_id);

    FamilyMemberDto getMemberDetail(Long member_id);

    void insertMember(FamilyMemberDto dto);

    void updateMember(FamilyMemberDto dto);

    void deleteMember(Long member_id);
}
