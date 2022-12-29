<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인영역 LNB 메뉴</title>
<link rel="stylesheet" href="/resources/style/style_Common.css">
<link rel="stylesheet" href="/resources/style/style_Template.css">
<link rel="stylesheet" href="/resources/style/style_BBS.css">
<script src="/resources/source/jquery-3.6.0.min.js"></script>
<script src="/resources/script/script_comments.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
	<!--  댓글 시작 -->
	<div id="CommentDiv">
	<c:choose>
		<c:when test="${sessionScope.uId!='admin' && sessionScope.uId!='adminSub' }">
		<form id="CommentFrm" name="CommentFrm">
			<table id="table">
				<tr>
					<td><b>${sessionScope.uName }</b></td>
					<td>
							<input type="button" id="Cbtn" value="댓글 등록">
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<textarea name="content" id="content" maxlength="50" placeholder="내용을 입력하세요."></textarea></td>
				</tr>
			</table>
			<!-- 현재 로그인중인 회원 정보 -->
			<input type="hidden" id="sessionuId" name="sessionuId" value="${sessionScope.uId }" /> 
			<input type="hidden" id="boardNo" name="boardNo" value="${data.num }">

		</form>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>

		<c:forEach var="list" items="${commentMapList }" begin="0">
		
			<!-- comments Div 시작 -->
			<div class="comments">
				<c:choose>
					<c:when test="${list.delcheck eq 'Y' }">
							<ul>
								<li  style="color: #ccc">
									${list.content }
								</li>
						</ul>	
					</c:when>
					<c:otherwise>
							<ul>
							<li>
								<b>
								<c:if test="${list.depth >0 }">
									<c:forEach begin="0" end="${list.depth-1 }">
									&nbsp;&nbsp;
									</c:forEach>
									<img src='/resources/images/replyImg.png' alt='reply'>&nbsp;
								</c:if> 
								${list.uId }
								</b>
							</li>
							<c:if test="${sessionScope.uId == list.uId || sessionScope.uId == 'admin' || sessionScope.uId == 'adminSub' }">
								<li>
									<button class="delBtn" onclick="del('${list.num}')" style="float:right;">X</button>
								</li>
							</c:if>
							<li>
								<c:if test="${list.depth >0 }">
									<c:forEach begin="0" end="${list.depth-1 }">
									&nbsp;&nbsp;
									</c:forEach>
								</c:if>
								${list.content }
							</li>
							<li>
								<c:if test="${list.depth >0 }">
										<c:forEach begin="0" end="${list.depth-1 }">
										&nbsp;&nbsp;
										</c:forEach>
								</c:if>	
							
								<small>${list.regdate }</small> 
								<c:choose>
							<c:when test="${sessionScope.uId == 'admin' ||  sessionScope.uId == 'adminSub'}">
							
							</c:when>
							<c:otherwise>
									<button class="replyBtn" >답글쓰기</button>
							</c:otherwise>
						</c:choose>
								
							</li>
							<li class="reply">
								<ul class="replyTable">
									<li><textarea name="commentReply" class="commentReply${list.num}"  maxlength="50" placeholder="내용을 입력하세요."></textarea></li>
									<li>
										<button class="ComReplyBtn" onclick="Commentreply('${list.num}','${list.pos}','${list.ref}','${list.depth}','${sessionScope.uId}')">답글등록</button>
									</li>
								</ul>
							</li>
						</ul>
					
					</c:otherwise>
				</c:choose>
				</div>
				<!-- comments Div 끝 -->
			
			</c:forEach>
		</div>	
	<!--  댓글 끝 -->

</body>
</html>
