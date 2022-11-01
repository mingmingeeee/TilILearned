<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/common/header.jsp"%>
<title>SSAFY</title>
</head>
<body>
	<%@ include file="/common/nav.jsp"%>
	<!-- 배너 -->
	<div id="bodyContent">
		<div id="banner">
			<div id="bannertitle">
				구해줘 HOME!!!
				<div id="subtitle">
					<a href="${root}/housetwo/getpage">집 찾으러가기</a>
				</div>
			</div>
		</div>

		<!-- 공지사항 table-->
		<div class="container my-3">
			<div class="row">
				<div class="text-center text-black-50 border-bottom py-2 my-2">집추천</div>
				<div class="d-md-flex justify-content-around" id="list">
					<div>
						<div class="home1"></div>
					</div>
					<div>
						<div class="home2"></div>
					</div>
					<div>
						<div class="home3"></div>
					</div>
					<div>
						<div class="home4"></div>
					</div>
				</div>

			</div>
		</div>
		<%@ include file="/common/footer.jsp"%>
</body>
</html>
