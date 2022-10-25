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
		return null;
	}

}
