<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 게시판 관리</title>
</head>
<body>
	<header>
		<form method="post" action="login">
			<label>아이디: <input type="text" name="id"></label>
			<label>비밀번호: <input type="password" name="pass"></label>
			<input type="submit" value="로그인">
		</form>
		<div style="display:none">
			<span id="userId"></span>
			
			<span>님 환영합니다. </span>
			<a href="logout">로그아웃</a>
		</div>
	</header>
	
	<h1>게시판 관리 Server</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/regist">
		<label>제목: <input type="text" name="title"></label>
		<label>내용: <input type="text" name="content"></label>
		<label>작성자ID: <input type="text" name="id"></label>
		<input type="submit" value="글 등록">
	</form>
	
	<a href="${root}/list">글 목록 페이지로 이동</a>
	
</body>
</html>


