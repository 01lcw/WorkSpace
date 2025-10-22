package com.hk.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.board.command.InsertCalCommand;
import com.hk.board.service.CalServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/schedule")
public class CalController {
	//  boardlist.do , boarddetail.do...
	// board/boardlist.do , board/boarddetail.do
	// board/* --> 로그인확인
	
	@Autowired
	private CalServiceImp calService;
	
	// localhost:9090/schedule/
	@GetMapping("/")
	public String home() {
		log.info("index페이지");
		return "index";
	}
	
	// localhost:9090/schedule/calendar --> /schedule/* --> login 확인 기능 실행
	// localhost:9090/member/loginpage --> /member/*
	@GetMapping("/calendar")
	public String calendar(Model model, HttpServletRequest request) {
		System.out.println("calendar페이지");
		//request(요청)객체를 전달하면, 받은 쪽에서도 요청처리를 할 수 있다.
		Map<String, Integer> calMap=calService.makeCalendar(request);
		model.addAttribute("calMap", calMap);
		return "calboard/calendar";
	}
	
	//일정 추가 폼 이동
	@GetMapping("/addcalboardform")
	public String addCalBoardForm(Model model, InsertCalCommand insertCalCommand) {
		log.info("일정추가폼이동");
		//입력 폼 요청시에도 command객체를 보내야 된다.
		model.addAttribute("insertCalCommand", insertCalCommand);
		return "calboard/addcalboardform";
	}
	
	@PostMapping("/addcalboard")
	public String addCalBoard(@Validated InsertCalCommand insertCalCommand, BindingResult result) {
		
		//유효값 처리 결과를 받아 에러를 확인
		if(result.hasErrors()) {
			log.info("일정을 모두 입력해야 됨");
			return "calboard/addcalboardform";
		}
		
		//일정 추가하기 실행 코드 작성
		//calService에 코드가 구현되어야 함.
		
		return"redirect:/schedule/calendar?year="+insertCalCommand.getYear()+"&month="+insertCalCommand.getMonth();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}