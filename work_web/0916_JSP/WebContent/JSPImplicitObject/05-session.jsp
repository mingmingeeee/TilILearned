<%@page import="javax.websocket.Session"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%-- <%@ page session="false"%> --%>
<%
	String userId = request.getParameter("userId");
	String pwd = request.getParameter("pwd");

	if ("ssafy".equals(userId) && "1234".equals(pwd)) {
		session.setAttribute("userId", userId);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("userId") == null) {
	%>
	<form method="post" action="./03-session.jsp">
		<label>아이디: <input type="text" name="userId"></label><br>
		<label>비밀번호: <input type="password" name="pwd"></label><br>
		<input type="submit" value="로그인">
	</form>
	<%
		} else {
	%>
	<h1><%=(String) session.getAttribute("userId")%>님 환영합니다.</h1>
	<%
		}
	%>
</body>
</html>