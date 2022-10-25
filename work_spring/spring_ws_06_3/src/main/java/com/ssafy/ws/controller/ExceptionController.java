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
	public String handleException(Exception e, Model model) {
		logger.error("예외 처리", e.getCause());
		e.printStackTrace();
		
		if (e instanceof BindException) {
			model.addAttribute("errmsg", "파라메터가 잘 전달되었는지 확인하세요.");
		}
		
		// 예외가 발생되면 /error/commonerr를 호출한다.
		return "/error/commonerr";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex, Model model) {
		model.addAttribute("errmsg", "해당 페이지를 찾을 수 없습니다!!!");
		return "/error/commonerr";
	}

}
