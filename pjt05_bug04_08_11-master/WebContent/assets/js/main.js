///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
let date = new Date();

window.onload = function () {
	// 브라우저가 열리면 시도정보 얻기.
	sendRequest("sido", "*00000000");
};

//https://juso.dev/docs/reg-code-api/
//let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
//let regcode = "*00000000";
//전국 특별/광역시, 도
//https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000

//시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", function () {
	if (this[this.selectedIndex].value) {
		let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
		sendRequest("gugun", regcode);
	} else {
		initOption("gugun");
		initOption("dong");
	}
});

//구군이 바뀌면 동정보 얻기.
document.querySelector("#gugun").addEventListener("change", function () {
	if (this[this.selectedIndex].value) {
		let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
		sendRequest("dong", regcode);
	} else {
		initOption("dong");
	}
});

function sendRequest(selid, regcode) {
	const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
	let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
	fetch(`${url}?${params}`)
	.then((response) => response.json())
	.then((data) => addOption(selid, data));
}

function addOption(selid, data) {
	let opt = ``;
	initOption(selid);
	switch (selid) {
	case "sido":
		opt += `<option value="">시도선택</option>`;
		data.regcodes.forEach(function (regcode) {
			opt += `
				<option value="${regcode.code}">${regcode.name}</option>
				`;
		});
		break;
	case "gugun":
		opt += `<option value="">구군선택</option>`;
		for (let i = 0; i < data.regcodes.length; i++) {
			if (i != data.regcodes.length - 1) {
				if (
						data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
						data.regcodes[i].name.split(" ").length !=
							data.regcodes[i + 1].name.split(" ").length
				) {
					data.regcodes.splice(i, 1);
					i--;
				}
			}
		}
		let name = "";
		data.regcodes.forEach(function (regcode) {
			if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
			else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
			opt += `
				<option value="${regcode.code}">${name}</option>
				`;
		});
		break;
	case "dong":
		opt += `<option value="">동선택</option>`;
		let idx = 2;
		data.regcodes.forEach(function (regcode) {
			if (regcode.name.split(" ").length != 3) idx = 3;
			opt += `
				<option value="${regcode.code}">${regcode.name.split(" ")[idx]}</option>
				`;
		});
	}
	document.querySelector(`#${selid}`).innerHTML = opt;
}

function initOption(selid) {
	let options = document.querySelector(`#${selid}`);
	options.length = 0;
}
//
/////////////////////////// 아파트 매매 정보 /////////////////////////
//document.querySelector("#list-btn").addEventListener("click", function () {
//	let url =
//		"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
//	let gugunSel = document.querySelector("#gugun");
//	let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0, 5);
//	let dealYM = "202112";
//	let queryParams =
//		encodeURIComponent("serviceKey") +
//		"=" +
//		"tVjgW6ovTuqohBqi6D6Gf%2B6MndIEx3CVmdwPQL7F8SCduFG6bWYlbRnuA7RVc%2Bzgf75D5UHpGLNVVDye4W%2BWvQ%3D%3D"; /*Service Key*/
//	queryParams +=
//		"&" +
//		encodeURIComponent("LAWD_CD") +
//		"=" +
//		encodeURIComponent(regCode); /*아파트소재 구군*/
//	queryParams +=
//		"&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(dealYM); /*조회년월*/
//	queryParams +=
//		"&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1"); /*페이지번호*/
//	queryParams +=
//		"&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent("30"); /*페이지당건수*/
//
//	fetch(`${url}?${queryParams}`)
//	.then((response) => response.text())
//	.then((data) => makehouseList(data));
//});

//다세대
document.querySelector("#housing-btn").addEventListener("click", function () {
	let url2 =
		"http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade";
	let gugunSel = document.querySelector("#gugun");
	let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0, 5);
	let dealYM = "202112";
	let queryParams =
		encodeURIComponent("serviceKey") +
		"=" +
		"1CCpQofcu%2BF732VXbRualdEBulu%2BXJO9eWA4Y69MOKFgF2RC2mEJ8VGNS4xoDPgLGfH0AU9h0ED1l8s121pL0A%3D%3D"; /*Service Key*/
	queryParams +=
		"&" +
		encodeURIComponent("LAWD_CD") +
		"=" +
		encodeURIComponent(regCode); /*다세대소재 구군*/
	queryParams +=
		"&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(dealYM); /*조회년월*/
	queryParams +=
		"&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1"); /*페이지번호*/
	queryParams +=
		"&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent("30"); /*페이지당건수*/

	fetch(`${url2}?${queryParams}`)
	.then((response) => response.text())
	.then((data) => makehousesList(data));
});

