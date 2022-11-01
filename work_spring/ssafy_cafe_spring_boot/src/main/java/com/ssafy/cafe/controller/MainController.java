package com.ssafy.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	// 바닥 페이지 => 실행시키면 바로 index로 가게끔
	@GetMapping("/")
	public String index() {
		return "redirect:/assets/index.html";
	}
	
}
