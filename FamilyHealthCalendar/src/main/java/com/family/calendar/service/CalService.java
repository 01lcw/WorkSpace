package com.family.calendar.service;

import java.util.List;
import java.util.Map;

import com.family.calendar.command.InsertCalCommand;
import com.family.calendar.dtos.CalendarDto;
import com.family.calendar.dtos.VisitDto;

public interface CalService {

	public boolean insertCalBoard(InsertCalCommand insertCommand);

	public List<CalendarDto> getAllCals();

	public CalendarDto getCalDetail(int seq);

	public boolean updateCal(CalendarDto dto);

	public boolean deleteCal(int seq);
	List<VisitDto> getVisitListByUser(int user_id);
	Map<String, Object> buildCalendar(Integer year, Integer month);

}