<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="javax.websocket.Session" %>
<%@ page session="true" %> <!-- session = "false" -> session 객체 자체를 막아버리는 거 -->
<%
	String userId = request.getParameter("userId");
	String pwd = request.getParameter("pwd");
	
	if("ssafy".equals(userId) && "1234".equals(pwd)){
		session.setAttribute("userId", userId); // session Storage랑은 다른 저장소 
		// -> session객체: 서버가 들고 있음
		// -> session Storage: 클라이언트가 들고 있음
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
		if (session.getAttribute("userId") == null){
	%>
	<form method="post" action="./03-session.jsp">
		<label>아이디: <input type="text" name="userId"></label>
		<label>비밀번호: <input type="text" name="pwd"></label>
		<input type="submit" value="로그인">
	</form>
	<%
		}
		else {
	%>
	<h1><%=(String) session.getAttribute("userId") %>님 환영합니다.</h1>
	<%
	}%>
</body>
</html>