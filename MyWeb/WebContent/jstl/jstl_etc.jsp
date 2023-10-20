<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<%
	String test = "test";
%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>jstl_etc.jsp</title>
	</head>
	<body>
		<h2>remove test</h2>
		<c:set var = "test" value="<%=test %>"/>
		<c:set var="test2" value="test2"/>
		<c:remove var="test2" scope="page"/>
		${test }<br>
		${test2 }<br>
		
		<h2>forTokens test</h2>
		<!-- 자바의 StringTokenizer를 JSTL을 사용하여 구현하려 할 때 쓸 수 있음 => 문자열을 구분자로 나눌 때 사용함 -->
		<c:set var="tokens" value="안녕/하세요/지금은/JSP수업/시간입니다."/>
		<c:forTokens var="token" items="${tokens }" delims="/">
			${token }<br>
		</c:forTokens>
	</body>
</html>