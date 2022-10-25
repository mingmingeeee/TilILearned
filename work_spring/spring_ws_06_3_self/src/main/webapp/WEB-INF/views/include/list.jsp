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
						<td> ${ vs.count }</td>
						<td> <a href="#" 
							class="isbn-num"
							data-no="${book.isbn}"> ${ book.isbn }</td>
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
	
	<form id="form-no-param" method="get" action="${root}/view">
		<input type="hidden" id="isbn" name="isbn" value="">
	</form>
	
	<script>
		let isbns = document.querySelectorAll(".isbn-num");
		isbns.forEach(function(isbn) {
			isbn.addEventListener("click", function() {
				document.qeurySelector("#isbn").value = this.getAttribute("data-no");
				document.querySelector("form-no-param").submit();
			})
		});
	</script>
	
</body>
</html>