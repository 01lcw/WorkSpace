package com.family.calendar.service;

import java.util.List;
import java.util.Map;

import com.family.calendar.dtos.VisitDto;

public interface VisitService {
    void insertVisit(VisitDto dto);
    List<VisitDto> getVisitList();
    VisitDto getVisitDetail(Map<String, Object> params);    // ✅ Long으로 통일
    void updateVisit(VisitDto dto);
    void deleteVisit(Long visit_id);           // ✅ Long으로 통일
    List<VisitDto> getVisitListByDate(String date);
    int getVisitCountByDate(String yyyyMMdd);
	List<VisitDto> getVisitListByUser(Long userId);
	List<VisitDto> getVisitListByDateAndUser(String date, Long userId);
	int getVisitCountByDateAndUser(String yyyyMMdd, Long userId);
	

}
