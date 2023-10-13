<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	session.getAttribute("user_id");
    	session.invalidate();
    	response.sendRedirect("/MyWeb/user/login.jsp");
    %>