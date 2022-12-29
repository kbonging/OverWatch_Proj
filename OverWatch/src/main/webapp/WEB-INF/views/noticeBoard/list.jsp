<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link rel="stylesheet" href="/resources/style/style_Common.css">
	<link rel="stylesheet" href="/resources/style/style_Template.css">
	<link rel="stylesheet" href="/resources/style/style_BBS.css">
	<script src="/resources/source/jquery-3.6.0.min.js"></script>
	<script src="/resources/script/script_noticeBoard.js"></script>
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
    		<div id="contents" class="bbsList">
			
				<c:set var="prnType" value=""/>
				<c:choose>
					<c:when test="${empty map.keyWord }">
						<c:set var="prnType" value="전체 게시글"/>
					</c:when>
					<c:otherwise>
						<c:set var="prnType" value="검색 결과"/>
					</c:otherwise>
				</c:choose>
			
	    		<div id="pageInfo" class="dFlex">
					<span>${prnType } :  ${pageVo.totalRecord } 개</span>
					<span>페이지 :  ${pageVo.nowPage }  /  ${pageVo.totalPage } </span>  
				</div>	
					
				<table id="boardList">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>이름</th>
							<th>날짜</th>
							<th>조회수</th>
						</tr>		
						<tr>
							<td colspan="5" class="spaceTd"></td>
						</tr>		
				</thead>
				<tbody>
			
				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="5">
									게시물이 없습니다.
							</td>
						</tr>	
					</c:when>
					<c:otherwise>
						<c:set var = "doneLoop" value="false"/>
						<c:forEach items="${list }" var="list" begin="0" end="${pageVo.numPerPage-1 }" >
							<c:choose>
								<c:when test="${fn:length(list) == pageVo.numPerPage}">
									<c:set var="doneLoop" value="true"></c:set>
								</c:when>
								<c:otherwise>
									<tr class="prnTr" onclick="read('${list.num }', '${pageVo.nowPage }', '${list.delCheck }')">
		
									<td>
											<c:out value="${list.num }"/>
									</td> 
									<td class="subjectTd">(공지) ${list.subject }
									</td>
									<td>${list.uName }</td>
									<td>${list.regTM}</td>
									<td>${list.readCnt }</td>
								</tr>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</c:otherwise>
				</c:choose>

					
					<tr id="listBtnArea">
						<td colspan="2">
							<c:choose>
								<c:when test="${empty sessionuId }">
								</c:when>
								<c:when test="${sessionScope.uId eq 'admin' || sessionScope.uId eq 'adminSub'}">
									<button type="button" id="writeBtn" class="listBtnStyle">글쓰기</button>								
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</td>
						
						<td colspan="3">
						
							<form name="searchFrm" class="dFlex"
									id="searchFrm">
							
								<div>
									<select name="keyField" id="keyField">
										<option value="subject" 
											<c:if test="${map.keyField == 'subject' }"> 
											selected="selected" 
											</c:if>>제  목
										</option>
											
										<option value="uName"
											<c:if test="${map.keyField == 'uName' }"> 
											selected="selected" 
											</c:if>>이 름
										</option>
										
										<option value="content" 
											<c:if test="${map.keyField == 'content' }"> 
											selected="selected" 
											</c:if>>내  용
										</option>
									</select>
								</div>
								<div>
									<input type="text" name="keyWord" id="keyWord"  size="20" maxlength="30" value="${map.keyWord }">
								</div>
								<div>
									<button type="button" id="searchBtn" class="listBtnStyle">검색</button>
									<input type="hidden" name="gnbParam" id="gnbParam" value="bbs">
								</div>
															
							</form>
							
							<input type="hidden" id="pKeyField" value="${map.keyField }">
							<input type="hidden" id="pKeyWord" value="${map.keyWord }">
							<input type="hidden" id="nowPage" value="${pageVo.nowPage }">
						
						</td>
					</tr> 
					
					<tr id="listPagingArea">

							<td colspan="5" id="pagingTd">
									 <c:choose>
									<c:when test="${pageVo.totalPage != 0 }">
										<c:choose> 
											<c:when test="${pageVo.nowBlock > 1 }">
												<span class="moveBlockArea"
													onclick="moveBlock('${pageVo.nowBlock-1}', '${pageVo.pagePerBlock}', 'pb')">&lt; 
												</span>
											</c:when>
											<c:otherwise>
												<span class="moveBlockArea"></span>
											</c:otherwise>
										</c:choose> 
										<c:forEach var="count" varStatus="status" begin="${pageVo.pageStart }" end="${pageVo.pageEnd }">
											<c:choose>
												<c:when test="${status.count == pageVo.nowPage}">
													<span class="nowPageNum">${status.count} </span>
												</c:when>
												<c:otherwise>
													<span class="pageNum"
														onclick="movePage('${status.count+pageVo.pageStart-1}')">
														${status.count+pageVo.pageStart-1}
													</span>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:choose>
											<c:when test="${pageVo.totalBlock > pageVo.nowBlock }">
												<span class="moveBlockArea" 
													onclick="moveBlock('${pageVo.nowBlock+1}', '${pageVo.pagePerBlock}', 'nb')">
												&gt; 
												</span>
											</c:when>
											<c:otherwise>
												<span class="moveBlockArea"></span>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<b>[ Paging Area ]</b>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						
				</tbody>
			</table>
		
		
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