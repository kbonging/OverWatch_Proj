<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인영역 LNB 메뉴</title>
</head>
<body>

	<nav id="mainLNB">
		<ul id="lnbMainMenu">
			<!-- 
			when1 : 게시판 클릭
			when2 : 로그인 하지 않았을때
			when3 : 마이페이지 클릭
			when4 : 관리자 페이지 클릭
			otherwise : 로그인 했을때 
			-->
    			
    		<c:choose>
    				<c:when test='${map.gnbParam.equals("bbs")}'>
   					<li class="lnbMainLi"><a href="/noticelist?gnbParam=bbs">공지 게시판</a></li>
					<li class="lnbMainLi"><a href="/list?gnbParam=bbs">자유 게시판</a></li>
					<li class="lnbMainLi"><a href="#">menu3</a></li>
					<li class="lnbMainLi"><a href="#">menu4</a></li>
					<li class="lnbMainLi"><a href="#">menu5</a></li>
    			</c:when>
    			
    			<c:when test="${sessionScope.uId == null }">
		    		<li class="lnbMainLi"><a href="#">menu1</a></li>
					<li class="lnbMainLi"><a href="#">menu2</a></li>
					<li class="lnbMainLi"><a href="#">menu3</a></li>
					<li class="lnbMainLi"><a href="#">menu4</a></li>
					<li class="lnbMainLi"><a href="#">menu5</a></li>
    			</c:when>                                                      
   				<c:when test='${gnbParam.equals("myPage")}'>
   					<li class="lnbMainLi"><a href="/memberMod">회원정보수정</a></li>
					<li class="lnbMainLi"><a href="/memberQuit">회원탈퇴</a></li>
					<li class="lnbMainLi"><a href="#">menu3</a></li>
					<li class="lnbMainLi"><a href="#">menu4</a></li>
					<li class="lnbMainLi"><a href="#">menu5</a></li>
    			</c:when>
   				<c:when test='${gnbParam.equals("adminPage")}'>
   					<li class="lnbMainLi"><a href="/memList?gnbParam=adminPage">회원 목록</a></li>
					<li class="lnbMainLi"><a href="#">menu2</a></li>
					<li class="lnbMainLi"><a href="#">menu3</a></li>
					<li class="lnbMainLi"><a href="#">menu4</a></li>
					<li class="lnbMainLi"><a href="#">menu5</a></li>
    			</c:when>
   		
    			<c:otherwise>
		    		<li class="lnbMainLi"><a href="#">main1</a></li>
					<li class="lnbMainLi"><a href="#">menu2</a></li>
					<li class="lnbMainLi"><a href="#">menu3</a></li>
					<li class="lnbMainLi"><a href="#">menu4</a></li>
					<li class="lnbMainLi"><a href="#">menu5</a></li>
	    			</c:otherwise>
    		</c:choose>
    		
    			
		</ul>
	</nav>
	
</body>
</html>