package com.ssafy.ws.controller;

import java.io.File;
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

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;

@Controller
public class BookController {
	
	// 경로
	@Autowired
	private ResourceLoader resourceLoader; // 파일의 이름, 경로 가져올 수 있도록 도와줌
	
	// 중복 파일을 방지하기 위해 img에 prefix를 추가한 이름
	private String img;
	
	// 클라이언트가 최초 업로드한 파일 이름
	private String orgimg;
	
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
	public String doRegist(@ModelAttribute Book book/* , Model model */, @RequestParam(value="upfile") MultipartFile file, Model model) throws Exception {
		
		// 클라이언트로 부터 전달받은 파일이 존재하면 아래와 같이 처리 => null이 아니고 0 이상
		if(file != null && file.getSize() > 0) {
			
			// 파일을 저장할 폴더 지정 
			// C:\SSAFY\bug_8th_4_minjeong\work_spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_ws_05_3 아래 생성됨
			Resource resource = resourceLoader.getResource("resources/upload");
			
			// 서버에 저장할 파일 이름을 생성
			String img = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			
			// 실제 파일 이름 (사용자가 올린 파일 이름)
			String orgImg = file.getOriginalFilename();
			
			book.setImg(img);
			book.setOrgImg(orgImg);
			
			// getFile: file 객체 획득
			// getCanonicalFile(): 파일 객체의 canonical path 얻어옴
			// canonical path란?: 유일한 경로 
			file.transferTo(new File(resource.getFile().getCanonicalFile() + "/" + book.getImg()));
			
		}

		
		return "regist_result";
	}
}







