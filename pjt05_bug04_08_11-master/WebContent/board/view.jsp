<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
	<%@ include file="/common/nav.jsp"%>
	<c:if test="${empty article}">
		<script type="text/javascript">
			alert("글이 삭제되었거나 정상적인 URL 접근이 아닙니다.");
			location.href = "${root}/board/list?pgno=1&key=&word=";
		</script>
	</c:if>
	<div class="container bc">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					글보기
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<div class="row my-2">
					<h2 class="text-secondary px-5">${article.articleNo}.
						${article.subject}</h2>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="clearfix align-content-center">
							<img class="avatar me-2 float-md-start bg-light p-2"
								src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
							<p>
								<span class="fw-bold">${article.userId}</span> <br /> <span
									class="text-secondary fw-light"> ${article.registerTime}</span>
							</p>
						</div>
					</div>
					<%-- <div class="col-md-4 align-self-center text-end">댓글 : 17</div> --%>
					<div class="col-md-4 align-self-center text-end">
						<span class="text-secondary fw-light">조회 : ${article.hit} </span>
					</div>
					<div class="divider mb-3"></div>
					<div class="text-secondary"><c:out value="${article.content}" /></div>
					<div class="divider mt-3 mb-3"></div>
					<div class="d-flex justify-content-end">
						<button type="button" id="btn-list"
							class="btn btn-outline-primary mb-3">글목록</button>
						<c:if test="${article.userId eq userinfo.userId}">
							<button type="button" id="btn-mv-modify"
								class="btn btn-outline-success mb-3 ms-1">글수정</button>
							<button type="button" id="btn-delete"
								class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form id="form-param" method="post" action="">
		<input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
		<input type="hidden" id="key" name="key" value="${param.key}">
		<input type="hidden" id="word" name="word" value="${param.word}">
	</form>
	<form id="form-no-param" method="post" action="${root}/board">
		<input type="hidden" id="npgno" name="pgno" value="${param.pgno}">
		<input type="hidden" id="nkey" name="key" value="${param.key}">
		<input type="hidden" id="nword" name="word" value="${param.word}">
		<input type="hidden" id="articleno" name="articleno"
			value="${article.articleNo}">
	</form>
	<script>
		document.querySelector("#btn-list").addEventListener("click",
				function() {
					let form = document.querySelector("#form-param");
					form.setAttribute("action", "${root}/board/list");
					form.submit();
				});

		document.querySelector("#btn-mv-modify").addEventListener("click",
				function() {
					let form = document.querySelector("#form-no-param");
					form.setAttribute("action", "${root}/board/mvmodify");
					form.submit();
				});

		document.querySelector("#btn-delete").addEventListener("click",
				function() {
					if (confirm("정말 삭제하시겠습니까?")) {
						let form = document.querySelector("#form-no-param");
						form.setAttribute("action", "${root}/board/delete");
						form.submit();
					}
				});
	</script>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
