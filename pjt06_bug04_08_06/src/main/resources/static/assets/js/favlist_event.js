
function getMyFavList(dongCode) {
	const favUrl = "mvfav_list2?dongCode="+dongCode;
	fetch(favUrl, {
		method: 'GET'
	})
	.then((response) => response.json())
	.then((data) => {
		makehouseList22(JSON.parse(data))
	})
}

function initFavlist() {
	let listDiv = document.querySelector("#favList");
	while (listDiv.hasChildNodes()) {
		listDiv.removeChild(listDiv.firstChild);
	}
}

function makehouseList22(data) {
    saleinfo = data.response;
    let listDiv = document.querySelector("#favList");
    console.log("called makehouseList22")

    data.forEach((apt) => {
   let address =  apt["dong"] + " " +  apt["jibun"]
      console.log(address)
     
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
              apt["apartmentName"]
            }<br>${address}</div>`,
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
            () => infoClickListener(map, marker, infowindow, apt,"apt")
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

function infoClickListener(map, marker, infowindow, aptInfo,type) {
	let infoDiv = document.querySelector("#houseDealInfo");
		var pos = marker.getPosition();
		console.log(pos);
		map.setLevel(5);
		map.panTo(pos);
		const favUrl = "getAptDealInfo?aptCode="+aptInfo["aptCode"];
		fetch(favUrl, {
			method: 'GET'
		})
		.then((response) => response.json())
		.then((data) => {
			 showSelectedHouseDealInfo(JSON.parse(data),aptInfo["apartmentName"]);
		
		})
}

function showSelectedHouseDealInfo(dealInfo,aptName){
	// 클릭한 집에 대한 거래정보를 보여준다.
	let infoDiv = document.querySelector("#houseDealInfo");
	while (infoDiv.hasChildNodes()) {
		infoDiv.removeChild(infoDiv.firstChild);
	}
	let divEl1 = document.createElement("div");
	divEl1.setAttribute(
	      "class",
	      "houseinfo border border-3 rounded-2 p-3 text-black"
	);
	let nameTd2 = document.createElement("h5");
	nameTd2.appendChild(document.createTextNode(aptName));
	divEl1.appendChild(nameTd2);
    let nameTd1 = document.createElement("h5");
    nameTd1.appendChild(document.createTextNode("거래년도 : "+ dealInfo["dealYear"]));
	divEl1.appendChild(nameTd1); 
	let nameTd3 = document.createElement("h5");
	nameTd3.appendChild(document.createTextNode("거래 달: "+ dealInfo["dealMonth"]));
	divEl1.appendChild(nameTd3);
	let nameTd4= document.createElement("h5");
	nameTd4.appendChild(document.createTextNode("층 : "+ dealInfo["floor"]));
	divEl1.appendChild(nameTd4);
	
	infoDiv.appendChild(divEl1); 
}

//  관심지역 목록 클릭했을 
function makeFavList(fav) {
	initFavlist();
    let listDiv = document.querySelector("#favList");
   console.log(fav)
    fav.forEach((dong) => {
    	let divEl1 = document.createElement("div");
    	divEl1.setAttribute(
    	      "class",
    	      "houseinfo border border-3 rounded-2 p-3 text-white bg-opacity-10"
    	);
    	divEl1.setAttribute("style", "background-color: #204981;");
    	    
        let nameTd1 = document.createElement("h5");
        nameTd1.appendChild(document.createTextNode(dong["dongName"]));
        nameTd1.setAttribute("class", "fw-bold");
        
    	divEl1.appendChild(nameTd1); 
    	
    	divEl1.addEventListener("click",function(){
				const favUrl = "mvfav_list2?dongCode="+dong["dongCode"];
				fetch(favUrl, {
					method: 'GET'
				})
				.then((response) => response.json())
				.then((data) => {
					makehouseList22(JSON.parse(data))
				})
    	})
    	listDiv.appendChild(divEl1);
  })

}