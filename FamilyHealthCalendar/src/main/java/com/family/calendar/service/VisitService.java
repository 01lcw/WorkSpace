package com.family.calendar.service;

import java.util.List;
import com.family.calendar.dtos.VisitDto;

public interface VisitService {

    // 전체 방문 내역 (로그인한 유저 기준)
    List<VisitDto> getVisitListByUser(int user_id);

    // 방문 상세 조회
    VisitDto getVisitDetail(int visit_id);

    // 방문 등록
    void insertVisit(VisitDto dto);

    // 방문 삭제
    void deleteVisit(int visit_id);
}
