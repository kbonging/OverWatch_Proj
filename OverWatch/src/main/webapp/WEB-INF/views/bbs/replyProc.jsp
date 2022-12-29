<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${data.repInsCnt > 0 }">
		<script>
			alert("글 등록 성공");
			location.href="<c:url value='${url}'/>";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("${msg}");
			history.back();
		</script>
	</c:otherwise>
</c:choose>
