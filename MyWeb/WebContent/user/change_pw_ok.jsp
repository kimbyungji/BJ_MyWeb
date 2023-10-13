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
    	1. 폼값 처리
    	2. login(id, 예쩐 비밀번호-old_pw) 아이디와 비밀번호가 맞는지 확인 - 인증
    		예전비밀번호가 틀린 경우 뒤로가기
    	3. 일치하면 changePassword(??)를 실행 DAO 객체에 생성
    	4. changePassword 메서드 실행 결과
    		성공하면, "비밀번호 변경처리 되었습니다." 출력 후 mypage로 이동
    		실패하면, "비밀번호 변경처리 실패했습니다." 출력 후 mypage로 이동
    	*/
    	
    	/*------------------------------------------------------
    	String id = (String)session.getAttribute("user_id");
    	String old_pw = request.getParameter("old_pw");
    	String new_pw = request.getParameter("new_pw");
    	
    	UserDAO dao = UserDAO.getInstance();
    	String pw = dao.get_pw(id);
    	
    	if(id!=null&&old_pw.equals(pw)){
    		
    		int result = dao.change(id, new_pw);
    		
    		if(result==0){%>
    		<script>
    			
    		</script>
    			<%response.sendRedirect("/MyWeb/user/mypage.jsp");
    		}else{%>
    		<script>
    			alert("비밀번호 변경처리 성공했습니다.");
    		</script>
    			<%response.sendRedirect("/MyWeb/user/mypage.jsp");
    		}
    	}
    	else{
    		System.out.print("pw"+pw);
    	}
    	-------------------------------------------------------*/
    	
    	request.setCharacterEncoding("utf-8");
    	String oldPw = request.getParameter("old_pw");
    	String newPw = request.getParameter("new_pw");
    	String id = (String)session.getAttribute("user_id");
    	
    	// DAO 객체 생성
    	UserDAO dao = UserDAO.getInstance();
    	
    	int result = dao.login(id, oldPw);
    	
    	if(result==1){	// 인증 통과
    		int result2 = dao.changePassword(id,newPw);
    		if(result2 ==1){%>
    			<script>
    				alert("비밀번호 변경처리 성공했습니다.");
    				location.href="/MyWeb/user/mypage.jsp";
    			</script>
    		<%}else{%>
    			<script>
    				alert("비밀번호 변경처리 실패했습니다.");
    				location.href="/MyWeb/user/mypage.jsp";
    			</script>
    		<%}
    	}else{	// 인증 통과 x%>
    		<script>
    			history.go(-1);
    		</script>
    	<%}%>  	   