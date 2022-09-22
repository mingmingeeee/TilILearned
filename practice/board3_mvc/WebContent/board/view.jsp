<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${ pageContext.request.contextPath }"></c:set>
<head>
    <meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <link rel="shortcut icon" href="${root}/assets/images/favicon.png"
	type="image/x-icon" />
	<link rel="stylesheet" href="${root}/style.css" />
    <link href="${root}/assets/css/app.css" rel="stylesheet" />
    <title>자유 게시판 목록</title>
  </head>
  <body>
<%@ include file="/common/header.jsp" %>
<div class="container">
<c:if test="${empty article}">
	<script type="text/javascript">
		alert("글이 삭제되었거나 정상적인 URL 접근이 아닙니다.");
		location.href = "${root}/board?act=list&pgno=1&key=&word=";
	</script>
</c:if>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글보기</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary px-5">${ article.articleNo }. ${ article.subject }</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">${ article.userName }</span> <br />
                  <span class="text-secondary fw-light"> ${ article.registerTime } 조회 : ${ article.hit } </span>
                </p>
              </div>
            </div>
            <div class="col-md-4 align-self-center text-end">댓글 : 17</div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              ${ article.content }
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                글목록
              </button>
            <c:if test="${ article.userId eq userinfo.userId }">
              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
                글수정
              </button>
              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
                글삭제
              </button>
            </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
    <form id="form-param" method="get" action="">
      <input type="hidden" id="act" name="act" value="">
      <input type="hidden" id="pgno" name="pgno" value="${ qs.pgno }">
      <input type="hidden" id="key" name="key" value="${ qs.key }">
      <input type="hidden" id="word" name="word" value="${ qs.word }">
    </form>
    <form id="form-no-param" method="get" action="${root}/board">
      <input type="hidden" id="nact" name="act" value="">
      <input type="hidden" id="npgno" name="pgno" value="${ qs.pgno }">
      <input type="hidden" id="nkey" name="key" value="${ qs.key }">
      <input type="hidden" id="nword" name="word" value="${ qs.word }">
      <input type="hidden" id="articleno" name="articleno" value="${ article.articleNo }">
    </form>
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
    	  let form = document.querySelector("#form-param");
    	  document.querySelector("#act").value = "list";
    	  form.setAttribute("action", "${root}/board");
          form.submit();
      });
      
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
    	  let form = document.querySelector("#form-no-param");
    	  document.querySelector("#nact").value = "mvmodify";
    	  form.setAttribute("action", "${root}/board");
          form.submit();
      });
      
      document.querySelector("#btn-delete").addEventListener("click", function () {
       	if(confirm("정말 삭제하시겠습니까?")) {
       	  let form = document.querySelector("#form-no-param");
      	  document.querySelector("#nact").value = "delete";
      	  form.setAttribute("action", "${root}/board");
          form.submit();
       	}
      });
    </script>
<%@ include file="/common/footer.jsp" %>
