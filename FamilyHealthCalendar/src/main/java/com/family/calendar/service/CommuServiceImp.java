package com.family.calendar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.family.calendar.dtos.CommuDto;
import com.family.calendar.mapper.CommuMapper;

@Service
public class CommuServiceImp implements CommuService {

    @Autowired
    private CommuMapper mapper;

    @Override
    public List<CommuDto> getLogsByVisit(Long visit_id) {
        return mapper.getLogsByVisit(visit_id);
    }

    @Override
    public boolean insertLog(CommuDto dto) {
        return mapper.insertLog(dto) > 0;
    }
}
