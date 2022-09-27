<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp" %>
<title>부동산 매매정보</title>
</head>
<body>
	<%@ include file="/include/nav.jsp"%>
	<h1>부동산 매매정보</h1>
	<select id="sido">
		<option value="">시도 선택</option>
	</select>
	<select id="gugun">
		<option value="">구군 선택</option>
	</select>
	<select id="dong">
		<option value="">동 선택</option>
	</select>
	<%@ include file="/include/footer.jsp" %>
	
	<script>
		window.onload = function() {
			// 시도 정보 얻기
			const url = '${ root }' + '/rest/house/sido'; 
			// ${root}: JSP에 명시되어있는 경로
			// ~/servlet 까지.
			fetch(url, {
				method: 'GET'
			})
			.then((response) => response.json()) // 여기서 역직렬화..^^
			.then((data) => {
				// console.log(data);
				let options = `<option value="">시도선택</option>`;
				Object.keys(data).forEach(function(key) {
					options += `<option value="\${key}">\${data[key]}</option>`;
				})
				
				document.querySelector("#sido").innerHTML = options;
			});
			
			let sidoCode;
			document.querySelector("#sido").addEventListener("change", function () {
				sidoCode = this[this.selectedIndex].value; // sido code값 가져옴 
				
				// 구군 정보 얻기
				const url = '${ root }' + '/rest/house/gugun?' + new URLSearchParams({
					// URLSearchParams: parameter 보낼 때 도와주는!
					sidoCode: sidoCode
				});
				
				fetch(url, {
					method: 'GET'
				})
				.then((response) => response.json())
				.then((data) => {
					// console.log(data);
					let options = `<option value="">구군선택</option>`;
					Object.keys(data).forEach(function(key) {
						options += `<option value="\${key}">\${data[key]}</option>`;
					})
					
					document.querySelector("#gugun ").innerHTML = options;
				});
			});
			
			
			document.querySelector("#gugun").addEventListener("change", function () {
				sidoCode = this[this.selectedIndex].value; // sido code값 가져옴 
				let gugunCode = this[this.selectedIndex].value; 
				
				// 동 정보 얻기
				const url = '${ root }' + '/rest/house/dong?' + new URLSearchParams({
					// URLSearchParams: parameter 보낼 때 도와주는!
					sidoCode: sidoCode,
					gugunCode: gugunCode
				});
				
				fetch(url, {
					method: 'GET'
				})
				.then((response) => response.json())
				.then((data) => {
					console.log(data);
					let options = `<option value="">동 선택</option>`;
					Object.keys(data).forEach(function(key) {
						options += `<option value="\${key}">\${data[key]}</option>`;
					})
					
					document.querySelector("#dong").innerHTML = options;
				});
			});
			
		};
	</script>
</body>
</html>