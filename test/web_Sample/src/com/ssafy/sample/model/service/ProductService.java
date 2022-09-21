package com.ssafy.sample.model.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssafy.sample.dto.Product;
import com.ssafy.sample.dto.User;
import com.ssafy.sample.model.dao.ProductDao;

public class ProductService {
	
	private ProductDao dao = new ProductDao();

	public int regist(Product product) throws SQLException {
		int cnt = dao.insert(product);
		return cnt;
	}

	public List<Product> list() throws SQLException {
		List<Product> list = dao.selectAll();
		return list;
	}

	public Product detail(String code) throws SQLException {
		Product detail = dao.select(code);
		return detail;
	}

	public int remove(String removeCode) throws SQLException {
		int cnt = dao.delete(removeCode);
		return cnt;
	}

	public User login(HttpServletRequest req, String id, String pass) {
		// id와 pass 비교 
		if("ssafy".equals(id) && "1234".equals(pass)) {
			User user = new User(id, pass);
			
			// 로그인 정보를 세션에 저장
			HttpSession session = req.getSession();
			
			session.setAttribute("user", user);
			
			return user;
		}
		return null;
	}

	public boolean logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		// session.invalidate(); // -> "세션 무효화"시킴 -> 아예 접근하지 못하게 막아버림 
		session.removeAttribute("user"); // user만 제거하기!
		
		if(session.getAttribute("user") == null)// 잘 지워짐
			return true;

		return false;
	}

}
