<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>부동산 매매정보</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
	<h1>부동산 매매 정보</h1>
	<select id="sido">
		<option value="">시도선택</option>
	</select>
	<select id="gugun">
		<option value="">구군선택</option>
	</select>
	<select id="dong">
		<option value="">동선택</option>
	</select>
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
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>