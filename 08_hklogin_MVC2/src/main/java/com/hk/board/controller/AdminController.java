package com.hk.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.script.ScriptContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.board.daos.AdminDao;
import com.hk.board.daos.UserDao;
import com.hk.board.dtos.UserDto;



@WebServlet("*.admin")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//클라이언트에서 get방식으로 요청을 하면 실행하는 메서드

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		//session객체를 얻어오기
		//		HttpSession session = request.getSession();
		//					session.setAttribute("ldto", dto);

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

		AdminDao dao=AdminDao.getAdminDao();
	
		if(command.equals("/userlistall.admin")){
			List<UserDto>list=dao.getAllUserList();
			request.setAttribute("list", list);
			dispatch("admin_listAll.jsp", request, response);
		}else if(command.equals("/userlist.admin")){
			List<UserDto>list=dao.getUserList();
			request.setAttribute("list", list);
			dispatch("admin_listA.jsp", request, response);
		}else if(command.equals("/userrole.admin")){
			String id=request.getParameter("id");
			UserDto dto=dao.getUserRole(id);
			request.setAttribute("dto", dto);
			dispatch("admin_userRoleForm.jsp", request, response);
		}else if(command.equals("/updaterole.admin")){
			String id=request.getParameter("id");
			String role=request.getParameter("role");
			boolean isS=dao.getUpdateRole(id,role);
			if(isS){
				response.sendRedirect("userlist.admin");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
	}

	//클라이언트에서 post방식으로 요청을 하면 실행하는 메서드
	//tomcat이 요청을 받아서 doPost에 파라미터로 request, response객체를 전달해주는 개념
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); //클라이언트에서 요청X -> 서버 내부에서 호출
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
	
	public void jsResponse(String url, String msg, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String js = 
			"<script type='text/javascript'>"
		+	"	alert('"+msg+"');"
		+		"location.href='"+url+"';"
		+		"</script>";
		
		out.print(js);//브라우저로 출력한다.
	}

}