package com.ssafy.reflection.factory;

public class UserService {

	private UserDao dao;
	
	public UserService() {}
	
	public UserService(UserDao dao) {
		this.dao = dao;
	}
	
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	// 사용자 등록
	public void add(User user) {
		dao.insert(user);
	}
	
	// 사용자 조회
	public User get(int id) {
		User user = dao.select(id);
		return user;
	}
}
