<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/nav.jsp"%>
<c:if test="${articles == null}">
	<script type="text/javascript">
		alert("정상적인 URL 접근이 아닙니다.");
		location.href = "${root}/board/list?pgno=1&key=&word=";
	</script>
</c:if>
<div class="container bc">
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				게시판
			</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
		<div id="product_order_list">
			<p  class="text-end">
				<a id="form-sort-latest" class="link-dark" style="text-decoration: none" href="#">최신순</a>&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
				<a id="form-sort-view" class="link-dark" style="text-decoration: none" href="#">조회순</a>
			</p>
		</div>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<div class="row align-self-center mb-2">
				<div class="col-md-2 text-start">
					<button type="button" id="btn-mv-register"
						class="btn btn-outline-primary btn-sm">글쓰기</button>
				</div>
				<div class="col-md-7 offset-3">
					<form class="d-flex" id="form-search" action="">
						<select class="form-select form-select-sm ms-5 me-1 w-50"
							name="key" aria-label="검색조건">
							<option value="" selected>검색조건</option>
							<option value="subject">제목</option>
							<option value="userid">작성자</option>
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
						<th scope="col">글번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">조회수</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${articles}">
						<tr class="text-center">
							<th scope="row">${article.articleNo}</th>
							<td class="text-start"><a href="#"
								class="article-title link-dark" data-no="${article.articleNo}"
								style="text-decoration: none"> ${article.subject} </a></td>
							<td>${article.userId}</td>
							<td>${article.hit}</td>
							<td>${article.registerTime}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty  articles}">
						<tr class="text-center">
							<th colspan="5" scope="col">조회된 게시글이 없습니다.</th>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="row">
			<!-- pagination{s} -->
			<div id="paginationBox">
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
			<!-- pagination{e} -->

		</div>
	</div>
</div>
<form id="form-no-param" method="get" action="${root}/board/view">
	<input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
	<input type="hidden" id="key" name="key" value="${param.key}">
	<input type="hidden" id="word" name="word" value="${param.word}">
	<input type="hidden" id="articleno" name="articleno" value="">
	<input type="hidden" id="sort" name="sort" value="">
</form>
<script>
	let titles = document.querySelectorAll(".article-title");
	titles.forEach(function(title) {
		title.addEventListener("click", function() {
			document.querySelector("#articleno").value = this
					.getAttribute("data-no");
			document.querySelector("#form-no-param").submit();
		});
	});

	document.querySelector("#btn-mv-register").addEventListener("click",
			function() {
				location.href = "${root}/board/mvwrite";
			});

	document.querySelector("#btn-search").addEventListener("click", function() {
		let form = document.querySelector("#form-search");
		form.setAttribute("action", "${root}/board/list?");
		form.submit();
	});
	document.querySelector("#form-sort-latest").addEventListener("click", function() {
		let form = document.querySelector("#form-no-param");
		document.querySelector("#sort").value = "form-sort-latest";
		form.setAttribute("action", "${root}/board/list?");
		form.submit();
	});
	document.querySelector("#form-sort-view").addEventListener("click", function() {
		let form = document.querySelector("#form-no-param");
		document.querySelector("#sort").value = "form-sort-view";
		form.setAttribute("action", "${root}/board/list?");
		form.submit();
	});
</script>
<script>
	//이전 버튼 이벤트
	function fn_prev(page, range, rangeSize) {
		var page = ((range - 2) * rangeSize) + 1;
		var range = range - 1;
		var url = "${root}/board/list?";
		url = url + "pgno=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}
	//페이지 번호 클릭
	function fn_pagination(page, range, rangeSize, searchType, keyword) {
		var url = "${root}/board/list?";
		url = url + "pgno=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}
	//다음 버튼 이벤트
	function fn_next(page, range, rangeSize) {
		var page = parseInt((range * rangeSize)) + 1;
		var range = parseInt(range) + 1;
		var url = "${root}/board/list?";
		url = url + "pgno=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}
</script>
<%@ include file="/common/footer.jsp"%>
