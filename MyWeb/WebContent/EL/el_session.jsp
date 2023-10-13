<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("id", "kkk123");	

	UserVO vo = new UserVO();
	vo.setId("hhh123");
	vo.setName("홍길동");
	vo.setEmail("kkk123@naver.com");
	vo.setAddress("산골짜기");
	
	session.setAttribute("vo", vo);	// 리퀘스트에 강제저장
	
	application.setAttribute("admin", "홍길자");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>el_session.jsp</title>
	</head>
	<body>
		<a href="el_session_ok.jsp">세션값 확인</a>
	</body>
</html>