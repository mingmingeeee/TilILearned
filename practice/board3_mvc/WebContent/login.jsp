<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${ pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>

<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구해줘 홈즈</title>
    <link rel="shortcut icon" href="${root}/assets/images/favicon.png" type="image/x-icon" />
    <link rel="stylesheet" href="${root}/style.css" />
    <script src="./js/toggleHamberger.js" defer></script>
    <script src="./js/login.js" defer></script>
    <script src="./js/toggleNav.js" defer></script>
  </head>
  <body>
    <!-- 헤더 -->
    <%@ include file="/common/header.jsp" %>
    
    <main class="main__container">
      <div class="main__login-page">
        <h1>로그인</h1>
        <div class="main__form">
          <form method="post" action="${root}/user" name="login-form" class="main__login-form">
          	<input type="hidden" name="act" value="login">
            <input id="userid" name="userid" type="text" placeholder="ID" />
            <input
              id="userpwd"
              name="userpwd"
              type="password"
              placeholder="password"
            />
    		<p style="color:red; padding-bottom: 10px">${msg}</p>
            <button id="login-btn" type="submit">로그인</button>
            <p>
              계정이 없으신가요?
              <u><a href="${ root }/user?act=mvjoin">회원가입</a></u>
            </p>
            <p>
              비밀번호를 잊으셨나요?
              <u><a href="#" id="find-password">비밀번호 찾기</a></u>
            </p>
          </form>
        </div>
      </div>
    </main>
    <%@ include file="/common/footer.jsp" %>
  </body>
</html>
