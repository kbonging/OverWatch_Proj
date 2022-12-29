<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" autoFlush="true" %>
<%
request.setCharacterEncoding("UTF-8");
String uId_Session = request.getParameter("test");
%>    
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>Document</title>
		<link rel="shortcut icon" href="#">
		<link rel="stylesheet" href="/resources/style/style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="/resources/script/script.js"></script>		
	</head>
	<body>
		<div id="wrap">
			<h1>OK!!!</h1>
		</div>
		<!-- div#wrap -->
		<%="인수값 : " + uId_Session %>
			
	</body>
</html>