<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>헤더템플릿</title>
</head>
<body>

    	<header id="header" class="dFlex"> 	<!-- 로고, GNB -->
    		<div id="headerLogo">
    			<a href="<c:url value='/'/>">
    				<img src="/resources/images/headerLogo.png" alt="헤더로고이미지">
    			</a>
    		</div>
    		<nav id="gnb">
    	
    			<ul id="mainMenu" class="dFlex">
    		 
    			<c:choose>
    				<c:when test="${sessionScope.uId == null }">
	    				<li class="mainLi"><a href="<c:url value='/'/>">HOME</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/login'/>">로그인</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href=" /joinAgreement">회원가입</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/noticelist?gnbParam=bbs'/>">게시판</a></li>
    				</c:when>
    				<c:when test="${sessionScope.uId eq 'admin' || sessionScope.uId eq 'adminSub'}">
	    				<li class="mainLi"><a href="<c:url value='/'/>">HOME</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/logout'/>">로그아웃</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/adminPage?gnbParam=adminPage'/>" style="font-size:16px;">관리자 페이지</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/noticelist?gnbParam=bbs'/>">게시판</a></li>
    				</c:when>
    				<c:otherwise>
	    				<li class="mainLi"><a href="<c:url value='/'/>">HOME</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/logout'/>">로그아웃</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/myPage?gnbParam=myPage'/>">마이페이지</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/noticelist?gnbParam=bbs'/>">게시판</a></li>
    				</c:otherwise>
    				
    			</c:choose>
    		
    			</ul>
    			
    		</nav>
    	</header>
    	<!--  header#header  -->
    	<hr class="sepLine">
    	
</body>
</html>