package com.hk.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.board.daos.HkDao;
import com.hk.board.dtos.HkDto;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//클라이언트에서 get방식으로 요청을 하면 실행하는 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		//--> 인코딩처리: filter에서 구현함
		
		//command 구현(요청 구분 값을 받기)
		// --> command=boardlist 파라미터값을 
		//     추가적으로 전달해야 되는 불필요한 작업
		// getRequestURI() : 주소의 ?전까지 구해줌
		// 컨트롤러 요청할때 boardlist.board 요청
//		request.getRequestURI();// 요청 주소를 얻어옴
		String requestURI=request.getRequestURI();
		StringBuffer requestURL=request.getRequestURL();
		String contextPath=request.getContextPath();
		String pathInfo=request.getPathInfo();
		System.out.println(requestURI+"\n"
						 + requestURL+"\n"
						 + contextPath+"\n"
						 + pathInfo);
		
		//command 값 구하기 --> "/boardlist.board" 추출
		String command=requestURI.substring(contextPath.length());
		System.out.println("요청Command:"+command);
		
		HkDao dao=new HkDao();
		
		if(command.equals("/boardlist.board")) {
			List<HkDto> list=dao.getAllList();
			request.setAttribute("list", list);
			
//			pageContext.forward("boardlist.jsp");
//			request.getRequestDispatcher("boardlist.jsp")
//			       .forward(request, response);
			dispatch("boardlist.jsp", request, response);
		}else if(command.equals("/insertboardform.board")) {
			response.sendRedirect("insertboardform.jsp");
		}else if(command.equals("/insertboard.board")) {
			//id, title, content
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			boolean isS=dao.insertBoard(new HkDto(id,title,content));
			if(isS){
				//새로 다시 요청을 해서 응답하기 때문에 주소창이 업데이트 된다.
				response.sendRedirect("boardlist.board");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
	}

	//클라이언트에서 post방식으로 요청을 하면 실행하는 메서드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
//		test(request);
	}
	
	public void test(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
	}
	
	//forward 기능 구현
	public void dispatch(String url,
						 HttpServletRequest request, 
			             HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url)
	       .forward(request, response);
	}

}