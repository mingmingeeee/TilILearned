package com.ssafy.cafe.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.mapper.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl (UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getUsers() throws SQLException {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	@Override
	public void removeAll() throws SQLException {
		// TODO Auto-generated method stub
		userDao.deleteAll();
	}

	@Override
	public int getCount() throws SQLException {
		// TODO Auto-generated method stub
		return userDao.getCount();
	}

	@Override
	public int addUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

	@Override
	public int modifyUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	@Override
	public int removeUser(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.delete(userId);
	}

	@Override
	public User getUser(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.select(userId);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

}
