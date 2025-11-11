package com.family.calendar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.calendar.command.InsertCalCommand;
import com.family.calendar.dtos.CalendarDto;
import com.family.calendar.dtos.VisitDto;
import com.family.calendar.mapper.CalMapper;

@Service
public class CalServiceImp implements CalService {

	@Autowired
	private CalMapper mapper;

	@Override
	public boolean insertCalBoard(InsertCalCommand insertCommand) {

		CalendarDto dto = new CalendarDto();
		dto.setId(insertCommand.getId());
		dto.setTitle(insertCommand.getTitle());
		dto.setContent(insertCommand.getContent());
		dto.setMdate(insertCommand.getMdate());

		return mapper.insertCal(dto);
	}

	@Override
	public List<CalendarDto> getAllCals() {
		return mapper.getCalList();
	}

	@Override
	public CalendarDto getCalDetail(int seq) {
		return mapper.getCalDetail(seq);
	}

	@Override
	public boolean updateCal(CalendarDto dto) {
		return mapper.updateCal(dto);
	}

	@Override
	public boolean deleteCal(int seq) {
		return mapper.deleteCal(seq);
	}
	@Override
	public List<VisitDto> getVisitListByUser(int user_id) {
	    return mapper.getVisitListByUser(user_id);
	}
	@Override
    public Map<String, Object> buildCalendar(Integer year, Integer month) {
        return Map.of("year", year, "month", month);
    }

}