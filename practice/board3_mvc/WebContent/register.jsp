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
    
    <main class="main__container">
      <div class="main__register-page">
        <h1 id="register">회원가입</h1>
        <div class="main__form">
          <form method="post" action="${root}/user" name="register-form" class="main__register-form">
          	<input type="hidden" name="act" value="join">
            <input id="userid" name="userid" type="text" placeholder="아이디" />
            <input
              id="userpwd"
              name="userpwd"
              type="password"
              placeholder="비밀번호"
            />
            <input id="username" name="username" type="text" placeholder="성함" />
            <input id="emailid" name="emailid" type="text" placeholder="주소" />
            <input
              id="number"
              name="number"
              type="tel"
              placeholder="전화번호"
            />
            <button id="register-btn" type="submit">회원가입</button>
            <p>
              계정이 있습니다.<u><a href="${root}/user?act=mvlogin">로그인</a></u>
            </p>
          </form>
        </div>
      </div>
    </main>
    
    <%@ include file="/common/footer.jsp" %>
  </body>
</html>
