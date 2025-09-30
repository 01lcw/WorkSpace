<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@ include file="header.jsp"%>

	
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
				<span> ${ldto.id}[${ldto.role }] 님이 로그인 하였습니다. </span> <span>
					<a href="userlistall.admin">회원전체조회</a>
				</span>| <span> <a href="userlist.admin">회원정보[등급]수정</a>
				</span>| <span> <a href="logout.admin">로그아웃</a>
				</span>
			</div>
		</div>
		<div class="admin_content">
			<h1>관리자 페이지</h1>
			<div class="userlist">
				<table border="1" class="table  table-striped table-hover">
					<tr class="table-primary">
						<th>회원번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>주소</th>
						<th>이메일</th>
						<th>회원등급</th>
						<th>탈퇴여부</th>
						<th>가입일</th>
					</tr>

					<c:forEach var="dto" items="${list}">

						<tr>
							<td>${dto.seq}</td>
							<td>${dto.id}</td>
							<td>${dto.name}</td>
							<td>${dto.address}</td>
							<td>${dto.email}</td>
							<td>${dto.role}</td>
							<td>
							<c:choose>
								<c:when test="${dto.enabled eq 'Y'}">가입중</c:when>
								<c:otherwise>탈퇴</c:otherwise>
							</c:choose>
							</td>
							<td>${dto.regdate}</td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</div>
</body>
</html>
<%@ include file="footer.jsp"%>