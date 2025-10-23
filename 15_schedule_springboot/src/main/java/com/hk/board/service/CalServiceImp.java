package com.hk.board.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.command.InsertCalCommand;
import com.hk.board.dtos.CalDto;
import com.hk.board.mapper.CalMapper;
import com.hk.board.utils.Util;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class CalServiceImp {
	
	@Autowired
	private CalMapper calMapper;
	
	@Autowired
	private Util util;
	
	@Autowired
	private ModelMapper modelMapper;
	// 파라미터로 request객체를 전달받음 ---> 요청정보를 처리할 수 있는 환경
	public Map<String, Integer> makeCalendar(HttpServletRequest request) {
		String paramYear=request.getParameter("year");
		String paramMonth=request.getParameter("month");
		
		//달력에 필요한 값을 구하기 위해 calendar객체가 필요함
		Calendar cal=Calendar.getInstance();
		
		//paramYear등의 값이 null이 아니면 사용자가 원하는 달을 요청
		//이외에 요청은 현재 날짜로 처리
		int year=(paramYear==null)?cal.get(Calendar.YEAR):Integer.parseInt(paramYear);
		
		int month=(paramMonth==null)?cal.get(Calendar.MONTH)+1:Integer.parseInt(paramMonth);
		
		//문제점: 월을 이동할 때 11, 12, 13, 14... 또는 2, 1, 0, -1
		if(month>12) {
			month=1;
			year++;
		}
		if(month<1) {
			month=12;
			year--;
		}
		//1. 해당 월의 1일에 대한 요일을 구하기
		//  - 1~7숫자를 반환: 1은 일요일 ~ 7은 토요일
		cal.set(year, month-1, 1); //월:0~11월
		int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
		
		//2. 월의 마지막 날 구하기
		int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//달력 구현에 필요한 값을 map 저장
		Map<String, Integer>map = new HashMap<>();
		map.put("year", year);
		map.put("month", month);
		map.put("dayOfWeek", dayOfWeek);
		map.put("lastDay", lastDay);
		
		return map;
	}
	// 일정 추가하기
	public boolean insertCalBoard(InsertCalCommand insertCalCommand) {
		//command에는 id, title, content, y,m,d,h,m <- DB와 일치X
		//ymdhm --> mdate로 변환 작업
		//command --> dto로 데이터 옮기기
		
		//"202510230939" --> mdate로 저장
		String mdate = insertCalCommand.getYear()
							+util.isTwo(insertCalCommand.getMonth()+"")
							+util.isTwo(insertCalCommand.getDate()+"")
							+util.isTwo(insertCalCommand.getHour()+"")
							+util.isTwo(insertCalCommand.getMin()+"");
		
		//command -> dto로 값을 복사해서 넣는 작업
//		CalDto dto=new CalDto();
//		dto.setId(insertCalCommand.getId());
//		dto.setTitle(insertCalCommand.getTitle());
//		dto.setContent(insertCalCommand.getContent());
//		dto.setMdate(mdate);
								 //(복사할 대상 객체, 붙여넣을 객체의 타입)
		CalDto dto=modelMapper.map(insertCalCommand, CalDto.class);
		dto.setMdate(mdate); //맴버필드 불일치일 경우는 따로 저장
		int count=calMapper.insertCalBoard(dto);
		
		
		return count>0?true:false;
	}
	
	//일정목록보기
	public List<CalDto> calBoardList(String id,Map<String, String>paramMap){
		//조회할 일정의 날짜 8자리 만들어주기-> "9" -> 09 -> "20250910"
		String yyyyMMdd =paramMap.get("year")
						+util.isTwo(paramMap.get("month"))
						+util.isTwo(paramMap.get("date"));
		
		//쿼리에 파라미터로 전달할 map 객체 생성
		Map<String, String>map=new HashMap<>();
		map.put("id", id);
		map.put("yyyyMMdd", yyyyMMdd);
		return calMapper.calBoardList(map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
