<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SSAFY 도서 관리</title>
</head>
<body>
	<h1>도서 등록</h1>

	<form id="form-register" method="POST" action="">
		도서번호  <input type="text" name="isbn"> <br>
		도서명 <input type="text" name="title"> <br>
		저자  <input type="text" name="author"> <br>
		가격 <input type="number" name="price"> <br>
		이미지 <input type="file" name="img"> <br>
		설명 <br><textarea rows="10" cols="20" name="content"></textarea>
	</form>
	
	<button type="button" id="btn-register">등록</button>
	<button type="button" id="btn-cancel">취소</button>
	
	<form id="form-param" method="get" action="">
    </form>
	
	<script>
	document.querySelector("#btn-register").addEventListener("click", function () {
          let form = document.querySelector("#form-register");
          form.setAttribute("action", "${root}/regist");
          form.submit();
      });
	
	document.querySelector("#btn-cancel").addEventListener("click", function () {
        let form = document.querySelector("#form-param");
        form.setAttribute("action", "${root}/");
        form.submit();
    });
	</script>
	
</body>
</html>