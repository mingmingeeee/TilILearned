package com.ssafy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
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
			sql.append("select user_name \n");
			sql.append("from members \n");
			sql.append("where user_id = ? and user_password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(userId);
				memberDto.setUserName(rs.getString("user_name"));
			}
		} finally {
			dbUtil.close(rs, pstmt);
		}
		return memberDto;
	}

	@Override
	public MemberDto viewMember(String userId) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select user_id, user_name, user_password, email_id, email_domain, join_date \n");
			sql.append("from members \n");
			sql.append("where user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserPwd(rs.getString("user_password"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setEmailDomain(rs.getString("email_domain"));
				memberDto.setJoinDate(rs.getString("join_date"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

	@Override
	public void usermodify(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE members \n");
			sql.append("SET user_name = ?, user_password=?, email_id=?, email_domain=? \n");
			sql.append("WHERE user_id= ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, memberDto.getUserName());
			pstmt.setString(++idx, memberDto.getUserPwd());
			pstmt.setString(++idx, memberDto.getEmailId());
			pstmt.setString(++idx, memberDto.getEmailDomain());
			pstmt.setString(++idx, memberDto.getUserId());
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteMember(String userid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql3 = new StringBuilder();
			sql3.append("delete from favarea \n");
			sql3.append("WHERE user_id= ?");
			pstmt3 = conn.prepareStatement(sql3.toString());
			pstmt3.setString(1, userid);
			pstmt3.executeUpdate();
			
			
			StringBuilder sql2 = new StringBuilder();
			sql2.append("delete from board \n");
			sql2.append("WHERE user_id= ?");
			pstmt2 = conn.prepareStatement(sql2.toString());
			pstmt2.setString(1, userid);
			pstmt2.executeUpdate();
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from members \n");
			sql.append("WHERE user_id= ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userid);

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt3, pstmt2, pstmt, conn);
		}
	}

	@Override
	public List<MemberDto> listUser(Map<String, String> map) throws SQLException {
		List<MemberDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select user_id, user_name, email_id, email_domain, join_date \n");
			sql.append("from members \n");
			String key = map.get("key");
			String word = map.get("word");
			if(!word.isEmpty()) {
				if("subject".equals(key))
					sql.append("where user_name like ? \n");
				else
					sql.append("where user_id = ? \n");
			}
			sql.append("order by join_date desc limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if(!word.isEmpty()) {
				if("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else 
					pstmt.setString(++idx, word);
			}
			pstmt.setInt(++idx, Integer.parseInt(map.get("start")));
			pstmt.setInt(++idx, Integer.parseInt(map.get("spl")));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setEmailId(rs.getString("email_id"));
				memberDto.setEmailDomain(rs.getString("email_domain"));
				memberDto.setJoinDate(rs.getString("join_date"));
				
				list.add(memberDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int getUserListCnt(Map<String, String> map) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(user_id) cnt \n");
			sql.append("from members \n");
			String key = map.get("key");
			String word = map.get("word");
			if(!word.isEmpty()) {
				if("subject".equals(key))
					sql.append("where user_name like ? \n");
				else
					sql.append("where user_id = ? \n");
			}
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if(!word.isEmpty()) {
				if("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else 
					pstmt.setString(++idx, word);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				return rs.getInt("cnt");
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return 0;
	}

	@Override
	public int insertFavorit(String code) throws SQLException {
		System.out.println("insertFavorit is called : ");
		return 0;
	}

}
