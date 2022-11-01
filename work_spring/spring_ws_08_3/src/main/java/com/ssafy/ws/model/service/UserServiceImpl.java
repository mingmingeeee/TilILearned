package com.ssafy.ws.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.UserDao;
import com.ssafy.ws.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao repo;
	
	@Autowired
	public UserServiceImpl(UserDao repo) {
		this.repo = repo;
	}

	@Override
	public User select(String id) {
		// TODO Auto-generated method stub
		return repo.select(id);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		repo.deleteAll();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return repo.getCount();
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return repo.insert(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return repo.login(user);
	}

}
