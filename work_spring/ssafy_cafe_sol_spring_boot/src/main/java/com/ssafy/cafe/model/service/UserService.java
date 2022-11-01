package com.ssafy.cafe.model.service;

import java.util.List;

import com.ssafy.cafe.model.dto.User;

public interface UserService {

	void removeAll();

	int getCount();

	int addUser(User user);

	int modifyUser(User user);

	int removeUser(String userId);

	User getUser(String userId);

	List<User> getUsers();

	User login(User user);
	
}
