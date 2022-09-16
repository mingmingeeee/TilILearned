<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- 버퍼가 다 찼을 경우 버퍼를 플러시하고 계속해서 작업을 진행 (기본값)  -->
<%@ page buffer="1kb" autoFlush="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>버퍼 정보</title>
</head>
<body>
<%
for(int i=1; i<=834; i++){
%>
<%=0 %>
<%
}
%>
	<div>버퍼 크기: <%= out.getBufferSize() %></div>
	<div>남은 크기: <%= out.getRemaining() %></div>
	<div>auto flush: <%= out.isAutoFlush() %></div>
</body>
</html>