package com.ssafy.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class BoardModify
 */
@WebServlet("/modify")
public class BoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardModify() {
		super();
	}

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		int article_no = Integer.parseInt(request.getParameter("article_no"));
		System.out.println(article_no);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC", "ssafy",
					"ssafy");

			StringBuilder sql = new StringBuilder();
			
			sql.append("select user_id, subject, content \n");
			sql.append("from board \n");
			sql.append("where article_no=" + article_no);
			
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("user_id"));
				out.println("<!DOCTYPE html>");
				out.println("<html lang=\"ko\">");
				out.println("  <head>");
				out.println("    <meta charset=\"UTF-8\" />");
				out.println("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />");
				out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
				out.println("    <link");
				out.println("      href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\"");
				out.println("      rel=\"stylesheet\"");
				out.println("      integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\"");
				out.println("      crossorigin=\"anonymous\"");
				out.println("    />");
				out.println("    <link href=\"/board1_servlet/assets/css/app.css\" rel=\"stylesheet\" />");
				out.println("    <title>SSAFY</title>");
				out.println("  </head>");
				out.println("  <body>");
				out.println("    <div class=\"container\">");
				out.println("      <div class=\"row justify-content-center\">");
				out.println("        <div class=\"col-lg-8 col-md-10 col-sm-12\">");
				out.println("          <h2 class=\"my-3 py-3 shadow-sm bg-light text-center\">");
				out.println("            <mark class=\"sky\">글수정</mark>");
				out.println("          </h2>");
				out.println("        </div>");
				out.println("        <div class=\"col-lg-8 col-md-10 col-sm-12\">");
				out.println("          <form id=\"form-modify\" method=\"POST\" action=\"\">");
				out.println("            <div class=\"mb-4\">");
				out.println("              <label for=\"userid\" class=\"form-label\">글 번호 : </label>");
				out.println("              <input type=\"article_no\" class=\"form-control\" id=\"article_no\" name=\"article_no\" value=" + article_no +" readonly />");
				out.println("            </div>");
				out.println("            <div class=\"mb-4\">");
				out.println("              <label for=\"userid\" class=\"form-label\">작성자 ID : </label>");
				out.println("              <input type=\"text\" class=\"form-control\" id=\"userid\" value=" + rs.getString("user_id") +" readonly />");
				out.println("            </div>");
				out.println("            <div class=\"mb-4\">");
				out.println("              <label for=\"subject\" class=\"form-label\">제목 : </label>");
				out.println("              <input type=\"text\" class=\"form-control\" id=\"subject\" name=\"subject\" value=" + rs.getString("subject") + " />");
				out.println("            </div>");
				out.println("            <div class=\"mb-4\">");
				out.println("              <label for=\"content\" class=\"form-label\">내용 : </label>");
				out.println("              <textarea class=\"form-control\" id=\"content\" name=\"content\" rows=\"7\">");
				out.println(rs.getString("content"));
				out.println("              </textarea>");
				out.println("            </div>");
				out.println("            <div class=\"col-auto text-center\">");
				out.println("              <button type=\"button\" id=\"btn-modify\" class=\"btn btn-outline-primary mb-3\">");
				out.println("                글수정");
				out.println("              </button>");
				out.println("              <button type=\"button\" id=\"bnt-list\" class=\"btn btn-outline-danger mb-3\">");
				out.println("                목록으로이동...");
				out.println("              </button>");
				out.println("            </div>");
				out.println("          </form>");
				out.println("        </div>");
				out.println("      </div>");
				out.println("    </div>");
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
		
		out.println("    <script");
		out.println("      src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"");
		out.println("      integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\"");
		out.println("      crossorigin=\"anonymous\"");
		out.println("    ></script>");
		out.println("    <script>");
		out.println("      document.querySelector(\"#btn-modify\").addEventListener(\"click\", function () {");
		out.println("        if (!document.querySelector(\"#subject\").value) {");
		out.println("          alert(\"제목 입력!!\");");
		out.println("          return;");
		out.println("        } else if (!document.querySelector(\"#content\").value) {");
		out.println("          alert(\"내용 입력!!\");");
		out.println("          return;");
		out.println("        } else {");
		out.println("          let form = document.querySelector(\"#form-modify\");");
		out.println("          form.setAttribute(\"action\", \"/board1_servlet/modify\");");
		out.println("          form.submit();");
		out.println("        }");
		out.println("      });");
		out.println("      document.querySelector(\"#btn-list\").addEventListener(\"click\", function () {");
		out.println("        location.href = \"/board1_servlet/list\";");
		out.println("      });");
		out.println("    </script>");
		out.println("  </body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // Post 방식일 때만 처리!! Get은 안 해도 안 깨짐
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		int article_no = Integer.parseInt(req.getParameter("article_no"));
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
		
		resp.sendRedirect("/board1_servlet/list");

	}
	

}
