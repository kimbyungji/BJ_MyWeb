<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	if(session.getAttribute("user_id")==null){
		response.sendRedirect("/user/login.jsp");
	}
%>

<%
	/*
		1. 폼 값 받기
		2. DAO연동을 통해서 update메서드를 호출해서 회원 정보 수정
		3. 매개변수의 값은 VO로 전달
		4. 수정 성공시, "회원정보가 수정되었습니다.", 출력 후 마이페이지로 이동(단, user_name은 세션 변경)
			수정 실패시, "회워정보 수정에 실패했습니다.",출력 후 마이페이지로 이동
	*/
	
	request.setCharacterEncoding("utf-8");
	String id = (String)session.getAttribute("user_id");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	
	UserDAO dao = UserDAO.getInstance();
	UserVO vo = new UserVO(id, null, name, email, address, null);
	
	int result = dao.update(vo);
	
	if(result ==1){
		session.setAttribute("user_name", name);%>
		<script>
			alert("회원정보가 수정되었습니다.");
			location.href="/MyWeb/user/mypage.jsp";
		</script>	
	<%}else{%>
			<script>
			alert("회원정보 수정에 실패하였습니다..");
			location.href="/MyWeb/user/mypage.jsp";
		</script>	
	<%}
%>