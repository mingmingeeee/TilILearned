<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${ pageContext.request.contextPath }"></c:set>
<!-- 히어로 섹션 -->
    <section class="intro">
      <div class="intro__dark">
        <div class="intro__title">
          <div><span aria-hidden="true">🏠</span>나만의 집</div>
          <div>구하기!<span class="underscore">_</span></div>
        </div>
      </div>
      <div class="intro__tilt"></div>
      <div class="intro__tilt--flip"></div>
    </section>

    <!-- 헤더 -->
    <header class="header">
      <h1>
        <a class="header__home-link" href="${root}/user?act=index"> WhereIsMyHome </a>
      </h1>
      <a href="#" class="header__menu-btn">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
      </a>
      <nav class="header__nav">
        <ul>
          <li class="header__nav-item">
            <a href="#">공지사항</a>
          </li>
          <li class="header__nav-item">
            <a href="#">아파트정보</a>
          </li>
        </ul>

        <ul>
       	 <c:if test="${ empty userinfo }">
          <li class="header__nav-item">
            <a href="${ root }/user?act=mvjoin">회원가입</a>
          </li>
          <li class="header__nav-item">
            <a href="${ root }/user?act=mvlogin">로그인</a>
          </li>
          </c:if>
          <c:if test="${ ! empty userinfo }">
	          <li class="header__nav-item hide" id="logout">
	            <a href="${ root }/user?act=logout" id="logout">로그아웃</a>
	          </li>
	          
	          <li class="header__nav-item hide" id="info">
	            <a href="${ root }/user?act=detail_user">회원정보</a>
	          </li>
          </c:if>
        </ul>
      </nav>
    </header>