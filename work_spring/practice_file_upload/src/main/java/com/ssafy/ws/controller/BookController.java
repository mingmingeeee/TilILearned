package com.ssafy.ws.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;

@Controller
public class BookController {

	@Autowired
	private ServletContext servletContext;

	@GetMapping({ "/", "/index" })
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
		} else {
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
		books.add(new Book("111-222-3333", "홍길동", "책제목1", 10000, "좋은 책 1", "abc1.png", null));
		books.add(new Book("111-222-4444", "임꺽정", "책제목2", 20000, "좋은 책 2", "abc2.png", null));
		books.add(new Book("111-333-4444", "장길산", "책제목3", 30000, "좋은 책 3", "abc3.png", null));

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
	public String doRegist(@ModelAttribute Book book/* , Model model */,
			@RequestParam("file") MultipartFile file, Model model) throws IllegalStateException, IOException {

		if (file != null && file.getSize() > 0) {
			String path = servletContext.getRealPath("/resources/upload");
			String orgimg = file.getOriginalFilename();
			String img = System.currentTimeMillis() + "_" + orgimg;

			File folder = new File(path);

			if (!folder.exists()) {
				folder.mkdirs();
			}

			book.setImg(img);
			book.setOrgimg(orgimg);

			file.transferTo(new File(path, img));

			// model.addAttribute("book", book);

		}
		return "regist_result";
	}
}
