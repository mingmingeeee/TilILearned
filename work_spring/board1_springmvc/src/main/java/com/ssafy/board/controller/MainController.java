package com.ssafy.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller  // 빈으로 생성하여 스프링 컨테이너에 등록하기 위해 (HandlerMapping에서 활용함)
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
