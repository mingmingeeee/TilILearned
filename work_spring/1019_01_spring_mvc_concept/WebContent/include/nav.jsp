<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<ul class="navbar-nav me-auto">
		<li class="nav-item"><a class="nav-link" href="${ root }/board/list">글 목록</a></li>
		<li class="nav-item"><a class="nav-link" href="${ root }/board/regist_form">글 등록</a></li>
		<li class="nav-item"><a class="nav-link" href="${ root }/house/search_form">부동산 매매정보</a></li>
	</ul>
	<c:choose>
		<%-- session에 user 객체 없는 경우(로그인 X) --%>
		<c:when test="${ empty user }">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="${root}/user/login_form">로그인</a></li>
			</ul>
		</c:when>
		<%-- session에 user 객체 있는 경우(로그인 O) --%>
		<c:otherwise>
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link disabled">${user.id}님 반갑습니다.</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="${root}/user/logout">로그아웃</a>
				</li>
			</ul>
		</c:otherwise>
	</c:choose>
</nav>