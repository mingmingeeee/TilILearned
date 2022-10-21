package com.ssafy.member.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ssafy.member.model.MemberDto;
import com.ssafy.util.SqlMapConfig;

@Repository
public class MemberDaoImpl implements MemberDao {

	// sql문을 실행할 수 있는 유일한 곳 설정
	private final String NAMESPACE = "com.ssafy.member.model.dao.MemberDao.";

	@Override
	public int idCheck(String userId) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			// selectOne(statement, parameter)
			// 			어떤 걸 호출할래?, 인자로 뭘 보낼래?
			return sqlSession.selectOne(NAMESPACE + "idCheck", userId);
		}
	}

	@Override
	public void joinMember(MemberDto memberDto) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.insert(NAMESPACE + "joinMember", memberDto);
			sqlSession.commit();
		}
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			// MyBatis는 파라미터 2개 이상 받을 수 없기 때문에 map으로 담아서 가져옴
			// 이거 하려고 Dto를 굳이 만들 필요는 없고 map으로 받아오면 됨
			return sqlSession.selectOne(NAMESPACE + "loginMember", map);
			// => 유일한 곳 찾아가보면 member.xml => 여기 namespace랑 package 일치해야 이동 가능함 
		}
	}

}
