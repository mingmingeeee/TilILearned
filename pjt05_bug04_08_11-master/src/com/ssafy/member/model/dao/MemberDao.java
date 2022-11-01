package com.ssafy.member.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberDao {

	int idCheck(String userId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(String userId, String userPwd) throws SQLException;
	MemberDto viewMember(String userId) throws SQLException;
	void usermodify(MemberDto memberDto) throws SQLException;
	void deleteMember(String userid) throws SQLException;
	List<MemberDto> listUser(Map<String, String> map) throws SQLException;
	int getUserListCnt(Map<String, String> map) throws SQLException;
	int insertFavorit(String code)  throws SQLException;
	
}
