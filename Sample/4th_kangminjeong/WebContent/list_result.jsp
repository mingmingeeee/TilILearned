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
	<table>
		<thead>
			<tr>
				<td>
					id
				</td>
				<td>
					이름
				</td>
				<td>
					부서 이름
				</td>
				<td>
					급여
				</td>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty list}">
					<c:forEach var="person" items="${list}">
						<tr>
							<td>
								<a href="${root}/main?act=detail&id=${person.id}">${person.id}</a>
							</td>
							<td>${person.name}</td>
							<td>${person.dept}</td>
							<td>${person.pay}원</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>등록된 사원 정보가 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<a href="${root}/main?act=regist">추가 등록</a>
	<a href="${root}/main?act=index">메인 페이지로 이동</a>
	<%@ include file="/include/footer.jsp" %>
</body>
</html>