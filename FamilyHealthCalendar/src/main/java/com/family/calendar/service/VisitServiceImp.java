package com.family.calendar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.family.calendar.dtos.VisitDto;
import com.family.calendar.mapper.VisitMapper;

@Service
public class VisitServiceImp implements VisitService {

    @Autowired
    private VisitMapper mapper;

    @Override
    public List<VisitDto> getVisitList() {
        return mapper.getVisitList();
    }

    @Override
    public VisitDto getVisitDetail(Map<String, Object> params) {
        return mapper.getVisitDetail(params);
    }

    @Override
    public void insertVisit(VisitDto dto) {
    	mapper.insertVisit(dto);   // VisitService: boolean 이라고 가정
    }

    @Override
    public void updateVisit(VisitDto dto) {
        mapper.updateVisit(dto);             // VisitService: void 이면 이렇게
    }

    @Override
    public void deleteVisit(Long visit_id) {
        mapper.deleteVisit(visit_id);        // VisitService: void 이면 이렇게
    }
    
    @Override
    public List<VisitDto> getVisitListByDate(String date) {
        return mapper.getVisitListByDate(date);
    }
    
    @Override
    public int getVisitCountByDate(String yyyyMMdd) {
        return mapper.getVisitCountByDate(yyyyMMdd);
    }
    
    @Override
    public List<VisitDto> getVisitListByUser(Long userId) {
        return mapper.getVisitListByUser(userId);
    }
    
    @Override
    public List<VisitDto> getVisitListByDateAndUser(String date, Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", date);
        params.put("userId", userId);
        return mapper.getVisitListByDateAndUser(params);
    }

    
    @Override
    public int getVisitCountByDateAndUser(String yyyyMMdd, Long userId) {
        return mapper.getVisitCountByDateAndUser(yyyyMMdd, userId);

    }
}
