<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SSAFY 도서 관리</title>
</head>
<body>
	<c:if test="${! empty book }">
		<h1>도서 등록 결과</h1>
		<table>
			<tr>
				<th>항목</th>
				<th>내용</th>
			</tr>
			<tr>
				<td>도서번호</td>
				<td>${book.isbn}</td>
			</tr>
			<tr>
				<td>도서명</td>
				<td>${book.title}</td>
			</tr>
			<tr>
				<td>저자</td>
				<td>${book.author}</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>${book.price}</td>
			</tr>
			<tr>
				<td>이미지</td>
				<td>${book.img}</td>
			</tr>
			<tr>
				<td>설명</td>
				<td>${book.content}</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${empty book }">
		<h1>등록된 도서가 없습니다.</h1>
	</c:if>

	<a href="${root}/regist">추가 등록</a>
	<a href="${root}/list">목록 보기</a>
</body>
</html>