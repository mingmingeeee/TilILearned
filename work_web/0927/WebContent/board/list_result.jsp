<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp" %>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<h1>게시물 관리 - 리스트</h1>
	<table>
		<thead>
			<tr>
				<td>글 번호</td>
				<td>제목</td>
				<td>작성자 ID</td>
				<td>작성날짜</td>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${ !empty list }">
					<c:forEach var="board" items="${ list }">
						<tr>
							<td><a href="${root}/board/detail?no=${board.no}">${ board.no }</a></td>
							<td>${ board.title }</td>
							<td>${ board.userId }</td>
							<td>
								<!-- 클라이언트에서 원하는 형태로 작성 -->
								<!-- Date longToDate => new Date(); -->
								<jsp:useBean id="longToDate" class="java.util.Date"/>
								<!-- target="${ longToDate }" :: longToDate.getTime(long)  -->
								<c:set target="${ longToDate }" property = "time" value="${ board.createdAt }"/>
								<!-- 형식 변환 -->
								<fmt:formatDate value="${ longToDate }" pattern="yyyy.MM.dd HH:mm"/>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>등록된 상품이 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<a href="${ root }/board/regist_form">추가 등록</a>
	
	<%@ include file="/include/footer.jsp" %>
</body>
</html>