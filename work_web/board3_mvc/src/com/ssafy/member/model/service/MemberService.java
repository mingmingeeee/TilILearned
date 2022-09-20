package com.ssafy.member.model.service;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception; // 아이디 중복 검사
	void joinMember(MemberDto memberDto) throws Exception; // 회원 가입
	MemberDto loginMember(String userId, String passPwd) throws Exception; // 로그인
	// 회원 정보 수정
	// 회원 탈퇴
	
}
