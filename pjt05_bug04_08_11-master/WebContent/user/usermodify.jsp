<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
	<%@ include file="/common/nav.jsp"%>
	<div class="container bc">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					회원정보 수정
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form id="form-usermodify" method="POST" action="">
					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label> <input
							type="text" class="form-control" id="username" name="username"
							value="${user.userName}" />
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							hidden name="userId" value="${user.userId}" />
						<div class="fw-bold input-group-text" id="userid">${user.userId}</div>
					</div>
					<div id="idcheck-result"></div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label> <input
							type="password" class="form-control" id="userpwd" name="userpwd"
							placeholder="비밀번호..." />
					</div>
					<div class="mb-3">
						<label for="pwdcheck" class="form-label">비밀번호확인 : </label> <input
							type="password" class="form-control" id="pwdcheck"
							placeholder="비밀번호확인..." />
					</div>
					<div class="mb-3">
						<label for="emailid" class="form-label">이메일 : </label>
						<div class="input-group">
							<input type="text" class="form-control" id="emailid"
								name="emailid" value="${user.emailId}" /> <span
								class="input-group-text">@</span> <select class="form-select"
								id="emaildomain" name="emaildomain" aria-label="이메일 도메인 선택">
								<option selected>선택</option>
								<option value="ssafy.com">싸피</option>
								<option value="google.com">구글</option>
								<option value="naver.com">네이버</option>
								<option value="kakao.com">카카오</option>
							</select>
						</div>
					</div>
					<div class="col-auto text-center">
						<button type="button" id="btn-modify"
							class="btn btn-outline-primary mb-3">수정완료</button>
						<!-- <button type="button" class="btn btn-outline-success mb-3">초기화</button> -->
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		document
				.querySelector("#btn-modify")
				.addEventListener(
						"click",
						function() {
							if (!document.querySelector("#username").value) {
								alert("이름 입력!!");
								return;
							} else if (!document.querySelector("#userpwd").value) {
								alert("비밀번호 입력!!");
								return;
							} else if (document.querySelector("#userpwd").value != document
									.querySelector("#pwdcheck").value) {
								alert("비밀번호 확인!!");
								return;
							} else {
								let form = document
										.querySelector("#form-usermodify");
								form.setAttribute("action",
										"${root}/user/usermodify");
								form.submit();
							}
						});
	</script>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
