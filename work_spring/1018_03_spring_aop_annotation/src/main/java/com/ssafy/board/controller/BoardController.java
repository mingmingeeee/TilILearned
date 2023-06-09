package com.ssafy.board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;

public class BoardController {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// TODO : xml or annotation의 spring 설정을 읽어온다.
		ApplicationContext context = new ClassPathXmlApplicationContext("/application.xml");
		
		BoardService boardService = context.getBean(BoardService.class);
		BoardDto boardDto = new BoardDto();
		boardDto.setUserId("mingming");
		System.out.print("제목 : ");
		boardDto.setSubject(in.readLine());
		System.out.print("내용 : ");
		boardDto.setContent(in.readLine());

		try {
			boardService.writeArticle(boardDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("================================== 글목록 ================================== ");
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pgno", "1");
			map.put("key", "");
			map.put("word", "");
			List<BoardDto> list = boardService.listArticle(map);
			for(BoardDto article : list) {
				System.out.println(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
