package com.ssafy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {
	
	private static MemberDao memberDao = new MemberDaoImpl();
	private DBUtil dbUtil;
	
	private MemberDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}
	

	@Override
	public int idCheck(String userId) throws SQLException {
		int cnt = 1; // 기본 값은 0만 아니면 됨...^^;;; 왜냐?
		// 잘 돼도 0, 안 돼도 0이 나올 것이기 때문 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(user_id)\n"); // -> count!!!!가 있으므로 단독으로 rs.next()해도 됨
			sql.append("from members \n");
			sql.append("where user_id = ?");
			System.out.println(userId);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1); // index, column name 둘 중 하나 써주면 됨
			// 여기서는 index -> count(user_id) -> 1번쨰 컬럼!!!
		} finally { // 예외 잡지는 말고 연결은 닫아줘라~
			dbUtil.close(rs, pstmt, conn);
		}
		
		return cnt;
	}

	@Override
	public void joinMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into members (user_id, user_name, user_password, email_id, email_domain, join_date) \n");
			sql.append("values (?, ?, ?, ?, ?, now())");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 0;
			pstmt.setString(++idx, memberDto.getUserId());
			pstmt.setString(++idx, memberDto.getUserName());
			pstmt.setString(++idx, memberDto.getUserPwd());
			pstmt.setString(++idx, memberDto.getEmailId());
			pstmt.setString(++idx, memberDto.getEmailDomain());
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select user_id, user_name \n"); // -> count!!!!가 있으므로 단독으로 rs.next()해도 됨
			sql.append("from members \n");
			sql.append("where user_id = ? and user_password = ?");
			
			System.out.println(userId);
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 얻어왔다면
				// memberDto 만들자!
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
			}
//			cnt = rs.getInt(1);
			// 여기서는 index -> count(user_id) -> 1번쨰 컬럼!!!
		} finally { // 예외 잡지는 말고 연결은 닫아줘라~
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

}
