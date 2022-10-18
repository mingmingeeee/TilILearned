package com.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;

@Service
public class BoardServiceTempImpl implements BoardService {

	@Override
	public int writeArticle(BoardDto boardDto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
