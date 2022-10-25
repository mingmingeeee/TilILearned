<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 도서 관리</title>
<style>
	label {
		display: inline-block;
		width: 80px;
	}
	
	textarea {
		width: 100%;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<div class="container">
		<h1>도서 등록</h1>
		<form method="post" action="modify" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="isbn" class="form-label">도서번호</label>
				<input type="text" class="form-control" id="isbn" name="isbn" value="${book.isbn}">
			</div>
			<div class="mb-3">
				<label for="title" class="form-label">도서명</label>
				<input type="text" class="form-control" id="title" name="title" value="${book.title}">
			</div>
			<div class="mb-3">
				<label for="author" class="form-label">저자</label>
				<input type="text" class="form-control" id="author" name="author" value="${book.author}">
			</div>
			<div class="mb-3">
				<label for="price" class="form-label">가격</label>
				<input type="number" class="form-control" id="price" name="price" value="${book.price}">
			</div>
			<div class="mb-3">
				<label for="file" class="form-label">이미지</label>
				<!-- img이름을 전달받기 위해서 dto이름이랑 맞춰줬는데 img는 따로 Parameter로 전달받게끔 이름을 바꿈 -->
				<input type="file" class="form-control" id="file" name="upfile" accept="image/*">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">설명</label>
				<textarea rows="3" class="form-control" id="content" name="content">${book.content}</textarea>
			</div>
			<div class="mb-3">
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</div>
		</form>
		<br>
		<a href="${ root }/list">도서 목록</a>
	</div>
</body>
</html>