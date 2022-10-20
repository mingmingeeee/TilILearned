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
	<c:if test="${! empty list }">
		<h1>도서 목록</h1>
		<table>
			<tr>
				<th>번호</th>
				<th>ISBN</th>
				<th>제목</th>
				<th>저자</th>
				<th>가격</th>
			</tr>
			<c:forEach var="book" items="${list}" begin="0" step="1" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${book.isbn}</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.price}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty list }">
		<h1>등록된 도서 목록이 없습니다.</h1>
	</c:if>
	
	<a href="${root}/regist">도서 등록</a>
</body>
</html>