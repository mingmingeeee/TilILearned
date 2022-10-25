<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 도서 관리</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<div class="container">
		<ul>
			<li>
				<a href="${ root }/regist">도서 등록</a>
			</li>
			<li>
				<a href="${ root }/list">도서 목록</a>
			</li>
		</ul>
	</div>
</body>
</html>