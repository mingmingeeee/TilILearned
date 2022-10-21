package com.ssafy.board.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.util.SqlMapConfig;

@Repository
public class BoardDaoImpl implements BoardDao {

	private final String NAMESPACE = "com.ssafy.board.model.dao.BoardDao.";

	@Override
	public int writeArticle(BoardDto boardDto) throws SQLException {

		int articleNo = 0;

		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.insert(NAMESPACE + "writeArticle", boardDto);
			articleNo = sqlSession.selectOne(NAMESPACE + "getLastNo"); 
			
			Map<String,Object> map = new HashMap <String,Object>();
			map.put("list", boardDto.getFileInfos());
			map.put("articleNo", articleNo);
			
			sqlSession.insert(NAMESPACE + "uploadFiles", map);
			sqlSession.commit();
		}

		return articleNo;
	}

	@Override
	public List<BoardDto> listArticle(Map<String, Object> map) throws SQLException {

		// map: pgno, key, word
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectList(NAMESPACE + "listArticle", map);
		}

	}

	@Override
	public int getTotalArticleCount(Map<String, String> map) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectOne(NAMESPACE + "getTotalArticleCount", map);
		}
	}

	@Override
	public BoardDto getArticle(int articleNo) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			BoardDto boardDto = sqlSession.selectOne(NAMESPACE + "getArticle", articleNo);
			List<FileInfoDto> list = sqlSession.selectList(NAMESPACE + "getFileInfos", articleNo);
			boardDto.setFileInfos(list);
			return boardDto;
		}
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.update(NAMESPACE + "updateHit", articleNo);
			sqlSession.commit();
		}
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.update(NAMESPACE + "modifyArticle", boardDto);
			sqlSession.commit();
		}
	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()){
			sqlSession.delete(NAMESPACE + "deleteFile", articleNo);
			sqlSession.delete(NAMESPACE + "deleteArticle", articleNo);
			sqlSession.commit();
		}
	}

}
