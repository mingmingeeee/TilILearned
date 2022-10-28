package com.ssafy.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cafe.model.Product;
import com.ssafy.cafe.model.User;
import com.ssafy.cafe.model.service.ProductService;
import com.ssafy.cafe.model.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*") // 어떤 ip든 상관 없이 이 서버에 접속을 허용하겠다!
public class AdminRestController {

	private UserService userService;
	private ProductService productService;

	@Autowired
	public AdminRestController(UserService userService, ProductService productService) {
		this.userService = userService;
		this.productService = productService;
	}

	@GetMapping("/user")
	public ResponseEntity<?> userList() {

		try {
			List<User> users = userService.getUsers();
			if (users != null && !users.isEmpty()) {
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("Error: userList()", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/user")
	public ResponseEntity<?> userRegister(@RequestBody User user) {

		try {
			userService.addUser(user);
			// 추가된 사용자 포함한 새로운 회원 목록 받아오기
			List<User> users = userService.getUsers();
			if (users != null && !users.isEmpty()) {
				return new ResponseEntity<List<User>>(users, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("Error: userList()", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// ResponseEntity<?> userList = userList();
		// return userList;

		// ControllerAdvice ~~
	}

	// 하나 하나를 API라고 부름
	@GetMapping("/user/{userid}")
	public ResponseEntity<?> userInfo(@PathVariable("userid") String userId) {

		try {
			User user = userService.getUser(userId);

			if (user != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: userInfo()", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// /user/{userid}해주는 게 더 맞는데(?) Dto로 받아서 처리하면 편하니까 유튜브 라이브 예제에서는 Dto로 했었음
	@PutMapping("/user/{userid}")
	public ResponseEntity<?> userModify(@RequestBody User user, @PathVariable("userid") String userId) {
		try {
			userService.modifyUser(user);
			// 새로운 회원 목록 받아오기
			List<User> users = userService.getUsers();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: userModify()", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user/{userid}")
	public ResponseEntity<?> userRemove(@PathVariable("userid") String userId) {

		try {
			userService.removeUser(userId);

			// 새로운 회원 목록 받아오기
			List<User> users = userService.getUsers();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: userRemove()", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product")
	public ResponseEntity<?> productList() {
		try {
			List<Product> products = productService.getProducts();
			
			if(products!=null && !products.isEmpty()) {
				return new ResponseEntity<List<Product>> (products, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
			}
		} catch(Exception e) {
			return new ResponseEntity<String> ("Error: productList()", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product/{productid}")
	public ResponseEntity<?> productView(@PathVariable("productid") Integer productId) {
		try {
			Product product = productService.getProduct(productId);
			
			return new ResponseEntity<Product> (product, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String> ("Error: productList()", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
