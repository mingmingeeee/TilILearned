<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<div class="container">
		<h1>도서 목록</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ISBN</th>
					<th>제목</th>
					<th>저자</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a href="#" class="isbn-num" data-no="${book.isbn}">
							${ book.isbn }</td>
					<td>${ book.title }</td>
					<td>${ book.author }</td>
					<td>${ book.price }</td>
				</tr>
			</tbody>
		</table>
		<br> <a href="${ root }/delete?isbn=${book.isbn}">도서 삭제</a>
		<a href="${ root }/modify?isbn=${book.isbn}">도서 수정</a>
	</div>

</body>
</html>