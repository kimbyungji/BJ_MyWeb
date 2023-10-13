<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	//System.out.println(id+pw);
	
	/*
	
	1.DAO를 통해서 executeQuery() 메서드를 이용한 결과값 존재 여부 확인
	2. 결과 값이 있다면, 성공으로 1을 결과 값이 없다면 실패로 0을 반환
	3. 실패인 경우에는 경고창을 출력한 후에 뒤로가기
	   성공인 경우에는 mypage.jsp로 이동 이 때 세션을 포함
	4. 세션에는 "user_id":id "user_name":name을 포함 한다.
	*/
	//dao 객체 생성
	UserDAO dao = UserDAO.getInstance();
	int result = dao.login(id, pw);
	
	if(result == 0) {//로그인 실패 %>
		<script>
		 alert("아이디 또는 비밀번호가 잘못되었습니다.");
		 history.go(-1);
		</script>
		<!--  // 세션 객체 생성
		session.setAttribute("user_id", id); // 세션 객체에 user_id에 id값을 저장
		session.setAttribute("user_name", name); // 세션 객체에 user_id에 id값을 저장
		//로그인시 보여줄 페이지로 전환
		response.sendRedirect("mypage.jsp"); -->
	<%}else {//성공
		//로그인 성공시 회원정보를 얻어오는 작업
		UserVO vo = dao.getUserInfo(id);
		String name = vo.getName();
		// 아이디와 이름을 세션에 넣기
		session.setAttribute("user_id", id); // 세션 객체에 user_id에 id값을 저장
		session.setAttribute("user_name", name); // 세션 객체에 user_id에 id값을 저장	
		// 이동
		response.sendRedirect("mypage.jsp");
		
	%>
		
	<%}
	
	
%>