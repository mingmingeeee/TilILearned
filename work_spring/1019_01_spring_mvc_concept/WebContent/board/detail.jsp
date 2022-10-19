<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp" %>
<title>게시판 관리 - 글 조회</title>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	
	<c:if test="${ !empty board }">
		<table>
			<tr>
				<th>글 번호</th>
				<td>${ board.no }</td>
			</tr>
			<tr>
				<th>작성자 ID</th>
				<td>${ board.userId }</td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>
					<jsp:useBean id="longToDate" class="java.util.Date"/>
					<c:set target="${ longToDate }" property="time" value="${ board.createdAt }"/>
					<fmt:formatDate value="${ longToDate }" pattern="yyyy.MM.dd HH:mm"/>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${ board.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea style="width:100%;" disabled>${ board.content }</textarea>
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${ empty board }">
		<p>선택한 글이 없습니다.</p>
	</c:if>
	<a href="${ root }/">메인페이지로</a>
	<a href="${ root }/board/remove?no=${ board.no }">삭제</a>

	<%@ include file="/include/footer.jsp" %>
</body>
</html>