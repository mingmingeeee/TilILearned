<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SSAFY ���� ����</title>
</head>
<body>

	<h1>���� ����</h1>
	<c:if test="${! empty userinfo}">
		${userinfo.name}�� �ݰ����ϴ�~
		<a href="${root}/logout">�α׾ƿ�</a>
	</c:if>
	
	<c:if test="${empty userinfo}">
		<form method="POST" action="${root}/login">
			<input type="text" name="id" placeholder="���̵�"> <input type="text"
				name="pass" placeholder="��й�ȣ"> <input type="submit" value="�α���">
		</form>
	</c:if>

</html>