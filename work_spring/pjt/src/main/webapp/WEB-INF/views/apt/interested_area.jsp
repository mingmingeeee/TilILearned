<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../common/header.jsp"%>
<title>주택 매매 정보</title>
</head>

<body>
	<%@ include file="../common/nav.jsp"%>
	<!-- 중앙 content start -->
	<div class="container bc">
		<div style="height: 70px"></div>
		<h2 class="text-center mt-5 mb-3">주택 
		매매 정보</h2>
		<div class="row col-md-12 justify-content-center mb-2">
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="sido">
					<option value="">시도선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="gugun">
					<option value="">구군선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="dong">
					<option value="">동선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<button type="button" id="list-btn" class="btn btn-outline-primary">
					아파트매매정보<br />가져오기
				</button>
			</div>
			<div class="form-group col-md-2">
				<button type="button" id="housing-btn"
					class="btn btn-outline-primary">
					주택정보<br />가져오기
				</button>
			</div>
		</div>

		<div class="row col-md-12 justify-content-center mb-2">
			<div class="col-md-3">
				<h3 class="border-bottom">거래정보</h3>
				<div id="houselist"></div>
			</div>
			<div class="col-md-9 test" id="map" style="width: 75%; height: 400px"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=22df7f0061e34a10374b0ed8307bd574&libraries=services"></script>
			<script>
				var mapContainer = document.getElementById("map"), // 지도를 표시할 div
				mapOption = {
					center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
					level : 3, // 지도의 확대 레벨
				};

				// 지도를 생성합니다
				var map = new kakao.maps.Map(mapContainer, mapOption);

				// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
				var mapTypeControl = new kakao.maps.MapTypeControl();

				// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
				// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
				map.addControl(mapTypeControl,
						kakao.maps.ControlPosition.TOPRIGHT);

				// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
				var zoomControl = new kakao.maps.ZoomControl();
				map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder();
			</script>
		</div>
	</div>
	<script src="${root}/assets/js/favlist_event.js"></script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>