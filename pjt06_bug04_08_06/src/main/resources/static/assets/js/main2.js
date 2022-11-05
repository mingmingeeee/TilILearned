var markers = [];
let apts=[];
let date = new Date();

window.onload = function () {
	// 브라우저가 열리면 시도정보 얻기.
    const url = '/apt/getSido';
    fetch(url, {
      method: "GET",
    })
      .then((response) => response.json())
      .then((data) => {
        let options = `<option value="">시도선택</option>`;
        data.forEach((dong) => {
          options += `<option value="${ dong.dongCode }">${ dong.sidoName }</option>`;
        });

        document.querySelector("#aptsido").innerHTML = options;
      });
    
    document.querySelector("#aptsido").addEventListener("change", function () {
    	console.log("aptsido clicked")
        let sidoCode = this[this.selectedIndex].value;
            // 구군 정보 얻기
            const url ='/apt/getGugun?' +
              new URLSearchParams({
              	dongCode: sidoCode,
              });

            fetch(url, {
              method: "GET",
            })
              .then((response) => response.json())
              .then((data) => {
                let options = `<option value="">구군선택</option>`;
                data.forEach((dong) => {
                    options += `<option value="${ dong.dongCode }">${ dong.gugunName }</option>`;
                  });
                document.querySelector("#aptgugun").innerHTML = options;
              });
          });

          document.querySelector("#aptgugun").addEventListener("change", function () {
            let gugunCode = this[this.selectedIndex].value;

            // 동 정보 얻기
            const url =
              "/apt/getDong?" +
              new URLSearchParams({
            	  dongCode: gugunCode,
              });

            fetch(url, {
              method: "GET",
            })
              .then((response) => response.json())
              .then((data) => {
                let options = `<option value="">동선택</option>`;
                data.forEach((dong) => {
                    options += `<option value="${ dong.dongCode }">${ dong.dongName }</option>`;
                  });
                document.querySelector("#aptdong").innerHTML = options;
              });
          });

};