var saleinfo;
function makehouseList(data) {
	saleinfo = data;
	let listDiv = document.querySelector("#houselist");
	let parser = new DOMParser();
	const xml = parser.parseFromString(data, "application/xml");

	initHouselist();
	let apts = xml.querySelectorAll("item");
	apts.forEach((apt) => {
		let divEl = document.createElement("div");
		divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");

		let nameTd = document.createElement("a");
		nameTd.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
		nameTd.setAttribute("class", "fw-bold fs-5 text-decoration-none");

		let sidoSel1 = document.querySelector("#sido");
		let gugunSel1 = document.querySelector("#gugun");
		let address2 =
			sidoSel1[sidoSel1.selectedIndex].text +
			" " +
			gugunSel1[gugunSel1.selectedIndex].text +
			" " +
			apt.querySelector("법정동").textContent;
		let address = address2 + " " + apt.querySelector("지번").textContent;



		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address, function (result, status) {
			// 정상적으로 검색이 완료됐으면
			if (status === kakao.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				nameTd.setAttribute("onclick", `panTo(${(result[0].x *= 1)}, ${(result[0].y *= 1)})`);
			}
		});

		divEl.appendChild(nameTd);

		let floorTd = document.createElement("div");
		floorTd.appendChild(document.createTextNode(apt.querySelector("층").textContent + "층"));
		divEl.appendChild(floorTd);

		let areaTd = document.createElement("div");
		areaTd.appendChild(
				document.createTextNode("면적: " + apt.querySelector("전용면적").textContent)
		);
		divEl.appendChild(areaTd);

		let dongTd = document.createElement("div");
		dongTd.appendChild(
				document.createTextNode("법정동: " + apt.querySelector("법정동").textContent)
		);
		divEl.appendChild(dongTd);

		let priceTd = document.createElement("div");
		priceTd.appendChild(
				document.createTextNode(
						"거래금액: " + apt.querySelector("거래금액").textContent + "만원"
				)
		);
		priceTd.classList.add("text-end");

		divEl.appendChild(priceTd);

		listDiv.appendChild(divEl);
		/* 주소 변환*/

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address, function (result, status) {
			// 정상적으로 검색이 완료됐으면
			if (status === kakao.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				// 결과값으로 받은 위치를 마커로 표시합니다
				var marker = new kakao.maps.Marker({
					map: map,
					position: coords,
				});

				// 인포윈도우로 장소에 대한 설명을 표시합니다
				var infowindow = new kakao.maps.InfoWindow({
					content: `<div style="width:150px;text-align:center;padding:6px 0;">${
						apt.querySelector("아파트").textContent
						}<br>${apt.querySelector("거래금액").textContent + "만원"}</div>`,
				});

				kakao.maps.event.addListener(
						marker,
						"mouseover",
						makeOverListener(map, marker, infowindow)
				);
				kakao.maps.event.addListener(marker, "mouseout", makeOutListener(infowindow));

				// 마커에 click 이벤트를 등록합니다
				kakao.maps.event.addListener(
						marker,
						"click",
						makeClickListener(map, marker, infowindow, apt.querySelector("아파트").textContent,"아파트")
				);
				//infowindow.open(map, marker);

				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				map.setCenter(coords);
				map.setLevel(6);
			}
		});

		/* 주소 변환 끝*/
	});
}

function makehousesList(data) {
	saleinfo = data;
	let listDiv = document.querySelector("#houselist");
	let parser = new DOMParser();
	const xml = parser.parseFromString(data, "application/xml");

	initHouselist();
	let apts = xml.querySelectorAll("item");
	apts.forEach((apt) => {
		let divEl = document.createElement("div");
		divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");

		let nameTd = document.createElement("a");
		nameTd.appendChild(document.createTextNode(apt.querySelector("연립다세대").textContent));
		nameTd.setAttribute("class", "fw-bold fs-5 text-decoration-none");

		let sidoSel1 = document.querySelector("#sido");
		let gugunSel1 = document.querySelector("#gugun");
		let address2 =
			sidoSel1[sidoSel1.selectedIndex].text +
			" " +
			gugunSel1[gugunSel1.selectedIndex].text +
			" " +
			apt.querySelector("법정동").textContent;
		let address = address2 + " " + apt.querySelector("지번").textContent;



		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address, function (result, status) {
			// 정상적으로 검색이 완료됐으면
			if (status === kakao.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				nameTd.setAttribute("onclick", `panTo(${(result[0].x *= 1)}, ${(result[0].y *= 1)})`);
			}
		});

		divEl.appendChild(nameTd);

		let floorTd = document.createElement("div");
		floorTd.appendChild(document.createTextNode(apt.querySelector("층").textContent + "층"));
		divEl.appendChild(floorTd);

		let areaTd = document.createElement("div");
		areaTd.appendChild(
				document.createTextNode("면적: " + apt.querySelector("전용면적").textContent)
		);
		divEl.appendChild(areaTd);

		let dongTd = document.createElement("div");
		dongTd.appendChild(
				document.createTextNode("법정동: " + apt.querySelector("법정동").textContent)
		);
		divEl.appendChild(dongTd);

		let priceTd = document.createElement("div");
		priceTd.appendChild(
				document.createTextNode(
						"거래금액: " + apt.querySelector("거래금액").textContent + "만원"
				)
		);
		priceTd.classList.add("text-end");

		divEl.appendChild(priceTd);

		listDiv.appendChild(divEl);
		/* 주소 변환*/

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address, function (result, status) {
			// 정상적으로 검색이 완료됐으면
			if (status === kakao.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				// 결과값으로 받은 위치를 마커로 표시합니다
				var marker = new kakao.maps.Marker({
					map: map,
					position: coords,
				});

				// 인포윈도우로 장소에 대한 설명을 표시합니다
				var infowindow = new kakao.maps.InfoWindow({
					content: `<div style="width:150px;text-align:center;padding:6px 0;">${
						apt.querySelector("연립다세대").textContent
						}<br>${apt.querySelector("거래금액").textContent + "만원"}</div>`,
				});

				kakao.maps.event.addListener(
						marker,
						"mouseover",
						makeOverListener(map, marker, infowindow)
				);
				kakao.maps.event.addListener(marker, "mouseout", makeOutListener(infowindow));

				// 마커에 click 이벤트를 등록합니다
				kakao.maps.event.addListener(
						marker,
						"click",
						makeClickListener(map, marker, infowindow, apt.querySelector("연립다세대").textContent,"연립다세대")
				);
				//infowindow.open(map, marker);

				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				map.setCenter(coords);
				map.setLevel(6);
			}
		});

		/* 주소 변환 끝*/
	});
}

