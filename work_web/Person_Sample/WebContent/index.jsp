<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>

	<%-- 페이지만의 내용 --%>
	<div class="container p-4">
	
		안녕하세요. 인사 관리 사이트 입니다.
		
		<c:if test="${empty user}">
			<form method="post" action="${root}/main?act=login">
				<c:if test="${! empty message}"><div>${message}</div></c:if>
				<label>ID <input type="text" id="userid" name="userid"></label>
				<label>PASSWORD <input type="password" id="userpass" name="userpass"></label>
				<input type="submit" value="LOGIN">
			</form>
		</c:if>
		<c:if test="${! empty user}">
			<p>${user.userid}님 환영합니다.</p> <a href="${root}/main?act=logout">로그아웃</a>
		</c:if>
	
	
	</div>

<%@ include file="/include/footer.jsp" %>