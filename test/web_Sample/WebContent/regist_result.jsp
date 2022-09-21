<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp" %>
<title>상품 관리 - 등록 성공</title>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<h1> 상품이 ${cnt}개 등록되었습니다.</h1>
	<a href="${root}/main?act=list">상품 목록 조회</a>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>