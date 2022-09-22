<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${ pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>구해줘 홈즈</title>
<link rel="shortcut icon" href="${root}/assets/images/favicon.png"
	type="image/x-icon" />
<link rel="stylesheet" href="${root}/style.css" />
<script src="./js/toggleHamberger.js" defer></script>
<script src="./js/modal.js" defer></script>
<script src="./js/index.js" defer></script>
<script src="./js/toggleNav.js" defer></script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<!-- 메인 -->
	<main class="index__main"> <!-- 아파트, 연립다세대, 토지 매매 부분-->
	<section id="estate" class="section">
		<header class="section__header">
			<strong class="section__tag"> 매매 </strong>
			<h1 class="section__title">원하는 매물을 찾아보세요</h1>
		</header>

		<div class="estate">
			<div class="estate__card apt">
				<img class="estate__icon" src="${root}/assets/images/apt_1.svg"
					alt="" />
				<h2 class="estate__title _1">연립 다세대</h2>
				<p class="estate__text">
					2-3인 가구라면 <br /> 다세대주택은 어떠신가요?
				</p>
			</div>
			<div class="estate__card apt2">
				<img class="estate__icon" src="${root}/assets/images/apt_2.svg"
					alt="" />
				<h2 class="estate__title _2">아파트</h2>
				<p class="estate__text">
					<a href="${root}/apt.jsp">4인 가구라면 <br /> 아파트를 추천드립니다.</a>
				</p>
			</div>
			<div class="estate__card land">
				<img class="estate__icon" src="${root}/assets/images/land_1.svg"
					alt="" />
				<h2 class="estate__title _3">토지</h2>
				<p class="estate__text">
					토지를 매입해서<br /> 귀농에 도전해보세요.
				</p>
			</div>
		</div>
	</section>

	<!-- 게시판 부분--> <!-- 인기글 -->
	<section id="popular" class="section">
		<header class="section__header">
			<strong class="section__tag"> 인기글 </strong>
			<h1 class="section__title">자유게시판</h1>
		</header>
		<article class="section__main _popular">
			<table class="section__table">
				<thead>
					<tr class="section__table--head">
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>싸피 최강미남 찬형이에요!!!! <span>2387</span>
						</td>
						<td>박찬형</td>
						<td>250</td>
					</tr>
					<tr>
						<td>이수민 그는 신인가?? <span>712</span></td>
						<td>이수민</td>
						<td>185</td>
					</tr>
					<tr>
						<td>코딩의 왕자 <span>1526</span></td>
						<td>공예찬</td>
						<td>136</td>
					</tr>
				</tbody>
			</table>
		</article>
	</section>

	<!-- 최신글 -->
	<section id="recent" class="section">
		<header class="section__header">
			<strong class="section__tag"> 최신글 </strong>
			<h1 class="section__title"><a href="${root}/board?act=list">자유게시판</a></h1>
		</header>
		<article class="section__main _recent">
			<table>
				<thead>
					<tr>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>히주니에요</td>
						<td>전희준</td>
						<td>11</td>
					</tr>
					<tr>
						<td>성난키보드!!! <span>20</span></td>
						<td>정원영</td>
						<td>12</td>
					</tr>
					<tr>
						<td>왜안될까???</td>
						<td>정은영</td>
						<td>7</td>
					</tr>
				</tbody>
			</table>
		</article>
	</section>
	</main>
	<section id="ad">
		<h1>광고 주세요</h1>
	</section>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
