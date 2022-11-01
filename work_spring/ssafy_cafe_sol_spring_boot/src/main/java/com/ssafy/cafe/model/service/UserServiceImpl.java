package com.ssafy.cafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.mapper.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void removeAll() {
		userDao.deleteAll();
	}

	@Override
	public int getCount() {
		return userDao.getCount();
	}

	@Override
	public int addUser(User user) {
		return userDao.insert(user);
	}

	@Override
	public int modifyUser(User user) {
		return userDao.update(user);
	}

	@Override
	public int removeUser(String userId) {
		return userDao.delete(userId);
	}

	@Override
	public User getUser(String userId) {
		return userDao.select(userId);
	}

	@Override
	public List<User> getUsers() {
		return userDao.selectAll();
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

}
