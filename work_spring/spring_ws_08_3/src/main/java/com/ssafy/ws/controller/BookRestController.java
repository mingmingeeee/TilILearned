package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;
import com.ssafy.ws.model.service.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bookapi")
@CrossOrigin("*")
public class BookRestController {

	@Autowired
	private BookService bookService;

	// isbn 번호로 도서 조회
	@GetMapping("/book/{isbn}")
	@ApiOperation(value = "{isbn}에 해당하는 도서 정보를 반환한다.", response = Book.class)
	public ResponseEntity<?> select(@PathVariable String isbn) {
		try {
			Book book = bookService.select(isbn);
			if (book != null) {
				return new ResponseEntity<Book>(book, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 검색 조건에 맞는 도서 목록 반환
	@GetMapping("/book")
	@ApiOperation(value = "query string에 해당하는 검색 조건에 해당하는 도서 목록을 반환한다.", response = Book.class)
	public ResponseEntity<?> search(@ModelAttribute SearchCondition condition) {
		try {
			List<Book> books = bookService.search(condition);
			if (books != null && books.size() > 0) {
				return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Book 객체를 저장

	// Book 객체 수정

	// Book 객체 삭제

}

// documentationPluginsBootstrapper: application.properties에 추가해줘야 함 => 오류 해결 위해... => swagger 사용할 때 설정해주기 !! 
