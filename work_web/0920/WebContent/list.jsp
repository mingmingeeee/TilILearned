<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${!empty requestScope.list}"> <!-- "test"에는 조건 쓰기 -->
			<table>
				<thead>
				<tr>
					<td>글 번호</td>
					<td>글 제목</td>
					<td>작성자 id</td>
					<td>글 작성날짜</td>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${list}"> <!-- var: list에서 하나 끄집어내면 저장할 변수 -->
						<tr>
							<td>${board.no}</td>
							<td>${board.title}</td>
							<td>${board.id}</td>
							<td>${board.createdAt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
	</c:choose>
</body>
</html>