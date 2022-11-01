<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/header.jsp"%>
<title>부동산 매매정보</title>
</head>
<body>
	<%@ include file="/common/nav.jsp"%>
	<h1>부동산 매매 정보</h1>
	<select id="sido">
		<option value="">도/광역시</option>
	</select>
	<select id="gugun">
		<option value="">시/구/군</option>
	</select>
	<select id="dong">
		<option value="">동</option>
	</select>
	<a href="" id="regist">등록</a>
	<a href="">닫기</a>
	<script type="text/javascript">
		window.onload = function(){
			// 시도 정보 얻기
			const url = '${root}'+ '/rest/house/sido';
			fetch(url, {
				method: 'GET'
			})
			.then((response) => response.json())
			.then((data) => {
				let options = `<option value="">시도선택</option>`;
				Object.keys(data).forEach(function (key) {
					options += `<option value="\${ key }">\${ data[key] }</option>`;
				});
				
				document.querySelector("#sido").innerHTML = options;
			});
			
			document.querySelector("#sido").addEventListener("change", function() {
				let sidoCode = this[this.selectedIndex].value;
				
				// 구군 정보 얻기
				const url = '${root}' + '/rest/house/gugun?' + new URLSearchParams({
					sidoCode: sidoCode
				});
				
				fetch(url, {
					method: 'GET'
				})
				.then((response) => response.json())
				.then((data) => {
					let options = `<option value="">구군선택</option>`;
					Object.keys(data).forEach(function (key) {
						options += `<option value="\${ key }">\${ data[key] }</option>`;
					});
					
					document.querySelector("#gugun").innerHTML = options;
				})
			})
		};
	</script>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>