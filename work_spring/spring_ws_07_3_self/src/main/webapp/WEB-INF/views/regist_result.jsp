<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	td:nth-child(3) {
		width: 150px;
	}
	
	#cover {
		width: 150px;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<div class="container">
		<h1>도서 등록 결과</h1>
		<c:if test="${ !empty book }">
			<table class="table">
				<thead>
					<tr>
						<th>항목</th>
						<th colspan="2">내용</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>도서번호</td>
						<td>${ book.isbn }</td>
						<td rowspan="5"><img id="cover" src="${ root }/resources/upload/${ book.img }"></td>
					</tr>
					<tr>
						<td>도서명</td>
						<td>${ book.title }</td>
					</tr>
					<tr>
						<td>저자</td>
						<td>${ book.author }</td>
					</tr>
					<tr>
						<td>가격</td>
						<td>${ book.price }</td>
					</tr>
					<tr>
						<td>이미지</td>
						<td>${ book.orgImg }</td>
					</tr>
					<tr>
						<td>설명</td>
						<td colspan="2">${ book.content }</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		<c:if test="${ empty book }">
			<p>등록된 도서가 없습니다.</p>
		</c:if>
		
		<a href="${ root }/regist">[추가등록]</a>
		<a href="${ root }/list">[목록보기]</a>
	</div>
</body>
</html>