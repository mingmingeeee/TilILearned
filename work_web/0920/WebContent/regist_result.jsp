<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${requestScope.cnt}개의 글이 등록되었습니다.</h1>
	<a href="${pageContext.request.contextPath}/index.jsp">홈으로 이동</a>
</body>
</html>