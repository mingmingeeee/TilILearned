

function sidoClick() {
	if (this[this.selectedIndex].value) {
		let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
		sendRequest("gugun", regcode);
	} else {
		initOption("gugun");
		initOption("dong");
	}
}

function gugunClick() {
	if (this[this.selectedIndex].value) {
		let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
		sendRequest("dong", regcode);
	} else {
		initOption("dong");
	}
}
//
// function sendRequest(selid, regcode) {
// console.log("sendRequest called")
// const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
// let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
// fetch(url+params)
// .then((response) => response.json())
// .then((data) => addOption(selid, data));
// }



function makehouseList(data) {
	console.log("makehouseList")
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
      /* 주소 변환 */

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(c, function (result, status) {
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
            makeClickListener(map, marker, infowindow, apt.querySelector("아파트").textContent)
          );
          // infowindow.open(map, marker);

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
          map.setLevel(6);
        }
      });

      /* 주소 변환 끝 */
    });
  }

function makehouseSaleList(data, pos) {
	console.log("makehouseSaleList")
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
// let apts = xml.querySelectorAll("item");
// apts.forEach((apt) => {
    	// 그럼 선택한 카테고리의 위치도 보여줄건지???

    			const url = "${root}" + '/house/getLocalInfo?categoryCode='+category;
    	        fetch(url, {
    	            method: "GET",
    	          })
    	            .then((response) => response.json())
    	            .then((data) => {
    	             	console.log(data)
    	             	apt.aroundOptions = data["meta"]["total_count"]
    	             	console.log(Object.keys(data))
    	            });

    		
    		
// makeAptList(apts)
//    		
// }
      if (apt.querySelector("아파트").textContent == click_apt_name) {
        let divEl = document.createElement("div");
        divEl.setAttribute("class", "houseinfo border border-3 rounded-2 p-3");

        let nameTd = document.createElement("a");
        nameTd.setAttribute("class", "fw-bold fs-5 text-decoration-none");
        nameTd.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
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
        // priceTd.classList.add("text-end");

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
  }

function listBtnClick() {
    let url =
      "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
    let gugunSel = document.querySelector("#gugun");
    let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0, 5);
    let dealYM = "202112";
    let queryParams =
      encodeURIComponent("serviceKey") +
      "=" +
      "tVjgW6ovTuqohBqi6D6Gf%2B6MndIEx3CVmdwPQL7F8SCduFG6bWYlbRnuA7RVc%2Bzgf75D5UHpGLNVVDye4W%2BWvQ%3D%3D"; /*
																											 * Service
																											 * Key
																											 */
    queryParams +=
      "&" +
      encodeURIComponent("LAWD_CD") +
      "=" +
      encodeURIComponent(regCode); /* 아파트소재 구군 */
    queryParams +=
      "&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(dealYM); /* 조회년월 */
    queryParams +=
      "&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1"); /* 페이지번호 */
    queryParams +=
      "&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent("30"); /* 페이지당건수 */

    fetch(`${url}?${queryParams}`)
      .then((response) => response.text())
      .then((data) => makehouseList(data));
  }

function initOption(selid) {
    let options = document.querySelector(`#${selid}`);
    options.length = 0;
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