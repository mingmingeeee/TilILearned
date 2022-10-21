<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${ pageContext.request.contextPath }" scope="session"></c:set>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

<link rel="stylesheet" href="${ root }/resources/css/common.css">

<div class="container">
	<img src="${ root }/resources/img/ssafy_logo.png" id="ssafy_logo">
	<h1 class="display-4 text-center">도서 관리</h1>
	<br>
	<div class="text-right">
		<c:if test="${ empty loginUser }">
			<form method="post" action="${ root }/login">
				<div class="form-group">
					<input type="text" name="id" placeholder="아이디">
					<input type="password" name="pass" placeholder="비밀번호">
					<input type="submit" class="btn btn-primary" value="로그인">
				</div>
			</form>
		</c:if>
		<c:if test="${ !empty loginUser }">
			<div>
				<span>${ loginUser.name }님 반갑습니다.</span>
				<a href="${ root }/logout">로그아웃</a>
			</div>
		</c:if>
	</div>
</div>

<script>
	let msg = "${ msg }";
	if (msg) {
		alert(msg);
	}
</script>