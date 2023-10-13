<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UserVO vo = new UserVO();
	vo.setId("hhh123");
	vo.setName("홍길동");
	vo.setAddress("산골짜기");
	
	request.setAttribute("vo", vo);	// 리퀘스트에 강제저장
	
	// 포워드 이동
	RequestDispatcher dp = request.getRequestDispatcher("el_request_ok.jsp");
	dp.forward(request, response);
%>