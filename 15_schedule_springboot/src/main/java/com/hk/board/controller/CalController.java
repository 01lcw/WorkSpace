package com.hk.board.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.board.command.InsertCalCommand;
import com.hk.board.dtos.CalDto;
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
	
	// localhost:9090/member/loginpage   --> /member/*
	// localhost:9090/schedule/calendar  --> /schedule/* --> login 확인 기능 실행
	@GetMapping("/calendar")
	public String calendar(Model model, HttpServletRequest request) {
		log.info("calendar페이지");
		//request(요청)객체를 전달하면, 받은쪽에서도 요청처리를 할 수 있다.
		Map<String, Integer> calMap = calService.makeCalendar(request);
		model.addAttribute("calMap", calMap);
		
		return "calboard/calendar";
	}
	
	//일정 추가 폼 이동
	@GetMapping("/addcalboardform")
	public String addCalBoardForm(Model model,
								  InsertCalCommand insertCalCommand) {
		log.info("일정추가폼이동");
		//입력폼 요청시에도 command객체를 보내야 된다.
		model.addAttribute("insertCalCommand", insertCalCommand);
		
		return "calboard/addcalboardform";
	}
	
	@PostMapping("/addcalboard")
	public String addCalBoard(@Validated InsertCalCommand insertCalCommand,
			                  BindingResult result) {
		
		//유효값 처리 결과를 받아 에러를 확인
		if(result.hasErrors()) {
			log.info("일정을 모두 입력해야 됨");
			return "calboard/addcalboardform";
		}
		
		//일정추가하기 실행 코드 작성
		//calService에 코드가 구현되어야 함.
		calService.insertCalBoard(insertCalCommand);
		
		return "redirect:/schedule/calendar?year="
								+insertCalCommand.getYear()
								+"&month="+insertCalCommand.getMonth();
	}
	
	//일정목록 보기
	@GetMapping("/calboardlist")
	//                year,month,date --> Map{"year":"2025","month":"10"..}
	public String calBoardList(@RequestParam Map<String, String>map,
			                   Model model) {
		
		String id="hk";//로그인기능있을때 세션으로부터 가져올 수 있다.
		//Service객체에 메서드 정의
		List<CalDto>list=calService.calBoardList(id, map);
		model.addAttribute("list", list);
		
		return "calboard/calboardlist";
	}
	
	//여러글 삭제하기
	// 실습: 기능 구현하기
	//    - 유효값처리: 예전 방식은 JS로 처리
	//               DeleteCalcommand객체 이용해서 validation 사용해서 처리
	//		삭제 쿼리는 다이나믹 쿼리로 작성하면 됨
	
	
	
	
}