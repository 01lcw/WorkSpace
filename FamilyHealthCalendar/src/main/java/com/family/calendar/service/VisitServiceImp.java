package com.family.calendar.service;

import java.util.List;
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
    public VisitDto getVisitDetail(Long visit_id) {
        return mapper.getVisitDetail(visit_id);
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

}
