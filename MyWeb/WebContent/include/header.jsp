<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-idth, initial-scale=1">
   

    <title>Welcome to MyWorld</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath }/css/business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">
	
	 <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 2000 //changes the speed
    })
    </script>

    
    
</head>

<body>
	<!-- header -->
	<div class="brand">My Web</div>        
    <div class="address-bar">Welcome to MyWorld</div>
		
    
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
                <a class="navbar-brand" href="/hong">My First Web</a>
            </div>
           
           
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	
                    <li>
                        <a href="index.do">HOME</a>
                    </li>
                    <li>
                        <a href="/MyWeb/member.do">Member</a>
                    </li>
                    <li>
                        <a href="/MyWeb/board/list.board">BOARD</a>
                    </li>
                    
                    
                    
                    <c:choose>
                    <c:when test="${sessionScope.user_id != null }">                  
                    	<li>
                    		<a href="${pageContext.request.contextPath }/user/logout_ok.jsp">LOGOUT</a>
                    	</li>
                    	<li>
                    		<a href="${pageContext.request.contextPath }/user/mypage.jsp">MYPAGE</a>
                    	</li>	
                    </c:when>
                    
                    <c:otherwise>
                    	<li>
                        	<a href="${pageContext.request.contextPath }/user/login.jsp">LOGIN</a>
                    	</li>
                    	<li>
                       	 	<a href="${pageContext.request.contextPath }/user/join.jsp" style="color:red">JOIN</a>
                   	 	</li>
                    </c:otherwise>
                    
                    </c:choose>
                    
                    <%-- 
                    <%if(session.getAttribute("user_id")!=null){%>
                    		  
                    	<li>
                    		<a href="/MyWeb/user/logout_ok.jsp">LOGOUT</a>
                    		
                    	</li>
                    	<li>
                    		<a href="/MyWeb/user/mypage.jsp">MYPAGE</a>
                    	</li>
                    <%}else{%>
                    	<li>
                        	<a href="/MyWeb/user/login.jsp">LOGIN</a>
                    	</li>
                    	<li>
                       	 	<a href="/MyWeb/user/join.jsp" style="color:red">JOIN</a>
                   	 	</li>
                    	<%}%>
                  --%>
                </ul>
            </div>
            
            
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
 	<!-- end header -->