<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>

	<%-- 페이지만의 내용 --%>
	<div class="container p-4">
	
	  <h2>글 등록</h2>
	  <form id="registForm" method="post" action="${ root }/board/regist">
	    <div class="form-group">
	      <label for="title">제목</label>
	      <input type="text" class="form-control" id="title" name="title" placeholder="제목 입력">
	    </div>
	    <div class="form-group">
	      <label for="content">내용</label>
	      <input type="text" class="form-control" id="content" name="content" placeholder="내용 입력">
	    </div>
	    
	    <button type="submit" class="btn btn-primary">등록</button>
	    <a class="btn btn-secondary" href="#" >취소</a>
	  </form>
	
	
	</div>
	<%-- --%>
<%@ include file="/include/footer.jsp" %>