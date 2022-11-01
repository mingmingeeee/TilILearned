package com.ssafy.cafe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserRestController {

	private UserService userService;

	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {

		try {
			User selected = userService.login(user);

			if (selected != null) {
				session.setAttribute("loginUser", selected);
				return new ResponseEntity<User>(selected, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		try {
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
