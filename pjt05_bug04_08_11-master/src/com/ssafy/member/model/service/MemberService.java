package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception; // 아이디 중복검사
	void joinMember(MemberDto memberDto) throws Exception; // 회원가입
	MemberDto loginMember(String userId, String userPwd) throws Exception; // 로그인
	MemberDto viewMember(String userId) throws SQLException;
	void usermodify(MemberDto memberDto) throws SQLException;
	void deleteMember(String userid) throws SQLException;
	List<MemberDto> listUser(Map<String, String> map) throws SQLException;
	int getUserListCnt(Map<String, String> map) throws SQLException;
	int saveFavorit(String code) throws SQLException;
	
}