function makeAptList(data) {
	saleinfo = data;
	let listDiv = document.querySelector("#houselist");

	initHouselist();
	hideMarkers();
	bounds = new kakao.maps.LatLngBounds(); 
	if (data.length == 0){
		console.log(" 없습니다.");
		let divEl = document.createElement("div");
		divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");
		let msg = document.createElement("div");
		msg.appendChild(document.createTextNode("검색 결과가 없습니다."));
		divEl.appendChild(msg);
		listDiv.appendChild(divEl);
	}
	let apts = data;
	apts.forEach((apt) => {
		let divEl = document.createElement("div");
		divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");

		let nameTd = document.createElement("a");
		nameTd.appendChild(document.createTextNode(apt["apartmentName"]));
		nameTd.setAttribute("class", "fw-bold fs-5 text-decoration-none");

		nameTd.setAttribute("onclick", `panTo(${(apt['lat']*= 1)}, ${(apt['lng']*= 1)})`);

		divEl.appendChild(nameTd);

		let areaTd = document.createElement("div");
		areaTd.appendChild(
				document.createTextNode("건축년도: " + apt["buildYear"])
		);
		divEl.appendChild(areaTd);

		let dongTd = document.createElement("div");
		dongTd.appendChild(
				document.createTextNode("법정동: " + apt["dong"])
		);
		divEl.appendChild(dongTd);

		listDiv.appendChild(divEl);
		/* 주소 변환 */

		// 주소로 좌표를 검색합니다
		// 정상적으로 검색이 완료됐으면
		var coords = new kakao.maps.LatLng(apt["lat"], apt["lng"]);

		// 결과값으로 받은 위치를 마커로 표시합니다
		var marker = new kakao.maps.Marker({
			map: map,
			position: coords,
		});

		// 인포윈도우로 장소에 대한 설명을 표시합니다
		var infowindow = new kakao.maps.InfoWindow({
			content: `<div style="width:150px;text-align:center;padding:6px 0;">${
				apt['apartmentName']
				}<br>${apt['roadName']}</div>`,
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
				makeClickListener(map, marker, infowindow, apt,"apartmentName")
		);
		// infowindow.open(map, marker);

		// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		map.setCenter(coords);
		bounds.extend(coords);
		map.setLevel(6);
		markers.push(marker);
		setBounds();
		/* 주소 변환 끝 */
	});
};

// 그럼 선택한 카테고리의 위치도 보여줄건지???
function sortByCategory(category,apts){
// apts.forEach((apt) => {
		const url = "${root}" + '/apt/getLocalInfo?categoryCode='+category+"&x="+apt["lat"]+"&y="+apt["lng"];
        fetch(url, {
            method: "GET",
          })
            .then((response) => response.json())
            .then((data) => {

             	apt.aroundOptions = data["meta"]["total_count"]
             	makehouseSaleList(apts)
            });
		
// });
	
	
	
	
}

var saleinfo;
function makehouseList(data) {
	saleinfo = data;
	let listDiv = document.querySelector("#houselist");

	initHouselist();
	hideMarkers();
	bounds = new kakao.maps.LatLngBounds(); 
	if (data == null){
		console.log(" 없습니다.");
		let divEl = document.createElement("div");
		divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");
		let msg = document.createElement("div");
		msg.appendChild(document.createTextNode("검색 결과가 없습니다."));
		divEl.appendChild(msg);
		listDiv.appendChild(divEl);
		return;
	}
	let apts = data;
	apts.forEach((apt) => {
		let divEl = document.createElement("div");
		divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");

		let nameTd = document.createElement("a");
		nameTd.appendChild(document.createTextNode(apt["연립다세대"]));
		nameTd.setAttribute("class", "fw-bold fs-5 text-decoration-none");

		let sidoSel1 = document.querySelector("#aptsido");
		let gugunSel1 = document.querySelector("#aptgugun");
		let address2 =
			sidoSel1[sidoSel1.selectedIndex].text +
			" " +
			gugunSel1[gugunSel1.selectedIndex].text +
			" " +
			apt["법정동"];
		let address = address2 + " " + apt["지번"];



		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address, function (result, status) {
			// 정상적으로 검색이 완료됐으면
			if (status === kakao.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				nameTd.setAttribute("onclick", `panTo(${(result[0].y *= 1)}, ${(result[0].x *= 1)})`);
			}
		});

		divEl.appendChild(nameTd);

		let floorTd = document.createElement("div");
		floorTd.appendChild(document.createTextNode(apt["층"] + "층"));
		divEl.appendChild(floorTd);

		let areaTd = document.createElement("div");
		areaTd.appendChild(
				document.createTextNode("면적: " + apt["전용면적"])
		);
		divEl.appendChild(areaTd);

		let dongTd = document.createElement("div");
		dongTd.appendChild(
				document.createTextNode("법정동: " + apt["법정동"])
		);
		divEl.appendChild(dongTd);

		let priceTd = document.createElement("div");
		priceTd.appendChild(
				document.createTextNode(
						"거래금액: " + apt["거래금액"] + "만원"
				)
		);
		priceTd.classList.add("text-end");

		divEl.appendChild(priceTd);

		listDiv.appendChild(divEl);
		/* 주소 변환 */

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
						apt["연립다세대"]
						}<br>${apt["거래금액"] + "만원"}</div>`,
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
						makeClickListener2(map, marker, infowindow, apt["연립다세대"],"연립다세대")
				);
				// infowindow.open(map, marker);

				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				// map.setCenter(coords);
				map.setLevel(6);
				bounds.extend(coords);
				markers.push(marker);
				setBounds();
				
			}
		});
		
		/* 주소 변환 끝 */
	});
	
	// showMarkers();
	
}

//dealinfo
function makeAptSaleList(aptInfo,dealInfo, pos,type) {
	console.log("makeAptSaleList")
	initHouselist();
	hideMarkers();
	bounds = new kakao.maps.LatLngBounds(); 
	let listDiv = document.querySelector("#houselist");
	let divEl1 = document.createElement("div");
	divEl1.setAttribute(
			"class",
			"houseinfo border border-3 rounded-2 p-3 text-white bg-opacity-10"
	);
	divEl1.setAttribute("style", "background-color: #204981;");

	let nameTd1 = document.createElement("h5");
	nameTd1.appendChild(document.createTextNode(aptInfo['apartmentName']));
	nameTd1.setAttribute("class", "fw-bold");

	divEl1.appendChild(nameTd1);
	listDiv.appendChild(divEl1);

	// 한 아파트의 거래 정보 얻어오기
	// let apts = xml.querySelectorAll("item");
	dealInfo.forEach((apt) => {
			let divEl = document.createElement("div");
			divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");

			let nameTd = document.createElement("a");
			nameTd.setAttribute("class", "fw-bold fs-5 text-decoration-none");
			nameTd.appendChild(document.createTextNode(aptInfo["apartmentName"]));
			nameTd.setAttribute("onclick", `panTo(${(apt['lat']*= 1)}, ${(apt['lng']*= 1)})`);

			divEl.appendChild(nameTd);

			let floorTd = document.createElement("div");
			floorTd.appendChild(
					document.createTextNode(apt["floor"] + "층")
			);
			
			divEl.appendChild(floorTd);

			let areaTd = document.createElement("div");
			areaTd.appendChild(
					document.createTextNode("면적: " + apt["area"])
			);
			divEl.appendChild(areaTd);

			let dongTd = document.createElement("div");
			dongTd.appendChild(
					document.createTextNode("법정동: " + aptInfo["dong"])
			);
			divEl.appendChild(dongTd);

			let priceTd = document.createElement("div");
			priceTd.appendChild(
					document.createTextNode(
							"거래금액: " + apt["dealAmount"] + "만원"
					)
			);
			// priceTd.classList.add("text-end");

			divEl.appendChild(priceTd);

			let dateTd = document.createElement("div");
			dateTd.appendChild(
					document.createTextNode(
							"거래날짜: " +
							apt["dealYear"] +
							"." +
							apt["dealMonth"] +
							"." +
							apt["dealDay"]
					)
			);
			divEl.appendChild(dateTd);

			listDiv.appendChild(divEl);
			var coords = new kakao.maps.LatLng(aptInfo["lat"], aptInfo["lng"]);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords,
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: `<div style="width:150px;text-align:center;padding:6px 0;">${
					apt['apartmentName']
					}<br>${apt['dealAmount'] + "만원"}</div>`,
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
					makeClickListener(map, marker, infowindow,apt,type)
			);
			// infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		//map.setCenter(coords);
			//map.setLevel(6);
			//markers.push(marker);
		showMarkers();
	});
};

function makeHouseSaleList(data, pos,type) {
	initHouselist();
	hideMarkers();
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

	// 한 아파트의 거래 정보 얻어오기
	// let apts = xml.querySelectorAll("item");
	let apts = data;
	apts.forEach((apt) => {
		if (apt[type] == click_apt_name) {
			let divEl = document.createElement("div");
			divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");

			let nameTd = document.createElement("a");
			nameTd.setAttribute("class", "fw-bold fs-5 text-decoration-none");
			nameTd.appendChild(document.createTextNode(apt[type]));
			let sidoSel1 = document.querySelector("#aptsido");
			let gugunSel1 = document.querySelector("#aptgugun");
			let address2 = 	sidoSel1[sidoSel1.selectedIndex].text + " " +
				gugunSel1[gugunSel1.selectedIndex].text + " " +
				apt["법정동"];
			let address = address2 + " " + apt["지번"];

			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(address, function (result, status) {
				// 정상적으로 검색이 완료됐으면
				if (status === kakao.maps.services.Status.OK) {
					var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					nameTd.setAttribute("onclick", `panTo(${(result[0].y *= 1)}, ${(result[0].x *= 1)})`);
				}
			});

			divEl.appendChild(nameTd);

			let floorTd = document.createElement("div");
			floorTd.appendChild(
					document.createTextNode(apt["층"] + "층")
			);
			
			divEl.appendChild(floorTd);

			let areaTd = document.createElement("div");
			areaTd.appendChild(
					document.createTextNode("면적: " + apt["전용면적"])
			);
			divEl.appendChild(areaTd);

			let dongTd = document.createElement("div");
			dongTd.appendChild(
					document.createTextNode("법정동: " + apt["법정동"])
			);
			divEl.appendChild(dongTd);

			let priceTd = document.createElement("div");
			priceTd.appendChild(
					document.createTextNode(
							"거래금액: " + apt["거래금액"] + "만원"
					)
			);
			// priceTd.classList.add("text-end");

			divEl.appendChild(priceTd);

			let dateTd = document.createElement("div");
			dateTd.appendChild(
					document.createTextNode(
							"거래날짜: " +
							apt["년"] +
							"." +
							apt["월"] +
							"." +
							apt["일"]
					)
			);
			divEl.appendChild(dateTd);

			listDiv.appendChild(divEl);
			/* 주소 변환 */

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
							apt["연립다세대"]
							}<br>${apt["거래금액"] + "만원"}</div>`,
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
							makeClickListener2(map, marker, infowindow, apt["연립다세대"],"연립다세대")
					);
					// infowindow.open(map, marker);

					// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					map.setCenter(coords);
					map.setLevel(6);
					markers.push(marker);
				}
			});

			/* 주소 변환 끝 */
		}
	});
};

