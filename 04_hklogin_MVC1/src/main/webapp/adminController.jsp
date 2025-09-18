<%@page import="com.hk.dtos.UserDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.AdminDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	/* 만들어 줄 페이지
	admin_main.jsp
	admin_listAll.jsp
	admin_list.jsp
	admin_userRoleForm.jsp : 회원상세조회(등급수정폼 포함)
	*/
	String command=request.getParameter("command");

	AdminDao dao=AdminDao.getAdminDao();
	
	if("admin_listAll".equals(command)) {
	    List<UserDto> list = dao.getAllUserList();  // DAO에서 데이터 조회
	    request.setAttribute("userList", list);     // JSP로 전달
	    RequestDispatcher rd = request.getRequestDispatcher("admin_listAll.jsp");
	    rd.forward(request, response);              // forward 사용
	}
	
%>
</body>
</html>