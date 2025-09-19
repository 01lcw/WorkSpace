<%@page import="com.hk.board.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
p
request.setCharacterEncoding("utf-8")
%>
<%
p
response.setContentType("text/html;charset=UTF-8")
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
p
  //삭제할 게시글의 seq값을 받는다.
	String sseq=request.getParameter("seq");
	int seq=Integer.parseInt(sseq);
	
	HkDao dao=new HkDao();
	boolean isS=dao.deleteBoard(seq);
	
	if(isS){
%>
		<script type="text/javascript">
			alert("글을 삭제합니다.");
			location.href="boardlist.jsp";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("글삭제 실패.");
			location.href="error.jsp";
		</script>
		<%
	}
%>
</body>
</html>