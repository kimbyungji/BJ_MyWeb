<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>jstl_choose01.jsp</title>
	</head>
	<body>
		<!-- choose의 자식으로 c:when, c:otherwise를 가질 수 있다. -->
		<c:choose>
			<c:when test="${param.name eq '홍길동' }">
				이름 : 홍길동입니다.
			</c:when>			
			<c:when test="${param.age >= 20 }">
				성인입니다.
			</c:when>
			<c:otherwise>
				홍길동도 아니고, 성인도 아닙니다.
			</c:otherwise>
			
		</c:choose>
	</body>
</html>