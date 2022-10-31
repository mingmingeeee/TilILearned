package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
	
	@GetMapping("/resthello")
	public String hello() {
		return "Hello Rest Controller!!!"; // data 그 자체 
	}

}
