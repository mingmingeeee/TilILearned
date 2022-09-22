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
		int cnt = 1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(user_id) \n");
			sql.append("from members \n");
			sql.append("where user_id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,  userId);
			
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		}
		finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return cnt;
	}

	@Override
	public int joinMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO members (user_id, user_name, user_password, email_id, number, join_date) \n");
			sql.append("VALUES (?, ?, ?, ?, ?, NOW())");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, memberDto.getUserId());
			pstmt.setString(++idx, memberDto.getUserName());
			pstmt.setString(++idx, memberDto.getUserPwd());
			pstmt.setString(++idx, memberDto.getEmailId());
			pstmt.setString(++idx, memberDto.getNumber());
			
			pstmt.executeUpdate();
		}
		finally {
			dbUtil.close(pstmt, conn);
		}
		
		return 0;
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
			sql.append("SELECT user_id, user_name \n");
			sql.append("FROM members \n");
			sql.append("WHERE user_id = ? AND user_password = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
			}
		}
		finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return memberDto;
	}

	@Override
	public MemberDto userDetail(String id) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT user_id, user_name, user_password, email_id, number\n");
			sql.append("FROM members \n");
			sql.append("WHERE user_id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setNumber(rs.getString("number"));
				memberDto.setUserPwd(rs.getString("user_password"));
			}
		}
		finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return memberDto;
	}

	@Override
	public void modify(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE members \n");
			sql.append("SET user_name = ?, user_password = ?, email_id = ?, number = ? \n");
			sql.append("WHERE user_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			System.out.println(memberDto.getUserName());
			System.out.println(memberDto.getUserId());
			
			pstmt.setString(1, memberDto.getUserName());
			pstmt.setString(2, memberDto.getUserPwd());
			pstmt.setString(3, memberDto.getEmailId());
			pstmt.setString(4, memberDto.getNumber());
			pstmt.setString(5, memberDto.getUserId());
			
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt);
		}
		finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
