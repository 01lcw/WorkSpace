package com.family.calendar.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.family.calendar.dtos.CommuDto;

@Mapper
public interface CommuMapper {
    List<CommuDto> getLogsByVisit(Long visit_id);
    int insertLog(CommuDto dto);
}
