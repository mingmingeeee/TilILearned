package com.ssafy.ws.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.service.BookService;

@Controller
@RequestMapping("/")
public class BookController {

	private final Logger logger = LoggerFactory.getLogger(BookController.class);

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping({"/", "/index"})
	public String index() {
//		int result = 1 / 0;
		logger.debug("index 화면 출력");
		return "/index";
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, HttpSession session, Model model) {

		if ("ssafy".equals(map.get("id")) && "1234".equals(map.get("pass"))) {
			User user = new User();
			user.setId(map.get("id"));
			user.setName("김싸피");
			user.setPass(map.get("pass"));
			
			session.setAttribute("userinfo", user);
			
			return "redirect:/";
		}
		else {
			model.addAttribute("msg", "로그인 실패");
			return "/index";
		}

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Book> list = bookService.search();
		model.addAttribute("list", list);
		
		return "/list";
	}
	
	@GetMapping("/regist")
	public String regist() {
		return "/regist";
	}
	
	@PostMapping("/regist")
	public String regist(Book book, Model model) {
		logger.debug("book : {}" + book.toString());
		
		bookService.insert(book);
		
		model.addAttribute("book", book);
		return "/regist_result";
	}
	
	@GetMapping("/regist_result")
	public String regist_result() {
		return null;
	}
	

}
