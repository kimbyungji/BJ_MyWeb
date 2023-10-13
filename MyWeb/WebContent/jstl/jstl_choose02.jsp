<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>jstl_choose02.jsp</title>
	</head>
	<body>
		<!-- choose절을 이용하여 A는 90점 이상, B는 80점 이상, C는 70점 이상, 나머지는F -->
		<c:choose>
			<c:when test="${param.point>=90 }">
				A입니다.
			</c:when>
			<c:when test="${param.point>=80 }">
				B입니다.
			</c:when>
			<c:when test="${param.point>=70 }">
				C입니다.
			</c:when>
			<c:otherwise>
				F입니다.
			</c:otherwise>
		</c:choose>
	</body>
</html>