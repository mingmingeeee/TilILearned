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
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글쓰기 결과</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="card text-center bg-light">
            <h2 class="fw-bold text-primary pt-3">등록 완료!!!</h2>
            <div class="card-body">
              <p class="card-text">글작성이 완료되었습니다.</p>
              <button type="button" id="btn-list" class="btn btn-outline-primary">
                글목록 페이지 이동...
              </button>
            </div>
          </div>
          <div class="card text-center bg-light">
            <h2 class="fw-bold text-danger pt-3">등록 실패T.T</h2>
            <div class="card-body">
              <p class="card-text">
                글작성에 문제가 있습니다. <br />
                잠시 후 시도해주세요.
              </p>
              <button type="button" id="btn-list" class="btn btn-outline-danger">
                글목록 페이지 이동...
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "";
      });
    </script>
<%@ include file="/common/footer.jsp" %>
