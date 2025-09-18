<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
</head>
<body>

<div id="container">
	<div class="main">
		<div>
			<span>
			<%=ldto.getId()%>[<%=ldto.getRole()%>]
			님이 로그인 함.
			</span>
			<span>
				<a href="adminController.jsp?command=admin_listAll">회원 전체 조회</a>
			</span>|
			<span>
				<a href="userController.jsp?command=admin_userRoleForm">회원정보(등급)수정</a>
			</span>|
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
	</div>
</div>
<h1>관리자 페이지</h1>
</body>
</html>
<%@ include file="footer.jsp" %>