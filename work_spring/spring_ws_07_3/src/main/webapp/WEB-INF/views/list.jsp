<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<div class="container">
		<h1>도서 목록</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>ISBN</th>
					<th>제목</th>
					<th>저자</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${ books }" varStatus="vs">
					<tr>
						<td>${ vs.count }</td>
						<td>${ book.isbn }</td>
						<td>${ book.title }</td>
						<td>${ book.author }</td>
						<td>${ book.price }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<a href="${ root }/regist">도서 등록</a>
	</div>
</body>
</html>