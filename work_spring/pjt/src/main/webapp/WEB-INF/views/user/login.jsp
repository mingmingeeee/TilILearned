<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/nav.jsp"%>
	<c:if test="${empty user}">
		<script type="text/javascript">
			location.href = "${root}/";
		</script>
	</c:if>
	<c:if test="${cookie.ssafy_id.value ne null}">
		<c:set var="idck" value="checked"></c:set>
		<c:set var="svid" value="${cookie.ssafy_id.value}"></c:set>
	</c:if>
	<div class="container bc">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					로그인
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form id="form-login" method="POST" action="">
					<div class="form-check mb-3 float-end">
						<input class="form-check-input" type="checkbox" value="ok"
							id="saveid" name="saveid" ${idck} /> <label
							class="form-check-label" for="saveid"> 아이디저장 </label>
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							type="text" class="form-control" id="userid" name="userid"
							placeholder="아이디..." value="${svid}" />
					</div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label> <input
							type="password" class="form-control" id="userpwd" name="userpwd"
							placeholder="비밀번호..." />
					</div>
					<div class="text-danger mb-2">${msg}</div>
					<div class="col-auto text-center">
						<button type="button" id="btn-login"
							class="btn btn-outline-primary mb-3">로그인</button>
						<button type="button" id="btn-mv-join"
							class="btn btn-outline-success mb-3">회원가입</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script>
		document.querySelector("#btn-mv-join").addEventListener("click",
				function() {
					location.href = "${root}/user/join";
				});

		document.querySelector("#btn-login").addEventListener("click",
				function() {
					if (!document.querySelector("#userid").value) {
						alert("아이디 입력!!");
						return;
					} else if (!document.querySelector("#userpwd").value) {
						alert("비밀번호 입력!!");
						return;
					} else {
						let form = document.querySelector("#form-login");
						form.setAttribute("action", "${root}/user/login");
						form.submit();
					}
				});
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
