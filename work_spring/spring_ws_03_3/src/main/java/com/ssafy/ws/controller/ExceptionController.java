package com.ssafy.ws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	public String handleExceptoin(Exception ex, Model model) {
		logger.error("예외 발생 : {}", ex.getMessage());
		
		if(ex instanceof BindException) {
			model.addAttribute("msg", "파라미터가 잘 전달되었는지 확인하세요.");
		} else {
			model.addAttribute("msg", "처리 중 에러 발생!!");
		}
		return "/error/commonerr";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex, Model model) {
		logger.error("404 발생 : {}", "404 page not found");
		model.addAttribute("msg", "요청하신 파일은 존재하지 않습니다.");
		return "/error/404";
	}
}
