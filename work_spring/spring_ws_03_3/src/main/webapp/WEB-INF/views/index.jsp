<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SSAFY ���� ����</title>
</head>
<body>
	<c:if test="${! empty msg }">
		<script type="text/javascript">
			alert("�α��� ����");
		</script>
	</c:if>

	<ul>
		<li><a href="${root}/regist">���� ���</a></li>
		<li><a href="${root}/list">���� ���</a></li>
	</ul>

</body>
</html>