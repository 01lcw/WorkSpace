package com.family.calendar.service;

import java.util.List;

import com.family.calendar.command.InsertCalCommand;
import com.family.calendar.dtos.CalendarDto;

public interface CalService {

	public boolean insertCalBoard(InsertCalCommand insertCommand);

	public List<CalendarDto> getAllCals();

	public CalendarDto getCalDetail(int seq);

	public boolean updateCal(CalendarDto dto);

	public boolean deleteCal(int seq);
}
