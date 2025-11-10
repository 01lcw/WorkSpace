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
    public List<VisitDto> getVisitListByUser(int user_id) {
        return mapper.getVisitListByUser(user_id);
    }

    @Override
    public VisitDto getVisitDetail(int visit_id) {
        return mapper.getVisitDetail(visit_id);
    }

    @Override
    public void insertVisit(VisitDto dto) {
        mapper.insertVisit(dto);
    }

    @Override
    public void deleteVisit(int visit_id) {
        mapper.deleteVisit(visit_id);
    }
}
