<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script type="text/x-mathjax-config">
        MathJax.Hub.Config({            
            tex2jax: {inlineMath: [['$','$'], ['\\(','\\)']]}            
        });
    </script>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/latest.js?config=TeX-MML-AM_CHTML'
	async></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/nav.jsp"%>
	<c:if test="${empty apt}">
		<script type="text/javascript">
			alert("정상적인 URL 접근이 아닙니다.");
			location.href = "${root}/";
		</script>
	</c:if>
	<c:if test="${userinfo.userId ne 'admin'}">
		<script type="text/javascript">
			alert("관리자만 접근할 수 있는 페이지입니다.");
			location.href = "${root}/";
		</script>
	</c:if>
	<div class="container">
		<ul class="nav nav-tabs" role="tablist">
			<li class="nav-item" role="presentation"><a
				class="nav-link active" data-bs-toggle="tab" href="#home"
				aria-selected="true" role="tab">주택정보</a></li>
			<li class="nav-item" role="presentation"><a class="nav-link"
				data-bs-toggle="tab" href="#homesale" aria-selected="false"
				role="tab" tabindex="-1">주택거래내역</a></li>
		</ul>
	</div>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade active show" id="home" role="tabpanel">
			<div class="container bc">
				<div class="row justify-content-center">
					<div class="col-lg-9 mt-3">
						<div class="card mb-3">
							<h5 class="card-header">주택정보</h5>
							<div class="card-body">
								<h2 class="card-title">${apt.apartmentName}</h2>
								<h6 class="card-subtitle text-muted mt-1">AptCode -
									${apt.aptCode}</h6>
							</div>
							<div class="card-body">
								<div id="map" style="width: 100%; height: 350px"></div>
								<!-- 지도를 표시할 div 입니다 -->
								<div id="roadview" style="width: 100%; height: 300px"></div>
								<!-- 로드뷰를 표시할 div 입니다 -->
							</div>

							<ul class="list-group list-group-flush">
								<li class="list-group-item">건축년도 : ${apt.buildYear}</li>
								<li class="list-group-item">주소 : ${apt.jibun}</li>
								<li class="list-group-item">위도 : ${apt.lat}</li>
								<li class="list-group-item">경도 : ${apt.lng}</li>
								<li class="list-group-item">도로명 : ${apt.roadName}</li>
								<li class="list-group-item">도로명건물본번호코드 :
									${apt.roadNameBonbun}</li>
								<li class="list-group-item">도로명건물부번호코드 :
									${apt.roadNameBubun}</li>
								<li class="list-group-item">도로명일련번호코드 : ${apt.roadNameSeq}</li>
								<li class="list-group-item">도로명지상지하코드 :
									${apt.roadNameBasementCode}</li>
								<li class="list-group-item">도로명코드 : ${apt.roadNameCode}</li>
								<li class="list-group-item">법정동본번코드 : ${apt.bonbun}</li>
								<li class="list-group-item">법정동부번코드 : ${apt.bubun}</li>
								<li class="list-group-item">법정동시군구코드 : ${apt.sigunguCode}</li>
								<li class="list-group-item">법정동읍면동코드 :
									${apt.eubmyundongCode}</li>
								<li class="list-group-item">법정동지번코드 : ${apt.landCode}</li>
							</ul>
							<!-- <div class="card-body"></div> -->
							<!-- <div class="card-footer text-muted"></div> -->

						</div>
						<div class="d-flex justify-content-end">
							<button type="button" id="btn-list"
								class="btn btn-outline-primary">주택목록</button>
							<button type="button" id="btn-mv-modify"
								class="btn btn-outline-success ms-1">주택정보 수정</button>
							<button type="button" id="btn-delete"
								class="btn btn-outline-danger ms-1">주택 정보 삭제</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="homesale" role="tabpanel">
			<div class="container bc">
				<div class="row justify-content-center">
					<div class="col-lg-8 col-md-10 col-sm-12">
						<h2 class="my-3 py-3 shadow-sm bg-light text-center">주택매매 내역</h2>
					</div>
					<div class="col-lg-8 col-md-10 col-sm-12">
						<div class="row align-self-center mb-2">
							<div class="col-md-3 text-start">
								<button type="button" id="btn-mv-housedeal-register"
									class="btn btn-outline-primary btn-sm">주택매매내역 추가</button>
							</div>
						</div>
						<table class="table table-hover">
							<thead>
								<tr class="text-center">
									<th scope="col">번호</th>
									<th scope="col">아파트 코드번호</th>
									<th scope="col">계약년도</th>
									<th scope="col">계약월</th>
									<th scope="col">계약일</th>
									<th scope="col">거래금액(만원)</th>
									<th scope="col">전용면적($m^{2}$)</th>
									<th scope="col">층</th>
									<th scope="col">수정</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ empty aptSales }">
								<tr class="text-center">
								<td colspan="8">매매 내역이 없습니다.</td></tr>
								</c:if>
								<c:if test="${ !empty aptSales }">
									<c:forEach var="aptSale" items="${aptSales}">
										<tr class="text-center">
											<td class="text-start"><a href="#"
												class="aptsale-title link-dark" data-no="${aptSale.no}"
												style="text-decoration: none"> ${aptSale.no} </a></td>
											<td>${aptSale.aptCode}</td>
											<td>${aptSale.dealYear}</td>
											<td>${aptSale.dealMonth}</td>
											<td>${aptSale.dealDay}</td>
											<td>${aptSale.dealAmount}</td>
											<td>${aptSale.area}</td>
											<td>${aptSale.floor}</td>
											<td><a href="#"
												class="aptsale-modify link-dark" data-no="${aptSale.no}"
												style="text-decoration: none"> 수정 </a></td>
										</tr>
									</c:forEach>
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
		</div>
	</div>
	<form id="form-param" method="post" action="">
		<input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
		<input type="hidden" id="key" name="key" value="${param.key}">
		<input type="hidden" id="word" name="word" value="${param.word}">
	</form>
	<form id="form-no-param" method="post" action="">
		<input type="hidden" id="npgno" name="pgno" value="${param.pgno}">
		<input type="hidden" id="nkey" name="key" value="${param.key}">
		<input type="hidden" id="nword" name="word" value="${param.word}">
		<input type="hidden" id=aptCode name="aptCode" value="${apt.aptCode}">
		<input type="hidden" id=no name="no" value="">
	</form>
	<script>
		document.querySelector("#btn-list").addEventListener("click",
				function() {
					let form = document.querySelector("#form-param");
					form.setAttribute("action", "${root}/houseAdmin/list");
					form.submit();
				});

		document.querySelector("#btn-mv-modify").addEventListener("click",
				function() {
					let form = document.querySelector("#form-no-param");
					form.setAttribute("action", "${root}/houseAdmin/mvmodify");
					form.submit();
				});

		document.querySelector("#btn-delete").addEventListener(
				"click",
				function() {
					if (confirm("정말 삭제하시겠습니까?")) {
						let form = document.querySelector("#form-no-param");
						form.setAttribute("action","${root}/houseAdmin/delete");
						form.submit();
					}
				});
		document.querySelector("#btn-mv-housedeal-register").addEventListener("click",
				function() {
					let form = document.querySelector("#form-no-param");
					document.querySelector("#no").value = this.getAttribute("data-no");
					form.setAttribute("action","${root}/houseAdmin/aptsale/mvwrite");
					form.submit();
					// location.href = "${root}/houseAdmin/aptsale/mvwrite";
				});
		let titles = document.querySelectorAll(".aptsale-title");
		titles.forEach(function(title) {
			title.addEventListener("click", function() {
				if (confirm("정말 삭제하시겠습니까?")) {
				document.querySelector("#no").value = this
						.getAttribute("data-no");
				let form = document.querySelector("#form-no-param");
				form.setAttribute("action","${root}/houseAdmin/aptsale/delete");
				form.submit();
				}
			});
		});
		let modifys = document.querySelectorAll(".aptsale-modify");
		modifys.forEach(function(title) {
			title.addEventListener("click", function() {
				if (confirm("정말 수정하시겠습니까?")) {
				document.querySelector("#no").value = this
						.getAttribute("data-no");
				let form = document.querySelector("#form-no-param");
				form.setAttribute("action","${root}/houseAdmin/aptsale/mvmodify");
				form.submit();
				}
			});
		});
	</script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a0d6bdea2eb0735ecc0dff0c3a7e4add&libraries=LIBRARY"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapCenter = new kakao.maps.LatLng(${apt.lat}, ${apt.lng}), // 지도의 중심좌표
		mapOption = {
			center : mapCenter, // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 커스텀 오버레이에 표시할 내용입니다
		// HTML 문자열 또는 Dom Element 입니다
		var content = '<div class="overlay_info">';
		content += `    <a href="https://map.kakao.com/link/map/${apt.apartmentName},${apt.lat},${apt.lng}" target="_blank"><strong>${apt.apartmentName}</strong></a>`;
		content += '    <div class="desc">';
		/* content += '        <img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/place_thumb.png" alt="">'; */
		content += `        <span class="address"> ${apt.jibun}</span>`;
		content += '    </div>';
		content += '</div>';

		// 커스텀 오버레이가 표시될 위치입니다 
		var position = new kakao.maps.LatLng(${apt.lat}, ${apt.lng});

		// 커스텀 오버레이를 생성합니다
		var mapCustomOverlay = new kakao.maps.CustomOverlay({
			position : position,
			content : content,
			xAnchor : 0.5, // 커스텀 오버레이의 x축 위치입니다. 1에 가까울수록 왼쪽에 위치합니다. 기본값은 0.5 입니다
			yAnchor : 1.1
		// 커스텀 오버레이의 y축 위치입니다. 1에 가까울수록 위쪽에 위치합니다. 기본값은 0.5 입니다
		});

		// 커스텀 오버레이를 지도에 표시합니다
		mapCustomOverlay.setMap(map);

		var rvContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
		var rv = new kakao.maps.Roadview(rvContainer); //로드뷰 객체
		var rvClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

		//지도의 중심좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
		rvClient.getNearestPanoId(mapCenter, 50, function(panoId) {
			rv.setPanoId(panoId, mapCenter); //panoId와 중심좌표를 통해 로드뷰 실행
		});

		kakao.maps.event.addListener(rv, 'init', function() {

			// 커스텀 오버레이를 생성합니다
			var rvCustomOverlay = new kakao.maps.CustomOverlay({
				position : position,
				content : content,
				xAnchor : 0.5, // 커스텀 오버레이의 x축 위치입니다. 1에 가까울수록 왼쪽에 위치합니다. 기본값은 0.5 입니다
				yAnchor : 0.5
			// 커스텀 오버레이의 y축 위치입니다. 1에 가까울수록 위쪽에 위치합니다. 기본값은 0.5 입니다
			});

			//rvCustomOverlay.setAltitude(2); //커스텀 오버레이의 고도값을 설정합니다.(로드뷰 화면 중앙이 0입니다)
			rvCustomOverlay.setMap(rv);

			var projection = rv.getProjection(); // viewpoint(화면좌표)값을 추출할 수 있는 projection 객체를 가져옵니다.

			// 커스텀오버레이의 position과 altitude값을 통해 viewpoint값(화면좌표)를 추출합니다.
			var viewpoint = projection.viewpointFromCoords(rvCustomOverlay
					.getPosition(), rvCustomOverlay.getAltitude());

			rv.setViewpoint(viewpoint); //커스텀 오버레이를 로드뷰의 가운데에 오도록 로드뷰의 시점을 변화 시킵니다.
		});
	</script>
	<link href="${root2}/assets/css/houseinfo.css" rel="stylesheet" />
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
