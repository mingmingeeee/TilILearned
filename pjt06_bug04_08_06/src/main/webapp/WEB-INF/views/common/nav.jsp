<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 상단 header -->
<nav class="navbar navbar-expand-sm fixed-top shadow" id="header">
	<div class="container">
		<a class="navbar-brand" href="${root}/"><img
			src="https://w.namu.la/s/2d416e068a2c3412297e3586fe34e2786b68334fced3262b77c744be7162336969490c1ce37f8d79833fc007314c8ac3782de3bebfa0a977e99b17dd09a962a334ea284f99516602ba214fced12ec96e461e80cb827b2b2e598769285bd86a35c4523fa01489707bc0a0a3ca6a4e8a65"
			width="120px" /></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarContent">
			<span class="toggler"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarContent">
			<c:if test="${userinfo eq null}">
				<ul class="navbar-nav justify-content-end ms-auto" id="fail_login"
					style="display: flex">
					<li class="nav-item"><a class="nav-link text-light"
						href="${root}/board/list">게시판</a></li>
					<li class="nav-item"><a class="nav-link text-light"
						href="${root}/house/search_form">오늘의 뉴스</a></li>
					<li class="nav-item"><a class="nav-link text-light"
						data-bs-toggle="modal" href="#loginmodal">로그인</a></li>
					<li class="nav-item"><a class="nav-link text-light"
						id="btn-mv-join1" href="#">회원가입</a></li>
				</ul>
			</c:if>
			<c:if test="${userinfo ne null}">
				<div class="nav-item" id="nav-user-name">
					<strong>${userinfo.userName}</strong> (${userinfo.userId})님<br>
					안녕하세요.
				</div>
				<ul class="navbar-nav justify-content-end ms-auto"
					id="success_login">
					<li class="nav-item"><a class="nav-link text-light"
						href="${root}/board/list">게시판</a></li>
					<li class="nav-item"><a class="nav-link text-light"
						href="${root}/board/search_form">오늘의 뉴스</a></li>
					<li class="nav-item"><a class="nav-link text-light"
						data-bs-toggle="modal" data-bs-target="#exampleModal"
						data-bs-whatever="@getbootstrap" href="#">관심지역 추가</a></li>

					<li class="nav-item"><a class="nav-link text-light"
						href="${root}/apt/getFav2">관심지역</a></li>
					<li class="nav-item"><a class="nav-link text-light"
						href="${root}/logout">로그아웃</a></li>
					<c:if test="${userinfo.userId eq 'admin'}">
						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle text-light" data-bs-toggle="dropdown"
							href="#">관리자</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${root}/user/list">회원관리</a>
								<a class="dropdown-item" href="${root}/houseAdmin/list">주택관리</a>
							</div>
						</li>
					</c:if>
					<li class="nav-item"><a class="nav-link text-light"
						href="${root}/user/userview?clickuserId=${userinfo.userId}">회원정보</a></li>
				</ul>
				<button class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap">관심정보
					등록</button>
			</c:if>
			<!--<c:if test="${!empty userinfo}"><%@ include file="/WEB-INF/views/common/confirm.jsp"%></c:if>-->
		</div>
	</div>
