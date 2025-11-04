package com.family.calendar.service;

import java.util.List;
import com.family.calendar.dtos.CommuDto;

public interface CommuService {
    List<CommuDto> getLogsByVisit(Long visit_id);
    boolean insertLog(CommuDto dto);
}
