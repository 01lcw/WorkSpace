package com.family.calendar.service;

import java.util.List;
import com.family.calendar.dtos.VisitDto;

public interface VisitService {
    void insertVisit(VisitDto dto);
    List<VisitDto> getVisitList();
    VisitDto getVisitDetail(Long visit_id);  // 변경
    void updateVisit(VisitDto dto);
    void deleteVisit(Long visit_id);         // 변경
    List<VisitDto> getVisitListByDate(String date);

}
