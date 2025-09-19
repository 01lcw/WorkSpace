<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.daos.HkDao"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.hk.board.dtos.UserDto"%>
<%@page import="com.hk.board.daos.UserDao"%>
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

	//요청값을 받기
	String command=request.getParameter("command");

	//싱글톤 패턴 : 클래스명.메서드 (static메서드 호출 방법)
	UserDao dao=UserDao.getUserDao();
	HkDao hkdao =new HkDao();

	//어떤 요청인지 확인-> 해당코드를 실행
	if(command.equals("registform")){//회원가입폼이동
		response.sendRedirect("registform.jsp");
	}else if(command.equals("insertUser")){//회원가입하기
		//회원정보 파라미터 받기
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
// 		String address=request.getParameter("address");

		//주소 API 활용: 파라미터 받기 ----------------------
		String addr1=request.getParameter("addr1");//우편번호
		String addr2=request.getParameter("addr2");//기본주소
		String addr3=request.getParameter("addr3");//상세주소
		String addr4=request.getParameter("addr4");//참고항목
		String address=addr1+" "+addr2+" "+addr3+" "+addr4;
		//주소 API 활용 종료-------------------------------
		
		String email=request.getParameter("email");
		
		boolean isS = dao.insertUser(new UserDto(id,name,password,address,phone,email));
		if(isS){
			%>
			<script type="text/javascript">
				alert("회원에 가입이 되셨습니다.");
				location.href="index.jsp";
			</script>
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("회원가입실패");
				location.href="error.jsp";
			</script>
			<%
		}
	}else if(command.equals("idchk")){//id체크하기
		String id=request.getParameter("id");
		String resultId=dao.idCheck(id);//결과값이 null이면 사용가능
		
		request.setAttribute("resultId", resultId);
		pageContext.forward("idchkform.jsp"); 
	}else if(command.equals("login")){
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		//id와 pw에 해당하는 회원정보 조회
		UserDto dto=dao.getLogin(id, password);
		
		if(dto==null||dto.getTID()==null){//회원이 존재하지 않는다면
			response.sendRedirect("index.jsp?msg="
			      +URLEncoder.encode("회원가입을 해주세요","utf-8"));
		}else{
			//회원이라면!
			//sessionScope객체에 로그인 정보를 저장
			session.setAttribute("ldto", dto);
			session.setMaxInactiveInterval(10*60);//10분간 유지
			
			//등급[ADMIN,MANAGER,USER]을 확인해서 해당 Main페이지로 이동하기
			if(dto.getTROLE().toUpperCase().equals("ADMIN")){
				response.sendRedirect("admin_main.jsp");
			}else if(dto.getTROLE().toUpperCase().equals("MANAGER")){
				response.sendRedirect("manager_main.jsp");
			}else if(dto.getTROLE().toUpperCase().equals("USER")){
				response.sendRedirect("user_main.jsp");
			}
		}
	}else if(command.equals("logout")){//로그아웃하기
		//session의 로그인 정보를 삭제한다.
// 		session.removeAttribute("ldto");// ldto만 삭제
		session.invalidate();//session의 모든 정보 삭제
		response.sendRedirect("index.jsp");
	}else if(command.equals("userinfo")){//회원상세조회
		//로그인할때 select문 모든 정보조회로 구현
		// --> 나의정보 조회할때도 활용하는 경우가 있음
		//    --> sessoin에 저장하고 그 정보를 사용 --> 이렇게 사용하면 안됨
		
		String id=request.getParameter("id");
		UserDto dto=dao.getUser(id);
		
		//객체(dto)를 스코프객체에 저장하고
		request.setAttribute("dto", dto);
		//이동한다.
		pageContext.forward("userinfo.jsp");
	}else if(command.equals("userupdate")){
		String id=request.getParameter("id");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		
		boolean isS=dao.updateUser(new UserDto(id,address,email));
		
		if(isS){
			%>
			<script type="text/javascript">
				alert("수정완료");
				location.href
				="userController.jsp?command=userinfo&id=<%=id%>";
			</script>
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("수정실패");
				location.href
				="error.jsp";
			</script>
			<%
		}
	}else if(command.equals("deluser")){
		String id=request.getParameter("id");
		
		boolean isS=dao.delUser(id);
		session.invalidate();//세션 삭제
		if(isS){
			%>
			<script type="text/javascript">
				alert("회원 탈퇴를 완료하였습니다.");
				location.href
				="index.jsp";
			</script>
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("회원탈퇴실패");
				location.href
				="error.jsp";
			</script>
			<%
		}
	}
