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
	
	<c:if test="${ ! empty person }">
		<table>
			<tr>
				<th>사원식별번호</th>
				<td>${ person.id }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${ person.name }</td>
			</tr>
			<tr>
				<th>부서명</th>
				<td>${ person.departmentName }</td>
			</tr>
			<tr>
				<th>급여</th>
				<td>${ person.pay }</td>
			</tr>

		</table>
		
		<a href="${root}/main?act=remove&id=${person.id}">삭제</a>
	</c:if>
	<c:if test="${empty person}">
		<p>등록된 사원이 없습니다.</p>
	</c:if>
	
	<a href="${root}/main?act=index">메인 화면으로 이동</a>
	<a href="${root}/main?act=list">사원 목록</a>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>