<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h1>오류가 발생했습니다.</h1>
	<p>에러 타입: <%=exception.getClass().getName() %></p>
	<p>에러 메세지: <%= exception.getMessage() %></p>
</body>
</html>