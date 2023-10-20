<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>jstl_redirect.jsp</title>
	</head>
	<body>
		<c:redirect url="jstl_etc2.jsp">
			<c:param name="abc" value="redirect 테스트"/>
			<c:param name="abc2" value="c:url로 넘겨주는 값 입니다."/>
		</c:redirect>
	</body>
</html>