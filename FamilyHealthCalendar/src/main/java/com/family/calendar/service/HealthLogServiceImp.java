package com.family.calendar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.calendar.dtos.HealthLogDto;
import com.family.calendar.mapper.HealthLogMapper;

@Service
public class HealthLogServiceImp implements HealthLogService {

    @Autowired
    private HealthLogMapper mapper;

    @Override
    public List<HealthLogDto> getAllLogs() {
        return mapper.getAllLogs();
    }

    @Override
    public List<HealthLogDto> getLogsByVisit(Long visit_id) {
        return mapper.getLogsByVisit(visit_id);
    }

    @Override
    public List<HealthLogDto> getLogsByMember(int member_id) {
        return mapper.getLogsByMember(member_id);
    }

    @Override
    public HealthLogDto getLogDetail(int log_id) {
        return mapper.getLogDetail(log_id);
    }

    @Override
    public boolean insertLog(HealthLogDto dto) {
        return mapper.insertLog(dto) > 0;
    }

    @Override
    public boolean updateLog(HealthLogDto dto) {
        return mapper.updateLog(dto) > 0;
    }

    @Override
    public boolean deleteLog(int log_id) {
        return mapper.deleteLog(log_id) > 0;
    }

    @Override
    public List<HealthLogDto> getLogsByType(Map<String, Object> params) {
        return mapper.getLogsByType(params);
    }
}
