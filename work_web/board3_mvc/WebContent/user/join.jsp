<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link href="${root}/assets/css/app.css" rel="stylesheet" />
<title>SSAFY</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="orange">회원가입</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<form id="form-join" method="POST" action="">

					<div class="mb-3">
						<label for="username" class="form-label">이름 : </label> <input
							type="text" class="form-control" id="username" name="username"
							placeholder="이름..." />
					</div>
					<div class="mb-3">
						<label for="userid" class="form-label">아이디 : </label> <input
							type="text" class="form-control" id="userid" name="userid"
							placeholder="아이디..." />
					</div>
					<div id="idcheck-result"></div>
					<div class="mb-3">
						<label for="userpwd" class="form-label">비밀번호 : </label> <input
							type="password" class="form-control" id="userpwd" name="userpwd"
							placeholder="비밀번호..." />
					</div>
					<div class="mb-3">
						<label for="pwdcheck" class="form-label">비밀번호확인 : </label> <input
							type="password" class="form-control" id="pwdcheck"
							placeholder="비밀번호확인..." />
					</div>
					<div class="mb-3">
						<label for="emailid" class="form-label">이메일 : </label>
						<div class="input-group">
							<input type="text" class="form-control" id="emailid"
								name="emailid" placeholder="이메일아이디" /> <span
								class="input-group-text">@</span> <select class="form-select"
								id="emaildomain" name="emaildomain" aria-label="이메일 도메인 선택">
								<option selected>선택</option>
								<option value="ssafy.com">싸피</option>
								<option value="google.com">구글</option>
								<option value="naver.com">네이버</option>
								<option value="kakao.com">카카오</option>
							</select>
						</div>
					</div>
					<!-- 
            <div class="mb-3">
              <label for="sido" class="form-label">지역 : </label>
              <div class="input-group">
                <select class="form-select me-1" id="sido" aria-label="시도">
                  <option selected>시도선택</option>
                  <option value="1100000000">서울특별시</option>
                  <option value="2200000000">경기도</option>
                  <option value="3300000000">강원도</option>
                  <option value="4400000000">충청도</option>
                </select>
                <select class="form-select" id="gugun" aria-label="구군">
                  <option selected>구군선택</option>
                  <option value="1111000000">종로구</option>
                  <option value="1112000000">중구</option>
                  <option value="1113000000">서초구</option>
                  <option value="1114000000">강남구</option>
                </select>
              </div>
            </div>
             -->
					<div class="col-auto text-center">
						<button type="button" id="btn-join"
							class="btn btn-outline-primary mb-3">회원가입</button>
						<button type="button" class="btn btn-outline-success mb-3">초기화</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script>
      let isUseId = false;
      document.querySelector("#userid").addEventListener("keyup", function () {
    	 let userid = this.value; // 누를 때마다 발생하는 값 -> 여기선 id
    	 let resultDiv = document.querySelector("#idcheck-result");
    	 if(userid.length < 6 || userid.length > 16) {
    		 resultDiv.setAttribute("class", "mb-3 text-dark"); // 이 색깔로
    		 resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다."; // 이거 찍어라 ^^ 
    		 isUseId = false;
    	 } else { // 6자리 이상 16자리 이하라면~
				 // 정상적인 입력 범위 => Server에 보내야 함
				 // data: db에서부터 이 id가 얼마나 있는지 리턴된 겂
				 console.log(userid);
				 fetch("${root}/user?act=idcheck&userid=" + userid)
				 .then(response => response.text()) // 어차피 1 아니면 0
				 .then(data => {
	    			 if(data == 0) { // 그 아이디로 가입한 사람이 없다? 사용 가능
	    			   resultDiv.setAttribute("class", "mb-3 text-primary");
	    		       resultDiv.textContent = userid + "는 사용할 수 있습니다.";
	    		       isUseId = true;
	    			 } else { // 있다? 사용 불가
	    			   resultDiv.setAttribute("class", "mb-3 text-danger");
	      		       resultDiv.textContent = userid + "는 사용할 수 없습니다.";
	      		       isUseId = false;
	    			 }
    			 });

    	 }
      });
      
      document.querySelector("#btn-join").addEventListener("click", function () {
        if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
          alert("비밀번호 확인!!");
          return;
        } else if (!isUseId) {
          alert("아이디 확인!!");
          return;
        } else {
          let form = document.querySelector("#form-join");
          form.setAttribute("action", "${root}/user");
          form.submit();
        }
      });
    </script>
</body>
</html>
