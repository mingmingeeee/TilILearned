<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
<title>상품 관리 - 상품 조회</title>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	
	<c:if test="${ !empty product }">
		<table>
			<tr>
				<th>상품 번호</th>
				<td>${product.code}</td>
			</tr>
			<tr>
				<th>모델명</th>
				<td>${product.model}</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>${product.price}</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty product}">
		<p>선택한 제품 정보가 없습니다.</p>
	</c:if>
	
	<a href="${root}/main?act=index">메인페이지로</a>
	<a href="${root}/main?act=remove&productCode=${product.code}">삭제</a> <!-- primary key로 처리 -> data 얻어낼 때 -->
	
	<%@ include file="/include/footer.jsp" %>
</body>
</html>