package com.ssafy.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDelete
 */
@WebServlet("/delete")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDelete() {
        super();
    }
    
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC" ,"ssafy", "ssafy");
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from board \n");
			sql.append("where article_no=" + article_no);
			
			pstmt = conn.prepareStatement(sql.toString());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt != null)
						pstmt.close();
					if(conn != null)
						conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		response.sendRedirect("/board1_servlet/list");
		
	}

}
