<%@page import="com.hk.board.dtos.UserDto"%>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Main</title>
</head>
<body>
<div id="container">
	<div class="main">
		<div>
			<span>
			${sessionScope.ldto.id}[${sessionScope.ldto.role}]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="userinfo.board?id=${sessionScope.ldto.id}">나의 정보</a>
			</span> |
			<span>
				<a href="logout.board">로그아웃</a>
			</span>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>
