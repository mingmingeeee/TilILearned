<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page errorPage="./error/error500.jsp" %> 
<%
	int result = 1 / 0; // ArithmeticException 발생
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h1>어서 오십시오.</h1>
</body>
</html>