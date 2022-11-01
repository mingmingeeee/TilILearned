<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<script type="text/x-mathjax-config">
        MathJax.Hub.Config({            
            tex2jax: {inlineMath: [['$','$'], ['\\(','\\)']]}            
        });
</script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/latest.js?config=TeX-MML-AM_CHTML' async></script>
<%@ include file="/common/nav.jsp"%>
<div class="container bc">
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				주택매매정보 등록
			</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<form id="form-register" method="POST" action="">
				<input type="hidden" id="aptCode" name="aptCode" value="${aptCode}">
				<div class="mb-3">
					<label for="dealDate" class="form-label">계약년도 : </label>
					<input type="date" class="form-control" id="dealDate" name="dealDate"
						placeholder="계약년도" />
				</div>
				<div class="mb-3">
					<label for="dealAmount" class="form-label">거래금액(만원) : </label>
					<input type="text" class="form-control" id="dealAmount" name="dealAmount"
						placeholder="거래금액" />
				</div>
				<div class="mb-3">
					<label for="area" class="form-label">전용면적($m^{2}$) : </label>
					<input type="text" class="form-control" id="area" name="area"
						placeholder="전용면적" />
				</div>
				<div class="mb-3">
					<label for="floor" class="form-label">층 : </label>
					<input type="text" class="form-control" id="floor" name="floor"
						placeholder="층" />
				</div>
				<div class="col-auto text-center">
					<button type="button" id="btn-register"
						class="btn btn-outline-primary mb-3">주택매매정보 등록</button>
					<button type="button" id="btn-list"
						class="btn btn-outline-danger mb-3">목록으로이동...</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	document.querySelector("#btn-register").addEventListener("click",
			function() {
				if (!document.querySelector("#dealDate").value) {
					alert("계약일을 입력하세요");
					return;
				} else if (!document.querySelector("#dealAmount").value) {
					alert("거래금액을 입력하세요");
					return;
				}  else if (!document.querySelector("#area").value) {
					alert("전용면적을 입력하세요");
					return;
				}  else if (!document.querySelector("#floor").value) {
					alert("층을 입력하세요");
					return;
				} else {
					let form = document.querySelector("#form-register");
					form.setAttribute("action", "${root}/houseAdmin/aptsale/write");
					
					form.submit();
				}
			});

	document.querySelector("#btn-list").addEventListener("click", function() {
		if (confirm("확인을 누르면 작성중인 주택매매정보는 삭제됩니다.\n취소하시겠습니까?")) {
			location.href = "${root}/houseAdmin/list";
		}
	});
</script>
<%@ include file="/common/footer.jsp"%>
