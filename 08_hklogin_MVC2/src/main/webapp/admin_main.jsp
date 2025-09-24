<%@ include file="header.jsp" %>
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
<div id="container">
	<div class="main">
		<div>
			<span>
			${ldto.id}[${ldto.role }]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="userlistall.board">회원전체조회</a>
			</span>|
			<span>
				<a href="userlist.board">회원정보[등급]수정</a>
			</span>|
			<span>
				<a href="logout.board">로그아웃</a>
			</span>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>