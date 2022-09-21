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
	<c:if test="${!empty person }">
		<table>
			<thead>
				<tr>
					<td>id</td>
					<td>이름</td>
					<td>부서</td>
					<td>급여</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${person.id}</td>
					<td>${person.name}</td>
					<td>${person.dept}</td>
					<td>${person.pay}</td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty person}">
		<p>선택한 사원 정보가 없습니다.</p>
	</c:if>
	<a href="${root}/main?act=index">메인 페이지로 이동</a>
	<a href="${root}/main?act=remove&id=${person.id}">삭제</a>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>