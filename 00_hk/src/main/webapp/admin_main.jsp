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
		<span>메뉴 구성: </span>
			<span>
			<%=ldto.getTID()%> [<%=ldto.getTROLE()%>]
			</span> |
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span> |
			<span>
				<a href="userController.jsp?command=userinfo&id=<%=ldto.getTID()%>">나의 정보</a>
			</span> |
			<span>
				<a href="userController.jsp?command=boardList">게시판</a>
			</span>
			
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>