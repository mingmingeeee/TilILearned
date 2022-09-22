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
	
	  <h2>인사 정보 등록</h2>
	  <form id="registForm" method="post" action="${root}/main">
	  	<input type="hidden" name="act" value="regist">
	    <div class="form-group">
	      <label for="personId">사원식별번호</label>
	      <input type="text" class="form-control" id="personId" name="personId" placeholder="사원식별번호 입력">
	    </div>
	    <div class="form-group">
	      <label for="name">이름</label>
	      <input type="text" class="form-control" id="name" name="name" placeholder="사원명 입력">
	    </div>
	    <div class="form-group">
	      <label for="departmentName">부서명</label>
	      <input type="text" class="form-control" id="departmentName" name="departmentName" placeholder="부서명 입력" >
	    </div>
	    <div class="form-group">
	      <label for="pay">급여</label>
	      <input type="number" class="form-control" id="pay" name="pay" placeholder="급여 입력" >
	    </div>

	    <button type="submit" class="btn btn-primary" id="regist">등록</button>
	    <a class="btn btn-secondary" href="${root}/main?act=index" >취소</a>
	  </form>
	
	
	</div>
	<%-- --%>
<%@ include file="/include/footer.jsp" %>