<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/registForm.css">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<div id="container">
		<div class="main">
			<div>
				<span> <%=ldto.getId()%>[<%=ldto.getRole()%>] 님이 로그인 함.
				</span> <span> <a href="adminController.jsp?command=admin_listAll">회원
						전체 조회</a>
				</span>| <span> <a
					href="userController.jsp?command=admin_userRoleForm">회원정보(등급)수정</a>
				</span>| <span> <a href="userController.jsp?command=logout">로그아웃</a>
				</span>
			</div>
		</div>
	</div>
	<h1>관리자 페이지</h1>
	<h2>
		<small>회원전체목록</small>
	</h2>
	<table class="table table-striped" border="3">
		<thead>
			<tr>
				<th scope="col">회원번호</th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">주소</th>
				<th scope="col">이메일</th>
				<th scope="col">회원등급</th>
				<th scope="col">탈퇴여부</th>
				<th scope="col">가입일</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<UserDto> list = (List<UserDto>) request.getAttribute("userList");
			if (list != null) {
				for (UserDto dto : list) {
			%>
			<tr>
				<th scope="row"><%=dto.getSeq()%></th>
				<td><%=dto.getId()%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getAddress()%></td>
				<td><%=dto.getEmail()%></td>
				<td><%=dto.getRole()%></td>
				<td><%="Y".equals(dto.getEnabled())?"가입중":"탈퇴"%></td>
				<td><%=dto.getRegDate()%></td>
			</tr>
			<%
			}
			}
			%>
		</tbody>
	</table>

</body>
</html>
<%@ include file="footer.jsp"%>