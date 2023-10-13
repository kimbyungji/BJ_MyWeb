<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>el_request_ok.jsp</title>
	</head>
	<body>
		${requestScope.vo.id }<br>
		${requestScope.vo.name }<br>
		${requestScope.vo.address }<br>
	</body>
</html>