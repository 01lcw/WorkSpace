package com.family.calendar.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.family.calendar.dtos.HealthLogDto;

@Mapper
public interface HealthLogMapper {

    List<HealthLogDto> getAllLogs();

    List<HealthLogDto> getLogsByVisit(Long visit_id);

    List<HealthLogDto> getLogsByMember(int member_id);

    HealthLogDto getLogDetail(int log_id);

    int insertLog(HealthLogDto dto);

    int updateLog(HealthLogDto dto);

    int deleteLog(int log_id);

    List<HealthLogDto> getLogsByType(Map<String, Object> params);
    
    List<HealthLogDto> getLogsByVisit(int visit_id);

}