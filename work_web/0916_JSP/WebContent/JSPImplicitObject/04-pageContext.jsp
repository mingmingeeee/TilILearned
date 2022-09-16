<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pageContext 기본 객체</title>
</head>
<body>
<!-- pageContext는 하나의 JSP 페이지와 1대 1로 매핑되는 객체 -->
<!-- JSP 2.3 API: https://docs.oracle.com/javaee/7/api/javax/servlet/jsp/package-summary.html -->
	<%
	HttpServletRequest httpRequest = (HttpServletRequest) pageContext.getRequest();  // request
	pageContext.getResponse();  // response
	pageContext.getSession();  // session
	pageContext.getServletContext();  // application
	pageContext.getServletConfig();  // config
	pageContext.getOut();  // out
	pageContext.getException();  // exception
	pageContext.getPage();  // page
	%>

	request 기본 객체와 pageContext.getRequest()의 동일여부:

	<%=request == httpRequest%><br>
	
	pageContext.getOut() 메서드를 사용한 데이터 출력:

	<%
		pageContext.getOut().println("안녕하세요!");
	%>

</body>
</html>