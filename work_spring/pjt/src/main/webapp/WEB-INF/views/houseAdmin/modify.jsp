<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/nav.jsp"%>
<c:if test="${empty apt}">
	<script type="text/javascript">
		alert("정상적인 URL 접근이 아닙니다.");
		location.href = "${root}/";
	</script>
</c:if>
<div class="container bc">
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				주택정보수정
			</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<form id="form-modify" method="POST" action="">
			<input type="hidden" name="aptCode" value="${ apt.aptCode }">
				<div class="mb-3">
					<label for="buildYear" class="form-label">건축년도 : </label>
					<input type="text" class="form-control" id="buildYear" name="buildYear"
						value="${ apt.buildYear }"  />
				</div>
				<div id="check-buildYear"></div>
				<div class="mb-3">
					<label for="roadName" class="form-label">도로명 : </label>
					<input type="text" class="form-control" id="roadName" name="roadName"
						value="${ apt.roadName }"  />
				</div>
				<div id="check-roadName"></div>
				<div class="mb-3">
					<label for="roadNameBonbun" class="form-label">도로명건물본번호코드 : </label>
					<input type="text" class="form-control" id="roadNameBonbun" name="roadNameBonbun"
						value="${ apt.roadNameBonbun }"  />
				</div>
				<div id="check-roadNameBonbun"></div>
				<div class="mb-3">
					<label for="roadNameBubun" class="form-label">도로명건물부번호코드 : </label>
					<input type="text" class="form-control" id="roadNameBubun" name="roadNameBubun"
						value="${ apt.roadNameBubun }"  />
				</div>
				<div id="check-roadNameBubun"></div>
				<div class="mb-3">
					<label for="roadNameSeq" class="form-label">도로명일련번호코드 : </label>
					<input type="text" class="form-control" id="roadNameSeq" name="roadNameSeq"
						value="${ apt.roadNameSeq }"  />
				</div>
				<div id="check-roadNameSeq"></div>
				<div class="mb-3">
					<label for="roadNameBasementCode" class="form-label">도로명지상지하코드 : </label>
					<input type="text" class="form-control" id="roadNameBasementCode" name="roadNameBasementCode"
						value="${ apt.roadNameBasementCode }" />
				</div>
				<div id="check-roadNameBasementCode"></div>
				<div class="mb-3">
					<label for="roadNameCode" class="form-label">도로명코드 : </label>
					<input type="text" class="form-control" id="roadNameCode" name="roadNameCode"
						value="${ apt.roadNameCode }"  />
				</div>
				<div id="check-roadNameCode"></div>
				<div class="mb-3">
					<label for="dong" class="form-label">법정동 : </label>
					<input type="text" class="form-control" id=dong name="dong"
						value="${ apt.dong }" />
				</div>
				<div class="mb-3">
					<label for="bonbun" class="form-label">법정동본번코드 : </label>
					<input type="text" class="form-control" id="bonbun" name="bonbun"
						value="${ apt.bonbun }"  />
				</div>
				<div id="check-bonbun"></div>
				<div class="mb-3">
					<label for="bubun" class="form-label">법정동부번코드 : </label>
					<input type="text" class="form-control" id="bubun" name="bubun"
						value="${ apt.bubun }"  />
				</div>
				<div id="check-bubun"></div>
				<div class="mb-3">
					<label for="sigunguCode" class="form-label">법정동시군구코드 : </label>
					<input type="text" class="form-control" id="sigunguCode" name="sigunguCode"
						value="${ apt.sigunguCode }"  />
				</div>
				<div id="check-sigunguCode"></div>
				<div class="mb-3">
					<label for="eubmyundongCode" class="form-label">법정동읍면동코드 : </label>
					<input type="text" class="form-control" id="eubmyundongCode" name="eubmyundongCode"
						value="${ apt.eubmyundongCode }"  />
				</div>
				<div id="check-eubmyundongCode"></div>
				<div class="mb-3">
					<label for="dongCode" class="form-label">지역코드(동코드) : </label>
					<input type="text" class="form-control" id="dongCode" name="dongCode"
						value="${ apt.dongCode }" />
				</div>
				<div id="check-dongCode"></div>
				<div class="mb-3">
					<label for="landCode" class="form-label">법정동지번코드 : </label>
					<input type="text" class="form-control" id="landCode" name="landCode"
						value="${ apt.landCode }"  />
				</div>
				<div id="check-landCode"></div>
				<div class="mb-3">
					<label for="apartmentName" class="form-label">아파트이름 : </label>
					<input type="text" class="form-control" id="apartmentName" name="apartmentName"
						value="${ apt.apartmentName }"  />
				</div>
				<div id="check-apartmentName"></div>
				<div class="mb-3">
					<label for="jibun" class="form-label">지번 : </label>
					<input type="text" class="form-control" id="jibun" name="jibun"
						value="${ apt.jibun }" />
				</div>
				<div id="check-jibun"></div>
				<div class="mb-3">
					<label for="lng" class="form-label">경도 : </label>
					<input type="text" class="form-control" id="lng" name="lng"
						value="${ apt.lng }" />
				</div>
				<div class="mb-3">
					<label for="lat" class="form-label">위도 : </label>
					<input type="text" class="form-control" id="lat" name="lat"
						alue="${ apt.lat }" />
				</div>
				<div class="col-auto text-center">
					<button type="button" id="btn-register"
						class="btn btn-outline-primary mb-3">주택 수정완료</button>
					<button type="button" id="btn-list"
						class="btn btn-outline-danger mb-3">목록으로이동...</button>
				</div>
			</form>
		</div>
	</div>
