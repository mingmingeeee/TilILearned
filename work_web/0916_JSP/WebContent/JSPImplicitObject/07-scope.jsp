<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.Enumeration"%>
<%
// JSP 기본객체의 영역은 총 4가지이다.
// pageContext, request, session, application

// 아래는 공통 메서드 4가지
// 속성명과 객체의 쌍으로 현재 객체에 저장
application.setAttribute("name", "홍길동"); // 첫번째 param은 무족권 String, 두번째는 Object가 들어갈 수 있음

// 현재 객체에 저장된 속성의 값 가져오기
String name = (String) application.getAttribute("name");

// 현재 객체에 저장된 속성들의 이름들을 가져온다.
Enumeration<String> attributeNames = application.getAttributeNames();
while (attributeNames.hasMoreElements()) {
	String attributeName = attributeNames.nextElement();
}

// 속성을 삭제한다.
application.removeAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>