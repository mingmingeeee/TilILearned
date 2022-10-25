package com.ssafy.ws.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.User;

@Controller
public class BookController {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping({"/", "/index"})
	public String showRoot(@RequestParam(required = false) String msg, Model model) {
		
		if (msg != null) {
			model.addAttribute("msg", msg);
		}
		
		return "index";
	}
	
	@PostMapping("/login")
	public String doLogin(User user, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		
		if (user.getId().equals("ssafy") && user.getPass().equals("1234")) {
			user.setName("김싸피");
			session.setAttribute("loginUser", user);
			return "redirect:/";
		}
		else {
			redirectAttributes.addAttribute("msg", "로그인 실패");
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@GetMapping("/list")
	public String showList(Model model) {
		List<Book> books = new ArrayList<>();
		books.add(new Book("111-222-3333", "홍길동", "책제목1", 10000, "좋은 책 1", null, "abc1.png"));
		books.add(new Book("111-222-4444", "임꺽정", "책제목2", 20000, "좋은 책 2", null, "abc2.png"));
		books.add(new Book("111-333-4444", "장길산", "책제목3", 30000, "좋은 책 3", null, "abc3.png"));
		
		model.addAttribute("books", books);
		return "list";
	}
	
	@GetMapping("/regist")
	public String showRegistForm() {
		return "regist";
	}
	
	// 현재 클라이언트에서는 book이라는 이름으로 보내는 데이터가 없기 때문에
	// Book DTO 파라메터 앞에는 @RequestParam을 붙이면 안된다.
	@PostMapping("/regist")
	public String doRegist(@ModelAttribute Book book/* , Model model */, @RequestParam(value = "upfile") MultipartFile file) throws IllegalStateException, IOException {
		
		//model.addAttribute("book", book);
		
		// 클라이언트로 부터 전달받은 파일이 존재하면 아래와 같이 처리
		if (file != null && file.getSize() > 0) {
			
			// 파일을 저장할 폴더 지정
			Resource resource = resourceLoader.getResource("resources/upload");
			
			// 서버에 저장할 파일 이름을 생성
			String img = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			
			// 실제 파일이름(사용자가 올린 파일이름)
			String orgImg = file.getOriginalFilename();
			
			book.setImg(img);
			book.setOrgImg(orgImg);
			
			file.transferTo(new File(resource.getFile().getCanonicalPath() + "/" + book.getImg()));
		}
		
		
		return "regist_result";
	}
}







