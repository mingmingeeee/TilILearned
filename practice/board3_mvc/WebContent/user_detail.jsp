<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${ pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구해줘 홈즈</title>
    <link rel="stylesheet" href="${root}/style.css" />
    <script src="./js/register.js" defer></script>
    <script src="./js/toggleNav.js" defer></script>
  </head>
  <body>
    <!-- 헤더 -->
    <%@ include file="/common/header.jsp" %>
    <main class="modification_register_container">
      <div class="modification_register-page">
        <h1>회원 정보 확인</h1>
        <div class="underbar"></div>
        <div>
	        <form class="modification__info" id="form-modify" method="post" action="">
	          <input type="hidden" id="act" name="act" value="modify">
	          <div>
	            <label for="modeId">아이디 *</label>
	            <input type="text"  name=userid value="${user.userId}"/>
	          </div>
	          <div>
	            <label for="modeId">비밀번호 *</label>
	            <input type="password" name=userpass value="${user.userPwd}"/>
	          </div>
	          <div>
	            <label for="modeId">이름 *</label>
	            <input type="text" name=username value="${user.userName}"/>
	          </div>
	          <div>
	            <label for="modeId">주소 *</label>
	            <input type="text" name=useremail value="${user.emailId}"/>
	          </div>
	          <div>
	            <label for="modeId">전화번호 *</label>
	            <input type="text" name=number value="${user.number}"/>
	          </div>
	        </form>
	
	        <div class="modal__btns">
	          <button type="button" class="modal__btn confirm" id="btn_confirm">
	            확인
	          </button>
	          <button type="button" class="modal__btn modify" id="btn-modify">수정</button>
	        </div>
	      </div>
      </div>
      
    </main>
   <script>
      document.querySelector("#btn-modify").addEventListener("click", function () {
         let form = document.querySelector("#form-modify");
         form.setAttribute("action", "${root}/user");
         form.submit();
      });
      
      document.querySelector("#btn_confirm").addEventListener("click", function () {
      	let form = document.querySelector("#form-modify");
        document.querySelector("#act").value="index";
        form.setAttribute("action", "${root}/user");
        form.submit();
      });
    </script>

    <%@ include file="/common/footer.jsp" %>
  </body>
</html>
