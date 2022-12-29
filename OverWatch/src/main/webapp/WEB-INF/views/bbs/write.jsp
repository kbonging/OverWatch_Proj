<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>글쓰기</title>
	<link rel="stylesheet" href="/resources/style/style_Common.css">
	<link rel="stylesheet" href="/resources/style/style_Template.css">
	<link rel="stylesheet" href="/resources/style/style_BBS.css">
	<script src="/resources/source/jquery-3.6.0.min.js"></script>
	<script src="/resources/script/script_BBS.js"></script>
</head>

<body>
    <div id="wrap">
    	
    	<!--  헤더템플릿 시작 -->
		<%@ include file="../ind/headerTmp.jsp" %>
    	<!--  헤더템플릿 끝 -->    	
    	
    	
    	<main id="main" class="dFlex">
    	
    		<div id="lnb">
	    		<!--  메인 LNB 템플릿 시작 -->
				<%@ include file="../ind/mainLnbTmp.jsp" %>
	    		<!--  메인 LNB 템플릿 끝 -->    	
    		</div>
    		
    		
	    	<!-- 실제 작업 영역 시작 -->
    		<div id="contents" class="bbsWrite">

				<h2>글쓰기</h2>
				
				<form name="writeFrm" 
						  method="post" id="writeFrm" enctype="multipart/form-data">
				
					<table>
						<tbody>
							<tr>
								<td class="req">성명</td>  <!-- td.req 필수입력 -->
								<td>
									${data.uName}
									<input type="hidden" name="uName" id="uName" value="${data.uName}">
									<input type="hidden" name="uId" value="${data.uId }">
								</td>
							</tr>
							<tr>
								<td class="req">제목</td> <!-- td.req 필수입력 -->
								<td>
									<input type="text" name="subject"
									maxlength="50" id="subject">
								</td>
							</tr>
							<tr>
								<td class="contentTD">내용</td>
								<td>
									<textarea name="content" id="content" cols="60" wrap="hard"></textarea>
								</td>
							</tr>
							<tr>
								<td>파일첨부</td>
								<td>
									<span class="spanFile">
										<input type="file" name="fileName" id="fileName">
									</span>	
								</td>
							</tr>
							<tr>
								<td>내용타입</td>
								<td>
									<label>
										<input type="radio" name="contentType" value="HTML">									
										<span>HTML</span>	
									</label>
									<label>
										<input type="radio" name="contentType" value="TEXT" checked>
										<span>TEXT</span>
									</label>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2"><hr>	</td>							
							</tr>
							<tr>
								<td colspan="2">
									<button type="button" id="regBtn">등록</button>
									<button type="reset">다시쓰기</button>
									<button type="button" id="listBtn">리스트</button>
								</td>
							</tr>
						</tfoot>
					</table>
					<input type="hidden" name="ip" value="${data.ip } ">
				    <!--  
				    IP주소를 IPv4 형식으로 설정함.(IPv6 형식이 기본으로 설정되어 있음)
				    프로젝트 => Run Configuration => Tomcat 클릭
				    => (x)Argument => VM arguments 입력란 =>
				    -Djava.net.preferIPv4Stack=true  
				     -->
								
				</form>
				<input type="hidden" name="nowPage" value="${map.nowPage }" id="nowPage">
				<input type="hidden" name="keyField" id="pKeyField" value="${map.keyField }">
				<input type="hidden" name="keyWord" id="pKeyWord" value="${map.keyWord }">
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