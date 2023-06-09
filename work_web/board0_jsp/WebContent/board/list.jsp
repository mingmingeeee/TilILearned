<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>

<%!@Override
	public void init() throws ServletException {
		super.init();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}%>

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
<link href="board0_jsp/assets/css/app.css" rel="stylesheet" />
<title>SSAFY</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="sky">글목록</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<div class="row align-self-center mb-2">
					<div class="col-md-2 text-start">
						<button type="button" id="btn-mv-register"
							class="btn btn-outline-primary btn-sm">글쓰기</button>
					</div>
					<div class="col-md-7 offset-3">
						<form class="d-flex">
							<select id="key"
								class="form-select form-select-sm ms-5 me-1 w-50"
								aria-label="검색조건">
								<option selected>검색조건</option>
								<option value="articleno">글번호</option>
								<option value="subject">제목</option>
								<option value="userid">작성자</option>
							</select>
							<div class="input-group input-group-sm">
								<input type="text" class="form-control" placeholder="검색어..." />
								<button class="btn btn-dark" type="button">검색</button>
							</div>
						</form>
					</div>
				</div>
				<table class="table table-hover">
					<thead>
						<tr class="text-center">
							<th scope="col">글번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">조회수</th>
							<th scope="col">작성일</th>
						</tr>
					</thead>
					<tbody>
						<%
							Connection conn = null;
							PreparedStatement pstmt = null;
							ResultSet rs = null;

							try {
								conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC", "ssafy",
										"ssafy");

								StringBuilder sql = new StringBuilder();

								sql.append("select article_no, user_id, subject, content, hit, register_time \n"); // 엔터 넣어주기!!!
								sql.append("from board \n");
								sql.append("order by article_no desc");

								pstmt = conn.prepareStatement(sql.toString());

								rs = pstmt.executeQuery();
								while (rs.next()) {
						%>
						<tr class="text-center">
							<th scope="row"><%= rs.getInt("article_no") %></th>
							<td class="text-start"><a href="#"
								class="article-title link-dark" data-no="<%= rs.getInt("article_no") %>"
								style="text-decoration: none"> <%= rs.getString("subject") %> </a></td>
							<td><%= rs.getString("uer_id") %></td>
							<td><%= rs.getInt("hit") %></td>
							<td><%= rs.getString("register_time") %></td>
						</tr>
						<%
							}

							} catch (SQLException e) {
								e.printStackTrace();
							} finally {
								try {
									if (rs != null)
										rs.close();
									if (pstmt != null)
										pstmt.close();
									if (conn != null)
										conn.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						%>
					</tbody>
				</table>
			</div>
			<div class="row">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="#">이전</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item active"><a class="page-link" href="#">2</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">4</a></li>
					<li class="page-item"><a class="page-link" href="#">5</a></li>
					<li class="page-item"><a class="page-link" href="#">다음</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script>
		let titles = document.querySelectorAll(".article-title");
		titles.forEach(function(title) {
			title.addEventListener("click", function() {
				location.href = "/board0_jsp/board/view.jsp?article_no=" + this.getAttribute("data-no");
			});
		});

		document.querySelector("#btn-mv-register").addEventListener("click",
				function() {
					location.href = "/board0_jsp/board/write.jsp";
				});
	</script>
</body>
</html>
