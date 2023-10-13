<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
	// url 주소를 통한 강제 접근을 막기
	if(session.getAttribute("user_id")==null){
		response.sendRedirect("login.jsp");
	}
	
	String id = (String)session.getAttribute("user_id");
	String name = (String)session.getAttribute("user_name");
%>
	<section>
		<div align="center">
			<h2>MyPage</h2>
			<hr>
			<%=id %>(<%=name %>) 님의 정보를 관리합니다.
			<hr>
			<input type="button" value="비밀번호 변경" class="btn btn-default" onclick="location.href='change_pw.jsp'">
			<input type="button" value="회원정보 변경" class="btn btn-primary" onclick="location.href='update.jsp'">
			<input type="button" value="회원탈퇴" class = "btn btn-info" onclick="location.href='delete_check.jsp'">
		</div>
	</section>
	
<%@ include file="/include/footer.jsp"%>