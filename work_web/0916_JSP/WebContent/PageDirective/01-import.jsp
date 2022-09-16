<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page trimDirectiveWhitespaces="true" %> <!-- JSP 페이지에 존재하는 불필요한 공백 제거 -> F12->Source-> 공백제거됨! -->
<%@ page import="java.util.Calendar" %>
<%-- 동해물과  --%>
<%-- 백두산이  --%>
<%-- 마르고  --%>
<%-- 닳도록  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Calendar cal = Calendar.getInstance();
%>
<div>오늘은</div>
<% out.println(cal.get(Calendar.YEAR)); %>
<%= cal.get(Calendar.YEAR) %>
</body>
</html>