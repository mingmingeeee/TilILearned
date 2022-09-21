package com.ssafy.sample.model.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssafy.sample.dto.Person;
import com.ssafy.sample.dto.User;
import com.ssafy.sample.model.dao.PersonDao;

public class PersonService {
	
	PersonDao dao = new PersonDao();

	public int regist(Person person) throws SQLException {
		int cnt = dao.regist(person);
		return cnt;
	}

	public List<Person> selectAll() throws SQLException {
		List<Person> list = dao.selectAll();
		return list;
	}

	public Person select(String id) throws SQLException {
		Person person = dao.select(id);
		return person;
	}

	public int delete(String id) throws SQLException {
		int cnt = dao.delete(id);
		return cnt;
	}

	public User login(HttpServletRequest req, String userid, String userpass) {
		
		
		if("ssafy".equals(userid) && "1234".equals(userpass)) {
			
			HttpSession session = req.getSession();		
			User user = new User(userid, userpass);
			
			session.setAttribute("user", user);
			return user;
		}
		
		return null;
		
	}

	public boolean logout(HttpServletRequest req) {

		HttpSession session = req.getSession();
		
		session.removeAttribute("user");
		
		if(session.getAttribute("user") == null)
			return true;
		

		return false;
	}

}
