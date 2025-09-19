<%@page import="com.hk.board.dtos.UserDto"%>
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
<style type="text/css">
/* 	.main{ */
/* 		padding:10px; */
/* 		font-size: 15pt; */
/* 	} */
/* 	.main a{ */
/* 		text-decoration: none; */
/* 	}     */
</style>
</head>
<body>
<%-- <% UserDto ldto=(UserDto)session.getAttribute("ldto"); %> --%>
<div id="container">
	<div class="main">
		<div>
			<span>
			로그인 아이디: 
			<%=ldto.getTID()%> [<%=ldto.getTROLE()%>]
			</span>
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span> |
			<span>
				<a href="userController.jsp?command=userinfo&id=<%=ldto.getTID()%>">나의 정보</a>
			</span> |
			<span>
				<a href="userController.jsp?command=boardlist">게시판</a>
			</span>
			
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>