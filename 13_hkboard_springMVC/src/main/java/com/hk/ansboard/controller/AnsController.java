package com.hk.ansboard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.ansboard.dtos.AnsDto;
import com.hk.ansboard.service.IAnsService;
import com.hk.board.util.Paging;

@Controller
public class AnsController {

	@Autowired
	private IAnsService ansService;
	
	@RequestMapping(value = "/home.do"
			       ,method = RequestMethod.GET)
	public String home() {
		System.out.println("HOME 페이지로 이동");
		return "home";
	}
	
	@RequestMapping(value = "/boardlist.do"
		       ,method = RequestMethod.GET)
	public String boardList(String pnum, Model model
							,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		if(pnum==null) {//페이지번호가 없이 요청들어왔을 경우
			pnum=(String)session.getAttribute("pnum");
		}else {//새로운 페이지 요청이 들어왔을 경우
			session.setAttribute("pnum", pnum);
		}
		
		List<AnsDto>list=ansService.getAllList(pnum);
		model.addAttribute("list", list);
		
		//페이지 개수 구해서 boardlist.jsp로 보내기 위해 스코프에 담기
		int pcount=ansService.getPCount();
		model.addAttribute("pcount", pcount);
		
		//페이지에 페이징 처리 기능 추가
		//필요한 값: pcount(총페이지개수), pnum(요청 페이지번호), 페이지 범위(페이지 수)
		Map<String, Integer>map=Paging.pagingValue(pcount, pnum, 5);
		model.addAttribute("pMap", map);
	
		return "boardlist";
	}
	
	//상세보기
	@RequestMapping(value = "/boarddetail.do",method = RequestMethod.GET)
	public String getBoard(Model model, String seq, String review) {
		if(review!=null&&review.equals("y")) {
			ansService.readCount(Integer.parseInt(seq));
			return "redirect:boarddetail.do?seq="+seq;
		}else {
			AnsDto dto=ansService.getBoard(Integer.parseInt(seq));
			model.addAttribute("dto",dto);
			return "detailboard";
		}
	}
	
	// 글 추가 폼 이동
		@RequestMapping(value = "/insertboardform.do",method = RequestMethod.GET)
		public String insertBoardForm() {
			return "insertboardform";
		}
		
	// 글 추가 폼 이동
		@RequestMapping(value = "/insertboard.do",method = RequestMethod.POST)
		public String insertBoard(AnsDto dto) {
			boolean isS=ansService.insertBoard(dto);
			if(isS) {
				return "redirect:boardlist.do";
			}else {
				return "error";
			}
		}
}