package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dao.MemberDaoImpl;
import com.ssafy.util.SizeConstant;

public class MemberServiceImpl implements MemberService {
	
	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
	}
	
	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberDao.idCheck(userId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		// TODO validation check
		memberDao.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		return memberDao.loginMember(userId, userPwd);
	}

	@Override
	public MemberDto viewMember(String userId) throws SQLException {
		return  memberDao.viewMember(userId);
	}

	@Override
	public void usermodify(MemberDto memberDto) throws SQLException {
		memberDao.usermodify(memberDto);
		
	}

	@Override
	public void deleteMember(String userid) throws SQLException {
		memberDao.deleteMember(userid);
		
	}

	@Override
	public List<MemberDto> listUser(Map<String, String> map) throws SQLException {
		int pgno = Integer.parseInt(map.get("pgno"));
		int spl = SizeConstant.SIZE_PER_LIST;
		int start = (pgno - 1) * spl;
		map.put("start", start + "");
		map.put("spl", spl + "");
		return memberDao.listUser(map);
	}

	@Override
	public int getUserListCnt(Map<String, String> map) throws SQLException {
		// TODO Auto-generated method stub
		return memberDao.getUserListCnt(map);
	}

	@Override
	public int saveFavorit(String code) throws SQLException {
		// TODO Auto-generated method stub
		return memberDao.insertFavorit(code);
	}

}

