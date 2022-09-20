<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<%! @Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} %>
	<%
	if(request.getMethod().equals("GET")) {
		response.setContentType("text/html;charset=utf-8");
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		System.out.println(article_no);
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC", "ssafy",
					"ssafy");
	
			StringBuilder sql = new StringBuilder();
			
			pstmt = conn.prepareStatement(sql.toString());
			
			sql.append("select user_id, subject, content \n");
			sql.append("from board \n");
			sql.append("where article_no=" + article_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { %>
		
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
      crossorigin="anonymous"
    />
    <link href="board0_jsp/assets/css/app.css" rel="stylesheet" />
    <title>SSAFY</title>
  </head>
  <body>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글수정</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <form id="form-modify" method="POST" action="">
          <div class="mb-4">
              <label for="article_no" class="form-label">글 번호 : </label>
              <input type="text" class="form-control" id="article_no" name="article_no" value=<%= article_no %> readonly />
            </div>
            <div class="mb-4">
              <label for="userid" class="form-label">작성자 ID : </label>
              <input type="text" class="form-control" id="userid" name="userid" value=<%= rs.getString("user_id") %> readonly />
            </div>
            <div class="mb-4">
              <label for="subject" class="form-label">제목 : </label>
              <input type="text" class="form-control" id="subject" name="subject" value=<%= rs.getString("subject") %> />
            </div>
            <div class="mb-4">
              <label for="content" class="form-label">내용 : </label>
              <textarea class="form-control" id="content" rows="7">
<%= rs.getString("content") %>
              </textarea>
            </div>
            <div class="col-auto text-center">
              <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3">
                글수정
              </button>
              <button type="button" id="bnt-list" class="btn btn-outline-danger mb-3">
                목록으로이동...
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <%
			}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if(rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		} else {
			
			request.setCharacterEncoding("utf-8"); // Post 방식일 때만 처리!! Get은 안 해도 안 깨짐
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			int article_no = Integer.parseInt(request.getParameter("article_no"));
			Connection conn = null;
			PreparedStatement pstmt = null;
			int cnt = 0;

			try {
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC", "ssafy",
						"ssafy");

				StringBuilder sql = new StringBuilder();
				
				sql.append("update board set subject=?, content=?, register_time=now() \n");
				sql.append("where article_no=?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, subject);
				pstmt.setString(2, content);
				pstmt.setInt(3, article_no);
				
				cnt = pstmt.executeUpdate();

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
			
			response.sendRedirect("list.jsp");
		}%>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
    <script>
      document.querySelector("#btn-modify").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-modify");
          form.setAttribute("action", "modify.jsp");
          form.submit();
        }
      });
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "";
      });
    </script>
  </body>
</html>

