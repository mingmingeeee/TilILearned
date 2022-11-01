package com.ssafy.cafe.controller;

import java.util.List;
import java.util.Map;

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

import com.ssafy.cafe.model.dto.Order;
import com.ssafy.cafe.model.dto.Product;
import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.service.OrderService;
import com.ssafy.cafe.model.service.ProductService;
import com.ssafy.cafe.model.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminRestController {

	private UserService userService;
	private ProductService productService;
	private OrderService orderService;

	@Autowired
	public AdminRestController(UserService userService, ProductService productService, OrderService orderService) {
		this.userService = userService;
		this.productService = productService;
		this.orderService = orderService;
	}

	@GetMapping("/user")
	public ResponseEntity<?> userList() {
		List<User> users = userService.getUsers();

		try {
			if (users != null && !users.isEmpty()) {
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<?> userRegister(@RequestBody User user) {
		userService.addUser(user);

		// 새로운 회원목록 받아오기
		List<User> users = userService.getUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<?> userInfo(@PathVariable("userid") String userId) {

		try {
			User user = userService.getUser(userId);
			if (user != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user/{userid}")
	public ResponseEntity<?> userModify(@RequestBody User user, @PathVariable("userid") String userId) {
		try {
			user.setId(userId);
			userService.modifyUser(user);

			// 새로운 회원목록 받아오기
			List<User> users = userService.getUsers();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user/{userid}")
	public ResponseEntity<?> userRemove(@PathVariable("userid") String userId) {

		try {
			userService.removeUser(userId);

			// 새로운 회원목록 받아오기
			List<User> users = userService.getUsers();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/product")
	public ResponseEntity<?> listProduct() {
		List<Product> products = productService.getProducts();

		try {
			if (products != null && !products.isEmpty()) {
				return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//	@PostMapping("/product")
	//	public ResponseEntity<?> productRegister(@RequestBody Product product) {
	//		productService.addProduct(product);
	//
	//		// 새로운 회원목록 받아오기
	//		List<Product> products = productService.getProducts();
	//		return new ResponseEntity<List<Product>>(products, HttpStatus.CREATED);
	//	}

	@PostMapping("/product")
	public ResponseEntity<?> registerProduct(Product product) {

		try {
			productService.addProduct(product);

			// 새로운 회원목록 받아오기
			List<Product> products = productService.getProducts();
			return new ResponseEntity<List<Product>>(products, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/product/{productid}")
	public ResponseEntity<?> getProduct(@PathVariable("productid") Integer productId) {

		try {
			Product product = productService.getProduct(productId);
			if (product != null) {
				return new ResponseEntity<Product>(product, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/product/{productid}")
	public ResponseEntity<?> modifyProduct(@RequestBody Product product, @PathVariable("productid") Integer productId) {
		try {
			product.setId(productId);
			productService.modifyProduct(product);

			// 새로운 회원목록 받아오기
			List<Product> products = productService.getProducts();
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/product/{productid}/upfile")
	public ResponseEntity<?> modifyProductFile(Product product, @PathVariable("productid") Integer productId) {
		try {
			product.setId(productId);
			productService.modifyProductFile(product);

			// 새로운 회원목록 받아오기
			List<Product> products = productService.getProducts();
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/product/{productid}")
	public ResponseEntity<?> removeProduct(@PathVariable("productid") Integer productId) {

		try {
			productService.removeProduct(productId);

			// 새로운 회원목록 받아오기
			List<Product> products = productService.getProducts();
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/order")
	public ResponseEntity<?> getOrders() {

		try {
			List<Map<String, Object>> orders = orderService.getOrders();
			if (orders != null && !orders.isEmpty()) {
				return new ResponseEntity<List<Map<String, Object>>>(orders, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/order/{orderid}")
	public ResponseEntity<?> modifyOrder(@RequestBody Order order, @PathVariable("orderid") Integer orderId) {
		try {
			order.setId(orderId);
			orderService.modifyOrder(order);

			// 새로운 회원목록 받아오기
			List<Map<String, Object>> orders = orderService.getOrders();
			return new ResponseEntity<List<Map<String, Object>>>(orders, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/order/{orderid}")
	public ResponseEntity<?> removeOrder(@PathVariable("orderid") Integer orderId) {

		try {
			orderService.removeOrder(orderId);

			// 새로운 회원목록 받아오기
			List<Map<String, Object>> orders = orderService.getOrders();
			return new ResponseEntity<List<Map<String, Object>>>(orders, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
