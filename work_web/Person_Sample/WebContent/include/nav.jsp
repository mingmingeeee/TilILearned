<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<ul class="navbar-nav me-auto">
		<li class="nav-item"><a class="nav-link" href="${root}/main?act=list">사원 목록</a></li>
		<li class="nav-item"><a class="nav-link" href="${root}/main?act=regist_form">사원 정보 등록</a></li>
	</ul>
	<div>
		<c:if test="${! empty sessionScope.user}">
			<p style="color: white">${user.userid}님 환영합니다.<a href="${root}/main?act=logout">로그아웃</a></p>
		</c:if>
	</div>
</nav>