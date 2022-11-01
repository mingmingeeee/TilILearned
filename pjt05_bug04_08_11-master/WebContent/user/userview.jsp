<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<c:if test="${userinfo eq null}">
	<script type="text/javascript">
		alert("회원정보가 삭제되었거나 정상적인 URL 접근이 아닙니다.");
		location.href = "${root}/index.jsp";
	</script>
</c:if>

<body>
	<%@ include file="/common/nav.jsp"%>
	<div class="container bc">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					회원정보
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form id="form-join" method="POST" action="">
					<!-- <input type="hidden" name="act" value="join"> -->
					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label>
						<div class="fw-bold input-group-text">${user.userName}</div>
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label>
						<div class="fw-bold input-group-text">${user.userId}</div>
					</div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label>
						<div class="fw-bold input-group-text">${user.userPwd}</div>
					</div>
					<div class="mb-3">
						<label for="emailid" class="form-label">이메일 : </label> <span
							class="fw-bold input-group-text">${user.emailId}@${user.emailDomain}</span>
					</div>
					<div class="mb-3">
						<label for="emailid" class="form-label">가입일자 : </label> <span
							class="fw-bold input-group-text">${user.joinDate}</span>
					</div>
					<div class="col-auto text-center">
						<c:if
							test="${ (userinfo.userId eq user.userId) or (userinfo.userId eq 'admin')}">
							<button type="button" id="btn-mv-modify"
								class="btn btn-outline-success mb-3 ms-1">회원정보수정</button>
							<button type="button" id="btn-delete"
								class="btn btn-outline-danger mb-3 ms-1">회원정보삭제</button>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	<form id="form-no-param" method="post" action="${root}/user">
		<input type="hidden" id="nuserid" name="userId" value="${user.userId}" />
	</form>
	<script>
		document.querySelector("#btn-mv-modify").addEventListener("click",
				function() {
					let form = document.querySelector("#form-no-param");
					form.setAttribute("action", "${root}/user/mvusermodify");
					form.submit();
				});
		document.querySelector("#btn-delete").addEventListener("click",
				function() {
					if (confirm("정말 삭제하시겠습니까?")) {
						let form = document.querySelector("#form-no-param");
						form.setAttribute("action", "${root}/user/delete");
						form.submit();
					}
				});
	</script>
	<%@ include file="/common/footer.jsp"%>
</body>
