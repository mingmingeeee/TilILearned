<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<ul class="navbar-nav me-auto">
		<li class="nav-item"><a class="nav-link" href="${ root }/board/list">글 목록</a></li>
		<li class="nav-item"><a class="nav-link" href="${ root }/board/regist_form">글 등록</a></li>
		<li class="nav-item"><a class="nav-link" href="${ root }/house/search_form">부동산 매매정보</a></li>
	</ul>
	
	<div>
		<c:if test="${ !empty sessionScope.user }">
			<div style="color:white">${ user.id }님 로그인 되었습니다. <a href="${ root }/user/logout">로그아웃</a></div>
		</c:if>
	</div>
</nav>