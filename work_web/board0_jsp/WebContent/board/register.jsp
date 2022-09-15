<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<%!@Override // 선언문 
	public void init() throws ServletException {
		super.init();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}%>

<%  // 처리문
	//1. data get -> 작성자 ID, 제목, 내용 얻어내야 함
	request.setCharacterEncoding("utf-8"); // Post 방식일 때만 처리!! Get은 안 해도 안 깨짐
	String userId = request.getParameter("userid");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	System.out.println(userId + " " + subject + " " + content);

	//2. logic
	//DB 작업 순서 -> JDBC
	//1. Driver loading Class.forName 
	//2. DB 연결(Connection) DriverManager.getConnection 
	//3. SQL실행 준비 1) String sql = , 2) Statement or PreparedStatement = ? set set set  
	//4. 실행 -> Insert,Update, Delete - exceteUpdate, Select - execteQuery->ResultSet next(), getXXX얻어내기
	//5. close - 연결한 거 역순으로 닫기 R.S close, P.S close, Connection Close

	//2. DB 연결(Connection) DriverManager.getConnection -> 요청마다 필요 (한 번만 연결 X)
	Connection conn = null;
	PreparedStatement pstmt = null;
	int cnt = 0;

	try {
		// DriverManager.getConnection
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC", "ssafy",
				"ssafy");

		//	3. SQL실행 준비 1) String sql = , 2) Statement or PreparedStatement = ? set set set  
		StringBuilder sql = new StringBuilder();

		// article no: 자동 증가되니까 AutoIncrement -> 넣을 필요 ㄴㄴ
		// hit: 조회수
		sql.append("insert into board (user_id, subject, content, hit, register_time) \n"); // 엔터 넣어주기!!!
		sql.append("values (?, ?, ?, 0, now())"); // -> ";": executeUpdate, excuteQuery 친구들임

		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, userId);
		pstmt.setString(2, subject);
		pstmt.setString(3, content);

		cnt = pstmt.executeUpdate(); // 성공 실패 가름 -> 바깥으로 뺌

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
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
					<mark class="sky">글쓰기 결과</mark>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
			
			<% if(cnt != 0) { %>
				<div class="card text-center bg-light">
					<h2 class="fw-bold text-primary pt-3">등록 완료!!!</h2>
					<div class="card-body">
						<p class="card-text">글작성이 완료되었습니다.</p>
						<button type="button" id="btn-list"
							class="btn btn-outline-primary">글목록 페이지 이동...</button>
					</div>
				</div>
				<% } else { %>
				<div class="card text-center bg-light">
					<h2 class="fw-bold text-danger pt-3">등록 실패T.T</h2>
					<div class="card-body">
						<p class="card-text">
							글작성에 문제가 있습니다. <br /> 잠시 후 시도해주세요.
						</p>
						<button type="button" id="btn-list" class="btn btn-outline-danger">
							글목록 페이지 이동...</button>
					</div>
				</div>
			</div>
			<% }; %>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script>
		document.querySelector("#btn-list").addEventListener("click",
				function() {
					location.href = "/board0_jsp/board/list.jsp";
				});
	</script>
</body>
</html>