function initHouselist() {
	let listDiv = document.querySelector("#houselist");
	while (listDiv.hasChildNodes()) {
		listDiv.removeChild(listDiv.firstChild);
	}
}

function makeClickListener(map, marker, infowindow, aptInfo,type) {
	return function () {
		var pos = marker.getPosition();
		map.setLevel(5);
		map.panTo(pos);
		infowindow.close();
		const url = '/apt/aptDealInfo?' +new URLSearchParams({
      	  aptCode: aptInfo["aptCode"],
        });
        fetch(url, {
            method: "GET",
          })
            .then((response) => response.json())
            .then((saleinfo) => {
            	makeAptSaleList(aptInfo,saleinfo, pos,type);
            });
	};
}
function makeClickListener2(map, marker, infowindow, aptname,type) {
	return function () {
		var pos = marker.getPosition();
		map.setLevel(5);
		map.panTo(pos);
		click_apt_name = aptname;
		infowindow.close();
		makeHouseSaleList(saleinfo, pos,type);
	};
}
var click_apt_name;
// 인포윈도우를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map, marker, infowindow) {
	return function () {
		infowindow.open(map, marker);
	};
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
	return function () {
		infowindow.close();
	};
}

function panTo(lat, lng) {
	// 이동할 위도 경도 위치를 생성합니다
	var moveLatLon = new kakao.maps.LatLng(lat, lng);
	map.setLevel(3);

	// 지도 중심을 부드럽게 이동시킵니다
	// 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	map.panTo(moveLatLon);
}


var bounds = new kakao.maps.LatLngBounds();  
function setMarkers(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}
// "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
function showMarkers() {
    setMarkers(map)
}

// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
function hideMarkers() {
    setMarkers(null);    
}
function setBounds() {
    // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
    // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
    map.setBounds(bounds);
}
