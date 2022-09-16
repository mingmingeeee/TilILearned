<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
	String userId = request.getParameter("userId");
	String pwd = request.getParameter("pwd");

	if ("ssafy".equals(userId) && "1234".equals(pwd)) {
		// forward
		/*java*/
		// RequestDispatcher disp = request.getRequestDispatcher("./03-success.jsp");
		// disp.forward(request, response);
		
		/*jsp*/
		//pageContext.forward("./03-success.jsp"); 
		// 요청했던 주소가 유지되고 다음 페이지로 이동되는 것
		// 새로고침 -> 전페이지에서 보냈던 data들을 다시 보내고 있음 
		
		// sendRedirect
		response.sendRedirect(request.getContextPath() + "/JSPImplicitObject/03-success.jsp");
		// -> request 객체 사용 불가
	}
	else {
%>
<html>
<head>
	<title>로그인 실패</title>
</head>
<body>
	<h1>로그인 실패했습니다.</h1>
</body>
</html>
<%
	}
%>