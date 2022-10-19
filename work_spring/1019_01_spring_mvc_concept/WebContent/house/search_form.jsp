<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/include/nav.jsp"%>
	<h1>부동산 매매정보</h1>
	<select id="sido">
		<option value="">시도선택</option>
	</select>
	<select id="gugun">
		<option value="">구군선택</option>
	</select>
	<select id="dong">
		<option value="">동선택</option>
	</select>

	<%@ include file="/include/footer.jsp"%>
	
	<script>
		window.onload = function() {
			
			// 시도 정보 얻기
			const url = '${ root }' + '/rest/house/sido';
			fetch(url, {
				method: 'GET'
			})
			.then((response) => response.json())
			.then((data) => {
				let options = `<option value="">시도선택</option>`;
				Object.keys(data).forEach(function(key) {
					options += `<option value="\${ key }">\${ data[key] }</option>`;
				});
				
				document.querySelector("#sido").innerHTML = options;
			});
			
			document.querySelector("#sido").addEventListener("change", function() {
				
				let sidoCode = this[this.selectedIndex].value;
				
				// 구군 정보 얻기
				const url = '${ root }' + '/rest/house/gugun?' + new URLSearchParams({
					sidoCode: sidoCode
				});
				fetch(url, {
					method: 'GET'
				})
				.then((response) => response.json())
				.then((data) => {
					let options = `<option value="">구군선택</option>`;
					Object.keys(data).forEach(function(key) {
						options += `<option value="\${ key }">\${ data[key] }</option>`;
					});
					
					document.querySelector("#gugun").innerHTML = options;
				});
			});
			
			// 아파트 매매 API 호출을 위한 요청 fetch 작성
			const aptUrl = '${ root }' + '/rest/house/apt/trade?' + new URLSearchParams({
				regionCode: "11110",
				dealYmd: "202112"
			});
			
			fetch(aptUrl, {
				method: 'GET'
			})
			.then((response) => response.json())
			.then((data) => {
				let houses = data;
				houses.forEach(function (house) {
					console.log(house.거래금액);
				});
			});
			
			// 연립다세대 API 호출을 위한 요청 fetch 작성
			const rowHouseUrl = '${ root }' + '/rest/house/row-house/trade?' + new URLSearchParams({
				regionCode: "11110",
				dealYmd: "202112"
			});
			
			fetch(rowHouseUrl, {
				method: 'GET'
			})
			.then((response) => response.json())
			.then((data) => {
				let houses = data;
				houses.forEach(function (house) {
					console.log(house.dealAmount);
				});
			});
		};
	</script>
</body>
</html>