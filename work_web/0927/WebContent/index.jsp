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
	
		안녕하세요. 게시판 관리 사이트 입니다.
		
		<c:if test="${ !empty user }">
			<div>${ user.id }님 로그인 되었습니다. <a href="${ root }/user/logout">로그아웃</a></div> 
		</c:if>
		<c:if test="${ empty user }">
			<form method="post" action="${ root }/user/login">
				<c:if test="${ !empty message }">
					<div>${ message }</div>
				</c:if>
				<label>ID <input type="text" name="id"></label>
				<label>PASSWORD <input type="password" name="pass"></label>
				<input type="submit" value="LOGIN">
			</form>
		</c:if>
	
	</div>

<%@ include file="/include/footer.jsp" %>