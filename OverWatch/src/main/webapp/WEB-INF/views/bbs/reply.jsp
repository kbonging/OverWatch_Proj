<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>답변글 작성페이지</title>
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
    		<div id="contents" class="reply">
    		
				<!--  답변페이지 내용 출력 시작 -->
				<form name="replyFrm" action="/replyProc"
						method="get" id="replyFrm">
			
					<h2>답변글 작성</h2>
						
					<table id="replyTbl">
						<tbody id="replyTblBody">
							<tr>
								<td class="req">작성자</td>
								<td>
									${data.replyName }
									[<span class="ori_Txt">원본 작성자 : <b>${data.uName }</b></span> ]
									
								</td>
							</tr>
							<tr>
								<td class="req">제목</td>
								<td>
									<input type="text" name="subject" value=""
										size="50" id="subject">								
									(<span class="ori_Txt">원본 제목 : <b>${data.subject }</b></span> )
								</td>
							</tr>
							<tr>
								<td style="vertical-align: top;">내용</td>
								<td>
									<textarea name="content" id="txtArea"  
									cols="89" wrap="hard"></textarea>
									
									<span id="ori_SpanTxtArea" class="ori_Txt">원본 글내용</span>
									<textarea id="ori_TxtArea"  
									cols="89" readonly>${data.content }</textarea>
									
								</td>
							</tr>			
						</tbody>
						 
						<tfoot>	
							<tr>
								<td colspan="2" id="footTopSpace"></td>							
							</tr>	
							<tr>
								<td colspan="2" id="hrTd"><hr></td>							
							</tr>
							<tr>
								<td colspan="2" id="btnAreaTd" class="reply">
									<button type="button" id="replyBtn">답변등록</button>
									<button type="reset">다시쓰기</button>
									<button type="button" id="backBtn">뒤 로</button>							
								</td>
							</tr>
						</tfoot>
						 
					</table>
					<input type="hidden" name="num" value="${data.num }" id="num">				
					<input type="hidden" name="uId" value="${sessionScope.uId }">	
					<input type="hidden" name="uName" value="${data.replyName }">
					<input type="hidden" name="ref" value="${data.ref }">				
					<input type="hidden" name="depth" value="${data.depth }">				
					<input type="hidden" name="pos" value="${data.pos }">
					
					<input type="hidden" name="nowPage" value="${map.nowPage }" id="nowPage">
					<input type="hidden" name="ip" value="${data.ip }">
					
					<!-- 검색어전송 시작 -->
					<input type="hidden" name="keyField" id="keyField" value="${map.keyField }">
					<input type="hidden" name="keyWord" id="keyWord" value="${map.keyWord }">
					<!-- 검색어전송 끝 -->
			
				</form>
				<!--  답변페이지 내용 출력 끝 -->
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