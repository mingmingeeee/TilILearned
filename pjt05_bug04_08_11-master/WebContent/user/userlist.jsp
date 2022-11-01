<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
	<%@ include file="/common/nav.jsp"%>
	<c:if test="${empty userinfo}">
		<script type="text/javascript">
			alert("정상적인 URL 접근이 아닙니다.");
			location.href = "${root}/";
		</script>
	</c:if>
	<div class="contatiner bc">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					회원목록
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<div class="row align-self-center mb-2">
					<div class="col-md-2 text-start">
						<button type="button" id="btn-mv-userregister"
							class="btn btn-outline-primary btn-sm">회원추가</button>
					</div>
					<div class="col-md-7 offset-3">
						<form class="d-flex" id="form-search" action="">
							<select
								class="form-select form-select-sm ms-5 me-1 w-50" name="key"
								aria-label="검색조건">
								<option value="" selected>검색조건</option>
								<option value="subject">회원이름</option>
								<option value="userid">아이디</option>
							</select>
							<div class="input-group input-group-sm">
								<input type="text" class="form-control" name="word"
									placeholder="검색어..." />
								<button id="btn-search" class="btn btn-dark" type="button">검색</button>
							</div>
						</form>
					</div>
				</div>
				<table class="table table-hover">
					<thead>
						<tr class="text-center">
							<th scope="col">아이디</th>
							<th scope="col">이름</th>
							<th scope="col">이메일</th>
							<th scope="col">가입일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr class="text-center">
								<th scope="row">${user.userId}</th>
								<td class="text-start"><a href="#"
									class="user-title link-dark" data-no="${user.userId}"
									style="text-decoration: none"> ${user.userName} </a></td>
								<td>${user.emailId}@${user.emailDomain}</td>
								<td>${user.joinDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="row">
				<ul class="pagination justify-content-center">
					<c:if test="${pagination.prev}">
						<li class="page-item"><a class="page-link" href="#"
							onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Previous</a></li>
					</c:if>
					<c:forEach begin="${pagination.startPage}"
						end="${pagination.endPage}" var="idx">
						<li
							class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> "><a
							class="page-link" href="#"
							onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')">
								${idx} </a></li>
					</c:forEach>
					<c:if test="${pagination.next}">
						<li class="page-item"><a class="page-link" href="#"
							onClick="fn_next('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Next</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<form id="form-no-param" method="post" action="${root}/user/userview">
		<input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
		<input type="hidden" id="key" name="key" value="${param.key}">
		<input type="hidden" id="word" name="word" value="${param.word}">
		<input type="hidden" id="clickuserId" name="clickuserId" value="">
	</form>
	<script>
		let titles = document.querySelectorAll(".user-title");
		titles.forEach(function(title) {
			title.addEventListener("click", function() {
				document.querySelector("#clickuserId").value = this
						.getAttribute("data-no");
				document.querySelector("#form-no-param").submit();
			});
		});

		document.querySelector("#btn-search").addEventListener("click",
				function() {
					let form = document.querySelector("#form-search");
					form.setAttribute("action", "${root}/user/userlist?");
					form.submit();
				});
		
		document.querySelector("#btn-mv-userregister").addEventListener("click",
				function() {
					location.href = "${root}/user/mvjoin";
				});
	</script>
	<script>
		//이전 버튼 이벤트
		function fn_prev(page, range, rangeSize) {
			var page = ((range - 2) * rangeSize) + 1;
			var range = range - 1;
			var url = "${root}/userlist";
			url = url + "&pgno=" + page;
			url = url + "&range=" + range;
			location.href = url;
		}
		//페이지 번호 클릭
		function fn_pagination(page, range, rangeSize, searchType, keyword) {
			var url = "${root}/userlist";
			url = url + "&pgno=" + page;
			url = url + "&range=" + range;
			location.href = url;
		}
		//다음 버튼 이벤트
		function fn_next(page, range, rangeSize) {
			var page = parseInt((range * rangeSize)) + 1;
			var range = parseInt(range) + 1;
			var url = "${root}/userlist";
			url = url + "&pgno=" + page;
			url = url + "&range=" + range;
			location.href = url;
		}
	</script>
	<%@ include file="/common/footer.jsp"%>

</body>
</html>