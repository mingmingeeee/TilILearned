<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/header.jsp"%>
<title>관심지역 정보</title>
</head>

<body>
	<%@ include file="/common/nav.jsp"%>
	<!-- 중앙 content start -->
	<div class="container bc">
		<div style="height: 70px"></div>
		<h2 class="text-center mt-5 mb-3">관심 지역 정보</h2>

		<div class="row col-md-12 justify-content-center mb-2">
			<div class="col-md-3">
				<h3 class="border-bottom">관심지역 목록</h3>
				<div id="favList"></div>
			</div>
			<div class="col-md-9 test" style="width: 75%; height: 800px">
				<div class="btn-group" role="group"
					aria-label="Basic checkbox toggle button group" name="category"
					id="category">
					<input type="checkbox" class="btn-check" name="category" id="HP8"
						autocomplete="off"> <label class="btn btn-outline-primary"
						value="hospital" for="HP8">병원</label> <input type="checkbox"
						class="btn-check" name="category" id="CS2" autocomplete="off">
					<label class="btn btn-outline-primary" value="convenience"
						for="CS2">편의점</label> <input type="checkbox" class="btn-check"
						name="category" id="SC4" autocomplete="off"> <label
						class="btn btn-outline-primary" value="school" for="SC4">학교</label>
				</div>
				<div class="col-md-12 test" id="map"
					style="width: 100%; height: 400px"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=22df7f0061e34a10374b0ed8307bd574&libraries=services"></script>
				<h3 class="border-bottom">상세 거래정보</h3>
				<div id="houseDealInfo" style="height: 300px"></div>
			</div>
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

			      window.onload = function () {

			    	  document.querySelector("#category").addEventListener("click", function(){
			    	    	const otherCheckbox = document.querySelector('#category');
			    	    	var aptList=[]
			    	    	var chkList = document.querySelectorAll("input[name=category]:checked");
			    	    	if (chkList.length>0) {
			    	    		console.log(chkList[0].id)
			    	    		//해당 카테고리 기반으로 요청을 보낸다.
			    	    		var position = map.getCenter();

			    	    		const url = "${root}" + '/house/getLocalInfo?categoryCode='+chkList[0].id;
			    	            fetch(url, {
			    	                method: "GET",
			    	              })
			    	      		.then((response) => response.json())
			    	    		.then((data) => {
			    	    			let houses = data
			    	    			makeFavList(houses);
			    	    		});
			    	        }
			    	    })
			      }
			</script>
			<c:if test="${! empty favorites }">
				<script type="text/javascript">
					getMyFavList(${favorites}[0]["dongCode"])
					makeFavList(${favorites});
				console.log("favorite is not empty");
				</script>
			</c:if>
			<c:if test="${empty favorites }">
			console.log("favorite is empty")
			</c:if>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>