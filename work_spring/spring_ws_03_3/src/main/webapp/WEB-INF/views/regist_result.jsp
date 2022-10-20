<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SSAFY ���� ����</title>
</head>
<body>
	<c:if test="${! empty book }">
		<h1>���� ��� ���</h1>
		<table>
			<tr>
				<th>�׸�</th>
				<th>����</th>
			</tr>
			<tr>
				<td>������ȣ</td>
				<td>${book.isbn}</td>
			</tr>
			<tr>
				<td>������</td>
				<td>${book.title}</td>
			</tr>
			<tr>
				<td>����</td>
				<td>${book.author}</td>
			</tr>
			<tr>
				<td>����</td>
				<td>${book.price}</td>
			</tr>
			<tr>
				<td>�̹���</td>
				<td>${book.img}</td>
			</tr>
			<tr>
				<td>����</td>
				<td>${book.content}</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${empty book }">
		<h1>��ϵ� ������ �����ϴ�.</h1>
	</c:if>

	<a href="${root}/regist">�߰� ���</a>
	<a href="${root}/list">��� ����</a>
</body>
</html>