<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<ul class="navbar-nav me-auto">
		<li class="nav-item"><a class="nav-link" href="${ root }/main?act=list">상품 목록</a></li>
		<li class="nav-item"><a class="nav-link" href="${ root }/main?act=regist_form">상품 정보 등록</a></li>
	</ul>
	
	<div>
		<c:if test="${ !empty sessionScope.user }">
			<div style=color:white>${ user.id }님 로그인 되었습니다. <a href="${ root }/main?act=logout">로그아웃</a></div>
		</c:if>
	</div>
</nav>
