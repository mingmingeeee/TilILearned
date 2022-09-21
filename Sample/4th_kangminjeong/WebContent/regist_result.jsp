<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	<title>등록 결과</title>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<p>${cnt}개 등록되었습니다.</p>
	<a href="${root}/main?act=index">메인 페이지로 이동</a>
	<a href="${root}/main?act=list">목록 보기</a>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>