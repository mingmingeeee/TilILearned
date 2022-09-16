<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<h1>로그인 성공했습니다.</h1>
	<%
	String userId = request.getParameter("userId");
	String pwd = request.getParameter("pwd");
	
	if (userId != null) {
	%>
	<h2><%= userId %>님 환영합니다.</h2>
	<%
	}
	%>
</body>
</html>