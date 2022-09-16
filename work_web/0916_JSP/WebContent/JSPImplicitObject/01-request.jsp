<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 원하는 정보 -> reuquest 객체 통해 알아볼 수 있음 -->
	<!-- request 생성: 요청할 때 -->
	<!-- request 소멸: 요청 끝날 때 -->
	<div>클라이언트IP = <%= request.getRemoteAddr() %></div>
	<div>요청정보길이 = <%= request.getContentLength() %></div>
	<div>요청정보 인코딩 = <%= request.getCharacterEncoding() %></div>
	<div>요청정보 컨텐츠타입 = <%= request.getContentType() %> </div>
	<div>요청정보 프로토콜 = <%= request.getProtocol() %></div>
	<div>요청정보 전송방식 = <%= request.getMethod() %></div>
	<div>요청 URI = <%= request.getRequestURI() %></div>
	<div>컨텍스트 경로 = <%= request.getContextPath() %></div> 
	<!-- : 현재 실행한 Project의 루트 경로 -> 응용 어띃게 해?
		루트 경로가 바뀔 때마다 수동으로 입력했었는데 그 부분 대체해서 쓰면 됨 -->
	<div>서버이름 = <%= request.getServerName() %></div>
	<div>서버포트 = <%= request.getServerPort() %></div>
</body>
</html>