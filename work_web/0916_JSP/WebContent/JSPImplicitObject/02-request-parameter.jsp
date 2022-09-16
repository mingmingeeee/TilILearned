<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="./02-request-parameter.jsp">
		<label>이름: <input type="text" name="name"></label>
		<label>좋아하는 과목</label><br>
		<input type="checkbox" name="favorite" value="Java">자바<br>
		<input type="checkbox" name="favorite" value="HTML">HTML<br>
		<input type="checkbox" name="favorite" value="CSS">CSS<br>
		<input type="submit" name="제출하기">
	</form>
	
	<%
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		if(name != null){
	%>
	<div>
		<span><%= name %></span>
	</div>
	<%
		}
	%>
	
	<%
		String[] values = request.getParameterValues("favorite");
	if(values != null){
		for(int i=0; i<values.length; i++){
	%>
	
	<div>
		<span><%= values[i] %></span>
	</div>
	
	<% 
		}
	}
	%>
</body>
</html>