<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
String uId_Session = (String)session.getAttribute("uId_Session"); 
%>    
 --%>
<!--
MemberMgr에서 세션을 사용하여 해당 회원의 정보를 반환하는 메서드를 생성하고 
memberMod.jsp 페이지에서 출력 항목에 맞도록 출력합니다.
탈퇴를 처리하는 메서드를 생성하여 처리합니다.
JavaScript파일을 기존 회원가입소스를 활용할 것인지 새롭게 만들것인지도 결정하고
회원정보수정이 정상적으로 처리될 수 있도록 작업합니다.
 -->    

<%-- <%
//String uId = "임시아이디출력";
MemberBean mBean = mMgr.modifyMember(uId_Session);
%>
 --%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>회원정보수정</title>
	<link rel="stylesheet" href="/resources/style/style_Common.css">
	<link rel="stylesheet" href="/resources/style/style_Template.css">
	<script src="/resources/source/jquery-3.6.0.min.js"></script>
	<script src="/resources/script/script_Join.js"></script>
</head>

<body>
    <div id="wrap">
    	
    	<!--  헤더템플릿 시작 -->
		<%@ include file="../ind/headerTmp.jsp" %>
    	<!--  헤더템플릿 끝 -->    	
    	
    	
    	<main id="main" class="dFlex">
    		
	    	<!-- 실제 작업 영역 시작 -->
    		<div id="contents" class="joinInsert">
    		
    			<form name="regFrm" id="regFrm" method="post">
    			
    				<table id="regFrmTbl">
    					<caption>회원 정보 수정</caption>
    					<tbody>
    						<tr>
    							<td class="req">아이디</td>
    							<td>${userData.uId }</td>
    							<td>&nbsp;</td>
    						</tr>
    						<tr>
    							<td class="req">패스워드</td>
    							<td>
    								<input type="password" name="uPw" id="uPw"
    								maxlength="20">
    								<input type="checkbox" id="pwView"> 비밀번호 보기
    							</td>
    							<td>
    								<span>영어소문자/숫자, _, @, $, 5~20 </span>
    								<!-- 공통비번1234 로 필 변경! -->
    							</td>
    						</tr>
    						<tr>
    							<td class="req">패스워드 확인</td>
    							<td>
    								<input type="password" id="uPw_Re"
    								maxlength="20">
    								<span id="pwChk"></span>
    							</td>
    							<td>&nbsp;</td>
    						</tr>
    						<tr>
    							<td class="req">이름</td>
    							<td>
    								<input type="text" name="uName" id="uName"
    								maxlength="20" value="${userData.uName }">
    							</td>
    							<td>&nbsp;</td>
    						</tr>
    						<tr>
    							<td class="req">Email</td>
    							<td>
	    							<input type="text" id="uEmail_01"
    									maxlength="20" size="7" value="${userData.uEmail_01 }">
    								<span>@</span>
    								<input type="text" id="uEmail_02"
    									maxlength="40" size="10"  value="${userData.uEmail_02 }">
    									
    								<select id="emailDomain" class="frmDropMenu">
    									<option value="">직접입력</option>
    									<option>naver.com</option>
    									<option>daum.net</option>
    								</select>
    								
    								<button type="button" id="emailAuthBtn" class="frmBtn">인증코드받기</button>
    								
    								<!--  이메일 인증 영역 시작 : Authentication Code 인증코드-->
    								<div id="emailAuthArea">           
    									<span>인증코드 입력</span>
    									<input type="text" id="emailAuth" size="25">
    									<button type="button" class="frmBtn">인증하기</button>
    								</div>
    								<!-- div#emailAuthArea -->
    											
    								<input type="hidden" name="uEmail" id="uEmail">
    								
    							</td>
    							<td>&nbsp;</td>
    						</tr>
    						<tr>
    							<td>성별</td>
    							<td>
    								<label>
    									남 <input type="radio" name="gender" value="1"
    									<c:if test="${userData.gender == 1}"> checked </c:if>
    									>
    								</label>
    								<label>
    									여 <input type="radio" name="gender" value="2"
    									<c:if test="${userData.gender == 2}"> checked </c:if>
    									>
    								</label>
    							</td>
    							<td>&nbsp;</td>
    						</tr>
    						<tr>
    							<td>생년월일</td>
    							<td>
    								<input type="text" name="uBirthday" id="uBirthday"
    								maxlength="6" size="8" value="${userData.uBirthday }">&nbsp;&nbsp;&nbsp;&nbsp;
    								<span>ex. 830815</span>
    							</td>
    							<td>&nbsp;</td>
    						</tr>
    						<tr>
    							<td>우편번호</td>
    							<td>    								
    								<input type="text" name="uZipcode" id="uZipcode"
    								maxlength="7" size="7" value="${userData.uZipcode }" readonly>
    								<button type="button" id="findZipBtn" class="frmBtn">우편번호찾기</button>
    							</td>
    							<td>
    								<span>우편번호 찾기 버튼을 클릭하세요.</span>
    							</td>
    						</tr>
    						<tr>
    							<td>주소</td>
    							<td>    								
    								<input type="text" name="uAddr" id="uAddr"
    								maxlength="100" size="50" value="${userData.uAddr }">
    							</td>
    							<td>&nbsp;</td>
    						</tr>
    						<tr>
    							<td>취미</td>
    							<td>    							
    								<label> 인터넷
    									 <input type="checkbox" name="uHobby" value="인터넷" 
    									 <c:if test="${hobby_1 == '인터넷'}"> checked </c:if>
    									 >
    								</label>						
    								<label> 여행
    									 <input type="checkbox" name="uHobby" value="여행"
    									 <c:if test="${hobby_2 == '여행'}"> checked </c:if>
    									 >
    								</label>						
    								<label> 게임
    									 <input type="checkbox" name="uHobby" value="게임"
    									 <c:if test="${hobby_3 == '게임'}"> checked </c:if>
    									 >
    								</label>						
    								<label> 영화
    									 <input type="checkbox" name="uHobby" value="영화"
    									 <c:if test="${hobby_4 == '영화'}"> checked </c:if>
    									 >
    								</label>						
    								<label> 운동
    									 <input type="checkbox" name="uHobby" value="운동"
    									 <c:if test="${hobby_5 == '운동'}"> checked </c:if>
    									 >
    								</label>
    							</td>
    							<td></td>
    						</tr>
    						<tr>
    							<td>직업</td>
    							<td>    								
    								<select name="uJob" id="uJob" class="frmDropMenu">
    									<option value=""> - 선택 - </option>
    									<option
    									 <c:if test="${userData.uJob == '교수'}">selected</c:if> 
    									>교수</option>
    									<option 
    									 <c:if test="${userData.uJob == '학생'}">selected</c:if>
    									>학생</option>
    									<option
    									 <c:if test="${userData.uJob == '회사원'}">selected</c:if>
    									>회사원</option>
    									<option
    									 <c:if test="${userData.uJob == '공무원'}">selected</c:if>
    									>공무원</option>
    									<option
    									 <c:if test="${userData.uJob == '자영업'}">selected</c:if>
    									>자영업</option>
    									<option
    									 <c:if test="${userData.uJob == '전문직'}">selected</c:if>
    									>전문직</option>
    									<option
    									 <c:if test="${userData.uJob == '주부'}">selected</c:if>
    									>주부</option>
    									<option
    									 <c:if test="${userData.uJob == '무직'}">selected="selected"</c:if>
    									>무직</option>
    								</select>
    							</td>
    							<td></td>
    						</tr>
    						<tr>
    							<td colspan="3">
    								<button type="button" id="joinSbmBtn" class="frmBtn">회원 수정</button>
    								<button type="reset" class="frmBtn">다시쓰기</button>
    							<!-- 	<button id="loginBtn" class="frmBtn">로그인</button> -->
    							</td>
    						</tr>
    					</tbody>
    				</table>
    					<input type="hidden" id="memEdit" value="수정">
    					<input type="hidden" id="checkuId" value="${userData.uId }">
    					<input type="hidden" id="yn" value="Y">
    					
    			</form>
    			<!-- form[name=regFrm] -->
    			
    		</div>
    		<!-- 실제 작업 영역 끝 -->
    		    	
    	</main>
    	<!--  main#main  -->
    
        	   	
    	<!--  푸터템플릿 시작 -->
		<%@ include file="../ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 -->  
        
    </div>
    <!-- div#wrap -->

</body>

</html>