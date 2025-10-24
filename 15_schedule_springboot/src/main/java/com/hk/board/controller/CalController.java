package com.hk.board.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
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

import com.hk.board.command.DeleteCalCommand;
import com.hk.board.command.InsertCalCommand;
import com.hk.board.command.UpdateCalCommand;
import com.hk.board.dtos.CalDto;
import com.hk.board.service.CalServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

	@Autowired
	private ModelMapper modelMapper;
	
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
		
		//현재시간구하기
		LocalTime now = LocalTime.now();
        model.addAttribute("now", now);
        
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
			                   Model model,
			                   HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		if(map.get("year")==null) {
			//schedule/calboardlist 요청했을 경우 
			// 세션에 저장된 ymd를 가져와서 쓰겠다.
			map=(Map<String,String>)session.getAttribute("ymd");
		}else {
			//schedule/calboardlist?year=2025&month=10&date=24
			//ymd 파라미터를 전송한 경우 세션에 저장
			session.setAttribute("ymd", map);
		}
		
		String id="hk";//로그인기능있을때 세션으로부터 가져올 수 있다.
		//Service객체에 메서드 정의
		List<CalDto>list=calService.calBoardList(id, map);
		model.addAttribute("list", list);
		//일정목록 페이지에서 유효값처리를 위한 deleteCalCommand를 정의했기때문에
		//해당 객체를 전달해야 오류가 안남
		model.addAttribute("deleteCalCommand", new DeleteCalCommand());
		
		return "calboard/calboardlist";
	}
	
	//여러글 삭제하기
	// 실습: 기능 구현하기
	//    - 유효값처리: 예전 방식은 JS로 처리
	//               DeleteCalcommand객체 이용해서 validation 사용해서 처리
	//    - 삭제 쿼리는 다이나믹 쿼리로 작성하면 됨
	@PostMapping("/calmuldel")
	public String calMulDel(@Validated DeleteCalCommand deleteCalCommand,
			                BindingResult result,
			                Model model,
			                HttpServletRequest request) {
		
		if(result.hasErrors()) {
			log.info("여러글 삭제시 오류가 발생");
			
			String id="hk";
			//ymd값이 없기때문에 session에서 가져옴
			HttpSession session=request.getSession();
			Map<String,String>map=
			(Map<String,String>)session.getAttribute("ymd");
			
			List<CalDto>list=calService.calBoardList(id,map);
			model.addAttribute("list", list);
			return "calboard/calboardlist";
		}
		
		calService.calMulDel(deleteCalCommand.getSeq());
		
		return "redirect:/schedule/calboardlist";
	}
	
	// 일정상세보기: 파라미터 seq전달받음
	@GetMapping("/calboarddetail")
	public String calboardDetail(UpdateCalCommand updateCalCommand,
								Model model) {
		CalDto dto=calService.calBoardDetail(updateCalCommand.getSeq());
		
		//dto --> command 값 이동
//		updateCalCommand.setSeq(dto.getSeq());
//		updateCalCommand.setId(dto.getId());// <--id 추가
//		updateCalCommand.setTitle(dto.getTitle());
//		updateCalCommand.setContent(dto.getContent());
//		updateCalCommand.setYear(Integer.parseInt(dto.getMdate().substring(0, 4)));
//		updateCalCommand.setMonth(Integer.parseInt(dto.getMdate().substring(4, 6)));
//		updateCalCommand.setDate(Integer.parseInt(dto.getMdate().substring(6, 8)));
//		updateCalCommand.setHour(Integer.parseInt(dto.getMdate().substring(8, 10)));
//		updateCalCommand.setMin(Integer.parseInt(dto.getMdate().substring(10)));
		
		//modelMapper를 이용한 값 복사 자동화
		updateCalCommand=modelMapper.map(dto, UpdateCalCommand.class);
		//필드끼리 매칭되지 않는 값들은 직접 처리해준다.
		updateCalCommand.mdateToYMDHM(dto.getMdate());
		
		model.addAttribute("updateCalCommand", updateCalCommand);
		return "calboard/calboarddetail";
	}
	
	//수정하기
	@PostMapping("/calboardupdate")
	public String calBoardUpdate(@Validated UpdateCalCommand updateCalCommand,
			   				    BindingResult result) {
		if(result.hasErrors()) {
			log.info("수정오류");
			
			return "calboard/calboarddetail";
		}
		
		calService.calBoardUpdate(updateCalCommand);
		
		return "redirect:/schedule/calboarddetail?seq="
		        +updateCalCommand.getSeq();
	}
}