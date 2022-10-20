<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SSAFY 도서 관리</title>
</head>
<body>

	<h1>도서 관리</h1>
	<c:if test="${! empty userinfo}">
		${userinfo.name}님 반갑습니다~
		<a href="${root}/logout">로그아웃</a>
	</c:if>
	
	<c:if test="${empty userinfo}">
		<form method="POST" action="${root}/login">
			<input type="text" name="id" placeholder="아이디"> <input type="text"
				name="pass" placeholder="비밀번호"> <input type="submit" value="로그인">
		</form>
	</c:if>

</html>