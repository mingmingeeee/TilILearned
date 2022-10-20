<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SSAFY 도서 관리</title>
</head>
<body>
	<c:if test="${! empty msg }">
		<script type="text/javascript">
			alert("로그인 실패");
		</script>
	</c:if>

	<ul>
		<li><a href="${root}/regist">도서 등록</a></li>
		<li><a href="${root}/list">도서 목록</a></li>
	</ul>

</body>
</html>