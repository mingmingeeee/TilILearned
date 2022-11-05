package com.ssafy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.util.PageNavigation;

@RestController
@RequestMapping("/boards")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private ServletContext servletContext;

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		logger.info("BoardController 생성자 호출 !!!");
		this.boardService = boardService;
	}


	@PostMapping(value = "/article")
	public ResponseEntity<?> boardWrite(HttpSession session, @RequestBody BoardDto boardDto) {
		logger.debug("userRegister memberDto : {}", boardDto);
		try {
			MemberDto loginUser = (MemberDto) (session.getAttribute("userinfo"));
			String userid = loginUser.getUserId();
			
			boardDto.setUserId(userid);
			boardService.writeArticle(boardDto);

			return new ResponseEntity<String>("wrote", HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	
	@GetMapping(value = {"/board", "/board/{pgno}", "/board/{pgno}/{key}","/board/{pgno}/{key}/{word}"})
	public ResponseEntity<?> userList(@PathVariable("pgno") Optional<String> pgno, @PathVariable("key") Optional<String> key, @PathVariable("word") Optional<String> word) {
		logger.debug("userList call");
		try {
			Map<String, String> map = new HashMap<>();
			if(pgno.isPresent()) 
				map.put("pgno", pgno.get());
			if(key.isPresent())
				map.put("key", key.get());
			if(word.isPresent())
				map.put("word", word.get());
			
			Map<String, Object> data = new HashMap<>();
			List<BoardDto> list = boardService.listArticle(map);
			PageNavigation pageNavigation = boardService.makePageNavigation(map);
			data.put("articles", list);
			data.put("navigation", pageNavigation);
			if(pgno.isPresent()) 
				data.put("pgno", pgno.get());
			if(key.isPresent())
				data.put("key", key.get());
			if(word.isPresent())
				data.put("word", word.get());

			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}
	
	@GetMapping("/article/{articleNo}")
	public ResponseEntity<?> userList(@PathVariable int articleNo) {
		logger.debug("userList call");
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			

			if (boardDto != null) {
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	@PutMapping("/article")
	public ResponseEntity<?> modify(@RequestBody BoardDto boardDto) throws Exception {
		logger.debug("modify boardDto : {}", boardDto);
		boardService.modifyArticle(boardDto);
		
		BoardDto boardInfo = boardService.getArticle(boardDto.getArticleNo());
		return new ResponseEntity<BoardDto> (boardInfo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/article/{articleNo}")
	public ResponseEntity<?> userDelete(@PathVariable int articleNo) {
		try {
			boardService.deleteArticle(articleNo);

			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