</nav>
<!-- 관심지역 등록 -->
<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">관심 지역</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form method="post" name="favarea">
					<div class="col justify-content-center">
						<div class="form-group">
							<select class="form-select bg-secondary text-light" name="sido"
								id="sido" value="">
								<option value="">시도선택</option>
							</select>
						</div>
						<div class="form-group ">
							<select class="form-select bg-secondary text-light" name="gugun"
								id="gugun" value="">
								<option value="">구군선택</option>
							</select>
						</div>
						<div class="form-group">
							<select class="form-select bg-secondary text-light" name="dong"
								id="dong" value="">
								<option value="">동선택</option>
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-primary" id="saveFavorit">등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<c:if test="${userinfo eq null}">
	<c:if test="${cookie.ssafy_id.value ne null}">
		<c:set var="idck" value=" checked"></c:set>
		<c:set var="svid" value="${cookie.ssafy_id.value}"></c:set>
	</c:if>
	<!-- 로그인 모달 -->
	<div class="modal fade" id="loginmodal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<form method="post" id="form-login-modal">
					<div class="modal-body">
						<div class="form-check mb-3 float-end">
							<input class="form-check-input" type="checkbox" value="ok"
								id="saveid" name="saveid" ${idck} /> <label
								class="form-check-label" for="saveid"> 아이디저장 </label>
						</div>
						<div class="mb-3">
							<label for=userid class="col-form-label">아이디</label> <input
								type="text" class="form-control" id="rguserid" name="userid"
								placeholder="아이디..." value="${svid}" />
						</div>
						<div class="mb-3">
							<label for="userpwd" class="col-form-label">패스워드</label> <input
								type="password" class="form-control" id="rguserpwd"
								name="userpwd" placeholder="비밀번호...">
						</div>
					</div>
					<div class="modal-footer">
						<div class="text-danger mb-2">${msg}</div>
						<button class="btn btn-primary" type="button" id="btn-rglogin"
							data-bs-dismiss="modal">로그인</button>
						<button type="button" id="btn-mv-join"
							class="btn btn-outline-success">회원가입</button>
						<!-- <button class="btn btn-primary" type="button"
								data-bs-target="#findpwdform" data-bs-toggle="modal">비밀번호
								찾기</button>-->
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">Close</button>
					</div>
				</form>
			</div>

		</div>
	</div>
</c:if>
<c:if test="${userinfo eq null}">
	<script>
		// 회원가입 페이지 이동.    
		document.querySelector("#btn-mv-join").addEventListener("click",
				function() {
					location.href = "${root}/user/mvjoin";
				});
		document.querySelector("#btn-mv-join1").addEventListener("click",
				function() {
					location.href = "${root}/user/mvjoin";
				});
		// 로그인
		document.querySelector("#btn-rglogin").addEventListener("click",
				function() {
					if (!document.querySelector("#rguserid").value) {
						alert("아이디 입력!!");
						return;
					} else if (!document.querySelector("#rguserpwd").value) {
						alert("비밀번호 입력!!");
						return;
					} else {
						let form = document.querySelector("#form-login-modal");
						form.setAttribute("action", "${ root }/login");
						form.submit();
					}
				});
		
	</script>
</c:if>
<script type="text/javascript">
	
	//관심지역 등록 
	const exampleModal = document.getElementById('exampleModal')
	exampleModal.addEventListener('show.bs.modal', event => {
		
	  const button = event.relatedTarget

	  const modalTitle = exampleModal.querySelector('.modal-title')
	  const modalBodyInput = exampleModal.querySelector('.modal-body input')

	  modalTitle.textContent = `관심 지역 설정`
		  //let gugunCode =  opener.document.querySelector("#gugun")[this.selectedIndex].value.substr(0, 2) + "*00000";
	  		//let dongCode = opener.document.querySelector("#dong")[this.selectedIndex].value.substr(0, 5) + "*";
	  	
	});
	
	document.querySelector("#saveFavorit").addEventListener("click",function(){
	
	//	let gugunSel = document.querySelector("#gugun");
		//let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0, 5);
		let dongSel = document.querySelector("#dong");
		let dongCode = dongSel[dongSel.selectedIndex].value;
// 연립다세대 API 호출을 위한 요청 fetch 작성
	const rowHouseUrl = '${ root }' + '/apt/saveFav?' + new URLSearchParams({
		regionCode: dongCode,
		dealYmd: "202112"
	});
	
	// db에 저장한다.
	fetch(rowHouseUrl, {
		method: 'POST'
	})
	.then((response) => {
		console.log(response)
		exampleModal.setAttribute("data-bs-dismiss","modal")
	});
	});
	
		
	</script>
<script src="${root2}/assets/js/main2.js"></script>
<!-- 배너 -->
