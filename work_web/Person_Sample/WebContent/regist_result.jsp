<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp" %>
<title>사원 등록</title>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<h1>${cnt}명의 사원이 등록되었습니다.</h1>
	<a href="${root}/main?act=regist_form">추가 등록</a>
	<a href="${root}/main?act=list">사원 목록</a>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>