</div>
<form id="form-param" method="post" action="">
	<input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
	<input type="hidden" id="key" name="key" value="${param.key}">
	<input type="hidden" id="word" name="word" value="${param.word}">
</form>
<script>
	let CKbuildYear = true;
	document.querySelector("#buildYear").addEventListener("keyup", function () {
	  let buildYear = this.value;
	  let resultDiv = document.querySelector("#check-buildYear");
	  if ( isNaN(buildYear) ) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "건축년도는 숫자만 입력가능합니다.";
	    CKbuildYear = false;
	  } else {
		  CKbuildYear = true;
	  }
	});
	let CKroadName = true;
	document.querySelector("#roadName").addEventListener("keyup", function () {
	  let roadName = this.value;
	  let resultDiv = document.querySelector("#check-roadName");
	  if ( roadName.legnth > 40) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "도로명은 40자 이하입니다.";
	    CKroadName = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
		CKroadName = true;
	  }
	});
	let CKroadNameBonbun = true;
	document.querySelector("#roadNameBonbun").addEventListener("keyup", function () {
	  let roadNameBonbun = this.value;
	  let resultDiv = document.querySelector("#check-roadNameBonbun");
	  if ( roadNameBonbun.length > 5) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "도로명건물본번호코드는 5자 이하입니다.";
	    CKroadNameBonbun= false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
		CKroadNameBonbun = true;
	  }
	});
	let CKroadNameBubun = true;
	document.querySelector("#roadNameBubun").addEventListener("keyup", function () {
	  let roadNameBubun = this.value;
	  let resultDiv = document.querySelector("#check-roadNameBubun");
	  if ( roadNameBubun.length > 5) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "도로명건물부번호코드는 5자 이하입니다.";
	    CKroadNameBubun = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
		CKroadNameBubun = true;
	  }
	});
	let CKroadNameSeq = true;
	document.querySelector("#roadNameSeq").addEventListener("keyup", function () {
	  let roadNameSeq = this.value;
	  let resultDiv = document.querySelector("#check-roadNameSeq");
	  if ( roadNameSeq.length > 2) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "도로명일련번호코드는 2자 이하입니다.";
	    CKroadNameSeq = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
		CKroadNameSeq = true;
	  }
	});
	let CKroadNameBasementCode = true;
	document.querySelector("#roadNameBasementCode").addEventListener("keyup", function () {
	  let roadNameBasementCode = this.value.toString();
	  let resultDiv = document.querySelector("#check-roadNameBasementCode");
	  if ( roadNameBasementCode.length > 1) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "도로명지상지하코드는 1자 이하입니다.";
	    CKroadNameBasementCode = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
		CKroadNameBasementCode = true;
	  }
	});
	let CKroadNameCode = true;
	document.querySelector("#roadNameCode").addEventListener("keyup", function () {
	  let roadNameCode = this.value.toString();
	  let resultDiv = document.querySelector("#check-roadNameCode");
	  if ( roadNameCode.length > 7) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "도로명코드는 7자 이하입니다.";
	    CKroadNameCode = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
		CKroadNameCode = true;
	  }
	});
	let CKbonbun = true;
	document.querySelector("#bonbun").addEventListener("keyup", function () {
	  let bonbun = this.value;
	  let resultDiv = document.querySelector("#check-bonbun");
	  if ( bonbun.length > 4) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "법정동본번코드는 4자 이하입니다.";
	    CKbonbun = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
		CKbonbun = true;
	  }
	});
	let CKbubun = true;
	document.querySelector("#bubun").addEventListener("keyup", function () {
	  let bubun = this.value;
	  let resultDiv = document.querySelector("#check-bubun");
	  if ( bubun.length > 4) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "법정동부번코드는 4자 이하입니다.";
	    CKbubun = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
		  CKbubun = true;
	  }
	});
	let CKsigunguCode = true;
	document.querySelector("#sigunguCode").addEventListener("keyup", function () {
	  let sigunguCode = this.value;
	  let resultDiv = document.querySelector("#check-sigunguCode");
	  if ( sigunguCode.length > 5) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "법정동시군구코드는 5자 이하입니다.";
	    CKsigunguCode = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
          CKsigunguCode = true;
	  }
	});
	let CKeubmyundongCode = true;
	document.querySelector("#eubmyundongCode").addEventListener("keyup", function () {
	  let eubmyundongCode = this.value;
	  let resultDiv = document.querySelector("#check-eubmyundongCode");
	  if ( eubmyundongCode.length > 5) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "법정동읍면동코드는 5자 이하입니다.";
	    CKeubmyundongCode = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
          CKeubmyundongCode = true;
	  }
	});
	
	let CKdongCode = true;
	document.querySelector("#dongCode").addEventListener("keyup", function () {
	  let dongCode = this.value;
	  let resultDiv = document.querySelector("#check-dongCode");
	  if ( dongCode.length > 10) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "지역코드는 10자 이하입니다.";
	    CKdongCode = false;
	  } else {
		  fetch("${root}/houeAdmin/dongCodeck?dongCode=" + dongCode)
          .then((response) => response.text())
          .then((data) => {
            console.log(data);
            if (data > 0) {
              resultDiv.setAttribute("class", "mb-3 text-primary");
              resultDiv.textContent = dongCode + "는 존재하는 지역코드입니다.";
              isUseId = true;
            } else {
              resultDiv.setAttribute("class", "mb-3 text-danger");
              resultDiv.textContent = dongCode + "는 존재하지않는 지역코드입니다.";
            }
          }); 
	  }
	});
	let CKlandCode = true;
	document.querySelector("#landCode").addEventListener("keyup", function () {
	  let landCode = this.value;
	  let resultDiv = document.querySelector("#check-landCode");
	  if ( landCode.length > 1) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "법정동지번코드는 1자 이하입니다.";
	    CKlandCode = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
          CKlandCode = true;
	  }
	});
	let CKjibun = true;
	document.querySelector("#jibun").addEventListener("keyup", function () {
	  let jibun = this.value;
	  let resultDiv = document.querySelector("#check-jibun");
	  if ( jibun.length > 10) {
	    resultDiv.setAttribute("class", "mb-3 text-danger");
	    resultDiv.textContent = "지번는 10자 이하입니다.";
	    CKjibun = false;
	  } else {
		  resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.textContent = "";
          CKjibun = true;
	  }
	});

	document.querySelector("#btn-register").addEventListener("click",
			function() {
				if (!document.querySelector("#apartmentName").value) {
					alert("아파트이름 입력하세요");
					return;
				} else if (!document.querySelector("#dongCode").value) {
					alert("지역코드(동코드)를 입력하세요");
					return;
				} else if (!(CKbuildYear && CKroadName && CKroadNameBonbun 
						&& CKroadNameBubun && CKroadNameSeq && CKroadNameBasementCode 
						&& CKroadNameCode && CKbonbun && CKbubun && CKsigunguCode 
						&& CKeubmyundongCode && CKdongCode && CKlandCode && CKjibun)) {
					alert("입력내용을 다시 확인하세요");
				} else {
					let form = document.querySelector("#form-modify");
					form.setAttribute("action", "${root}/houseAdmin/modify");
					form.submit();
				}
			});

	document.querySelector("#btn-list").addEventListener("click", function() {
		if (confirm("확인을 누르면 작성한 주택정보 삭제됩니다.\n취소하시겠습니까?")) {
			location.href = "${root}/houseAdmin/list";
		}
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
