package com.ssafy.cafe.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.cafe.model.dto.User;

public interface UserService {
	
	void removeAll() throws SQLException;
	
	int getCount() throws SQLException;

	int addUser(User user) throws SQLException;

	int modifyUser(User user) throws SQLException;

	int removeUser(String userId) throws SQLException;

	User getUser(String userId) throws SQLException;

	List<User> getUsers() throws SQLException;

	User login(User user);

}
