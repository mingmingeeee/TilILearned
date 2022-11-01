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
		<h2 class="text-center mt-5 mb-3">주택 매매 정보</h2>
		<div class="row col-md-12 justify-content-center mb-2">
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="aptsido">
					<option name="sido" value="">시도선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="aptgugun">
					<option name="gugun" value="">구군선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="aptdong">
					<option name="dong" value="">동선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<button type="button" id="list-btn"
					class="btn btn-outline-primary col-md-12">
					아파트매매정보<br />가져오기
				</button>
			</div>
			<div class="form-group col-md-2">
				<button type="button" id="housing-btn"
					class="btn btn-outline-primary col-md-12">
					주택매매정보<br />가져오기
				</button>
			</div>

		</div>
		<div class="row col-md-12 justify-content-center mb-2">
			<div></div>
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
              center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
              level: 3, // 지도의 확대 레벨
            };

          // 지도를 생성합니다
          var map = new kakao.maps.Map(mapContainer, mapOption);

          // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
          var mapTypeControl = new kakao.maps.MapTypeControl();

          // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
          // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
          map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

          // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
          var zoomControl = new kakao.maps.ZoomControl();
          map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
          // 주소-좌표 변환 객체를 생성합니다
          var geocoder = new kakao.maps.services.Geocoder();
        </script>
		</div>
	</div>
	<script type="text/javascript">
	
    		
        // 시도 정보 얻기
        
        const url = "${root}" + '/housetwo/getSIDO';
        fetch(url, {
          method: "GET",
        })
          .then((response) => response.json())
          .then((data) => {
            let options = `<option value="">시도선택</option>`;
            Object.keys(data).forEach(function (key) {
              options += `<option value="\${ key }">\${ data[key] }</option>`;
            });

            document.querySelector("#aptsido").innerHTML = options;
          });
        document.querySelector("#aptsido").addEventListener("change", function () {
          let sidoCode = this[this.selectedIndex].value;

          // 구군 정보 얻기
          const url = "${root}" + '/housetwo/getGUGUN?' +
            new URLSearchParams({
              sidoCode: sidoCode,
            });

          fetch(url, {
            method: "GET",
          })
            .then((response) => response.json())
            .then((data) => {
              let options = `<option value="">구군선택</option>`;
              Object.keys(data).forEach(function (key) {
                options += `<option value="\${ key }">\${ data[key] }</option>`;
              });

              document.querySelector("#aptgugun").innerHTML = options;
            });
        });

        document.querySelector("#aptgugun").addEventListener("change", function () {
          let gugunCode = this[this.selectedIndex].value;

          // 동 정보 얻기
          const url =
            "${root}" +
            "/housetwo/getDONG?" +
            new URLSearchParams({
              gugunCode: gugunCode,
            });

          fetch(url, {
            method: "GET",
          })
            .then((response) => response.json())
            .then((data) => {
              let options = `<option value="">동선택</option>`;
              Object.keys(data).forEach(function (key) {
                options += `<option value="\${ key }">\${ data[key] }</option>`;
              });

              document.querySelector("#aptdong").innerHTML = options;
            });
        });
        
      document.querySelector("#list-btn").addEventListener("click", function () {
        let sidoSel1 = document.querySelector("#aptsido");
        let gugunSel1 = document.querySelector("#aptgugun");
        let dongSel1 = document.querySelector("#aptdong");
        let address =
          sidoSel1[sidoSel1.selectedIndex].text +
          " " +
          gugunSel1[gugunSel1.selectedIndex].text +
          " " +
          dongSel1[dongSel1.selectedIndex].text;
        // 주소 전달
        const url =
          "${root}" +
          "/housetwo/aptlist?" +
          new URLSearchParams({
            sidoName: sidoSel1[sidoSel1.selectedIndex].text,
            gugunName: gugunSel1[gugunSel1.selectedIndex].text,
            dongName: dongSel1[dongSel1.selectedIndex].text,
            dongCode: dongSel1[dongSel1.selectedIndex].value,
          });

        fetch(url, {
          method: "GET",
        })	
          .then((response) => response.json())
          .then((data) => {
        	makeAptList(data);
            console.log(data);
            /* for (let i = 0; i < data.length; i++) { //JSONArray 내 json 개수만큼 for문 동작
					console.log(data[i]);
					console.log(data[i]["apartmentName"]);
				} */
          });
      });
   // 연립다세대 API 호출을 위한 요청 fetch 작성
    document.querySelector("#housing-btn").addEventListener("click", function () {
         let dongSel1 = document.querySelector("#aptdong");
		const rowHouseUrl = '${ root }' + '/rest/house/row-house/trade?' + new URLSearchParams({
			regionCode: dongSel1.value.slice(0, 5),
			dealYmd: "202112"
		});
		
		fetch(rowHouseUrl, {
			method: 'GET'
		})
		.then((response) => response.json())
		.then((data) => {
			let houses = data.response.body.items.item;
			makehouseList(houses);
		});
    });
		

    </script>
	<script type="text/javascript" src="${root2 }/assets/js/main2.js"></script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
