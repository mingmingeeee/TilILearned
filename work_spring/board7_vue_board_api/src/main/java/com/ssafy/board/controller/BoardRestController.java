package com.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;

@RestController
@RequestMapping("/api/board")
@CrossOrigin("*")
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping
	public ResponseEntity<?> listArticle(@RequestParam Map<String, String> map) {
		try {
		List<BoardDto> listArticle = boardService.listArticle(map);
		return new ResponseEntity<List<BoardDto>> (listArticle, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{articleno}")
	public ResponseEntity<?> getArticle(@PathVariable("articleno") int articleno) {
		try {
			boardService.updateHit(articleno);
			BoardDto article = boardService.getArticle(articleno);
			
			return new ResponseEntity<BoardDto> (article, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{articleno}")
	public ResponseEntity<?> ModifyAtricle(@PathVariable("articleno") int articleno, @RequestBody BoardDto boardDto) {
		try {
			boardDto.setArticleNo(articleno);
			boolean isModify = boardService.modifyArticle(boardDto);
			if(isModify) {
				return new ResponseEntity<Void> (HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void> (HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{articleno}")
	public ResponseEntity<?> DeleteArticle(@PathVariable("articleno") int articleno){
		try {
			boolean isDelete = boardService.deleteArticle(articleno, null);
			if(isDelete) {
				return new ResponseEntity<Void> (HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void> (HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> DeleteArticle(@RequestBody BoardDto boardDto){
		try {
			boolean isWrite = boardService.writeArticle(boardDto);
			if(isWrite) {
				return new ResponseEntity<Void> (HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void> (HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
