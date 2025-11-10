package com.family.calendar.service;

import java.util.List;
import java.util.Map;
import com.family.calendar.dtos.HealthLogDto;

public interface HealthLogService {

    List<HealthLogDto> getAllLogs();

    List<HealthLogDto> getLogsByVisit(Long visit_id);

    List<HealthLogDto> getLogsByMember(int member_id);

    HealthLogDto getLogDetail(int log_id);

    boolean insertLog(HealthLogDto dto);

    boolean updateLog(HealthLogDto dto);

    boolean deleteLog(int log_id);

    List<HealthLogDto> getLogsByType(Map<String, Object> params);
}