function initHouselist() {
	let listDiv = document.querySelector("#houselist");
	while (listDiv.hasChildNodes()) {
		listDiv.removeChild(listDiv.firstChild);
	}
}

function makeClickListener(map, marker, infowindow, aptname,type) {
	return function () {
		var pos = marker.getPosition();
		console.log(pos);
		map.setLevel(5);
		map.panTo(pos);
		click_apt_name = aptname;
		makehouseSaleList(saleinfo, pos,type);
	};
}
var click_apt_name;
//인포윈도우를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map, marker, infowindow) {
	return function () {
		infowindow.open(map, marker);
	};
}

//인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
	return function () {
		infowindow.close();
	};
}

function makehouseSaleList(data, pos,type) {
	initHouselist();
	let listDiv = document.querySelector("#houselist");
	let divEl1 = document.createElement("div");
	divEl1.setAttribute(
			"class",
			"houseinfo border border-3 rounded-2 p-3 text-white bg-opacity-10"
	);
	divEl1.setAttribute("style", "background-color: #204981;");

	let nameTd1 = document.createElement("h5");
	nameTd1.appendChild(document.createTextNode(click_apt_name));
	nameTd1.setAttribute("class", "fw-bold");

	divEl1.appendChild(nameTd1);
	listDiv.appendChild(divEl1);
	let parser = new DOMParser();
	const xml = parser.parseFromString(data, "application/xml");

	// 한 아파트의 거래 정보 얻어오기
	let apts = xml.querySelectorAll("item");
	apts.forEach((apt) => {
		if (apt.querySelector(type).textContent == click_apt_name) {
			let divEl = document.createElement("div");
			divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");

			let nameTd = document.createElement("a");
			nameTd.setAttribute("class", "fw-bold fs-5 text-decoration-none");
			nameTd.appendChild(document.createTextNode(apt.querySelector(type).textContent));
			nameTd.setAttribute("onclick", `panTo(${(pos.La *= 1)}, ${(pos.Ma *= 1)})`);

			divEl.appendChild(nameTd);

			let floorTd = document.createElement("div");
			floorTd.appendChild(
					document.createTextNode(apt.querySelector("층").textContent + "층")
			);
			divEl.appendChild(floorTd);

			let areaTd = document.createElement("div");
			areaTd.appendChild(
					document.createTextNode("면적: " + apt.querySelector("전용면적").textContent)
			);
			divEl.appendChild(areaTd);

			let dongTd = document.createElement("div");
			dongTd.appendChild(
					document.createTextNode("법정동: " + apt.querySelector("법정동").textContent)
			);
			divEl.appendChild(dongTd);

			let priceTd = document.createElement("div");
			priceTd.appendChild(
					document.createTextNode(
							"거래금액: " + apt.querySelector("거래금액").textContent + "만원"
					)
			);
			//priceTd.classList.add("text-end");

			divEl.appendChild(priceTd);

			let dateTd = document.createElement("div");
			dateTd.appendChild(
					document.createTextNode(
							"거래날짜: " +
							apt.querySelector("년").textContent +
							"." +
							apt.querySelector("월").textContent +
							"." +
							apt.querySelector("일").textContent
					)
			);
			divEl.appendChild(dateTd);

			listDiv.appendChild(divEl);
		}
	});
}

function panTo(lat, lng) {
	// 이동할 위도 경도 위치를 생성합니다
	var moveLatLon = new kakao.maps.LatLng(lng, lat);
	map.setLevel(3);

	// 지도 중심을 부드럽게 이동시킵니다
	// 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	map.panTo(moveLatLon);
}


