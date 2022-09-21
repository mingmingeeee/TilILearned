<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp" %>
<title>상품 조회</title>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<h1>상품 관리 - 리스트</h1>
	<table>
		<thead>
			<tr>
				<td>상품번호</td>
				<td>모델이름</td>
				<td>가격</td>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty list}">
					<c:forEach var="product" items="${list}">
						<tr>
							<td>
							<a href="${root}/main?act=detail&productCode=${product.code}">${product.code}</a>
							</td>
							<td>${product.model}</td>
							<td>${product.price} 원</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>등록된 상품이 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<a href="${root}/main?act=regist_form">추가 등록</a>
	
	<%@ include file="/include/footer.jsp" %>
</body>
</html>