<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
<title>사원 조회</title>
</head>
<body>
	<%@ include file="/include/nav.jsp"%>
	<c:choose>
		<c:when test="${! empty list }">
			<table>
				<thead>
					<tr>
						<td>id</td>
						<td>이름</td>
						<td>부서 이름</td>
						<td>급여</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="person" items="${list}">
						<tr>
							<td><a href="${root}/main?act=detail&id=${person.id}">${person.id}</a></td>
							<td>${person.name}</td>
							<td>${person.departmentName}</td>
							<td>${person.pay}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>등록된 사원이 없습니다.</p>
		</c:otherwise>
	</c:choose>
	<a href="${root}/main?act=regist_form">추가 등록</a>
	<a href="${root}/main?act=index">메인 화면으로 가기</a>
	<%@ include file="/include/footer.jsp"%>
</body>
</html>