package com.family.calendar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.family.calendar.command.InsertCalCommand;
import com.family.calendar.dtos.CalendarDto;
import com.family.calendar.dtos.VisitDto;

@Mapper
public interface CalMapper {
    boolean insertCal(CalendarDto dto);
    List<CalendarDto> getCalList();
    CalendarDto getCalDetail(int seq);
    boolean updateCal(CalendarDto dto);
    boolean deleteCal(int seq);
    List<VisitDto> getVisitListByUser(int user_id);

}