//  게시판 기능 -------------------------------------------------------------아 그냥 따로 만들걸	
	else if(command.equals("boardlist")){
		List<HkDto> list= hkdao.getAllList();//DB로부터 글목록 데이터 가져오기
		
		//6단계:Scope 객체에 담기
		//request스코프: 객체 전달범위 
		// 요청페이지 ----> 응답페이지
		request.setAttribute("list", list);//["list":list]
		
		//7단계: 페이지 응답(이동)
		pageContext.forward("boardlist.jsp");
	}else if(command.equals("insertboardform")){//글쓰기폼요청
		response.sendRedirect("insertboardform.jsp");
	}else if(command.equals("insertboard")){//글추가하기
		//id, title, content
		String Tid=request.getParameter("id");
		String Ttitle=request.getParameter("title");
		String Tcontent=request.getParameter("content");
		
		boolean isS=hkdao.insertBoard(new HkDto(Tid,Ttitle,Tcontent));
		if(isS){
	//새로 다시 요청을 해서 응답하기 때문에 주소창이 업데이트 된다.
	response.sendRedirect("userController.jsp?command=boardlist");
	//글추가할때 요청 주소가 남아있어서 새로고침하면 글이 계속 추가된다.
// 			pageContext.forward("boardController.jsp?command=boardlist");
		}else{
	response.sendRedirect("error.jsp");
		}
	}else if(command.equals("boarddetail")){//상세보기
		//전달된 파라미터 받기
		String Tsseq=request.getParameter("seq");
		int Tseq=Integer.parseInt(Tsseq);//"5"->정수 5 변환
		
		HkDto dto=hkdao.getBoard(Tseq);//db에서 글하나에 대한 정보가져오기
		//dto객체를 boarddeatil.jsp로 전달해야 함
		request.setAttribute("dto", dto);
		pageContext.forward("boarddetail.jsp");
	}else if(command.equals("boardupdateform")){
		//수정폼 이동
		//전달된 파라미터 받기
		String Tsseq=request.getParameter("seq");
		int Tseq=Integer.parseInt(Tsseq);//"5"->정수 5 변환
		
		HkDto dto=hkdao.getBoard(Tseq);//db에서 글하나에 대한 정보가져오기
		//dto객체를 boardupdateform.jsp로 전달해야 함
		request.setAttribute("dto", dto);
		pageContext.forward("boardupdateform.jsp");
	}else if(command.equals("boardupdate")){
		//수정하기
		String Tsseq=request.getParameter("seq");
		int Tseq=Integer.parseInt(Tsseq);
		String Ttitle=request.getParameter("title");
		String Tcontent=request.getParameter("content");
		
		boolean isS=hkdao.updateBoard(new HkDto(Tseq,Ttitle,Tcontent));
		if(isS){
	response.sendRedirect("userController.jsp?"
	             +"command=boarddetail&seq="+Tseq);
		}else{
	response.sendRedirect("error.jsp");
		}
	}else if(command.equals("boarddelete")){
		//삭제하기
		String Tsseq=request.getParameter("seq");
		int Tseq=Integer.parseInt(Tsseq);
		boolean isS=hkdao.deleteBoard(Tseq);
		if(isS){
	response.sendRedirect("userController.jsp?command=boardlist");
		}else{
	response.sendRedirect("error.jsp");
		}
		
		
	}else if(command.equals("muldel")){
		//여러글 삭제
		// 전달받는 파라미터: 동일한 name으로 여러개의 값이 전달되는 상황
	    //   url -> boardController.jsp?seq=5,6,7,8
// 		request.getParameter("seq");// 해당 name에 대한 값 1개를 받는다
		String[] seqs=request.getParameterValues("seq");//여러개의 값을 받아 배열로 반환
		boolean isS=hkdao.mulDel(seqs);
		if(isS){
	response.sendRedirect("userController.jsp?command=boardlist");
		}else{
	response.sendRedirect("error.jsp");
		}
	}else if(command.equals("search")){ //아이디 검색
		String tid = request.getParameter("tid");
		List<HkDto> list = hkdao.getSearchBoard(tid);
        request.setAttribute("list", list);
        pageContext.forward("boardlist.jsp");
	}
%>
</body>
</html>