package com.ssafy.ws.model.dao;

import com.ssafy.ws.model.dto.User;

public interface UserDao {
	
	User select(String id);

	void deleteAll();

	int getCount();

	int insert(User user);

	User login(User user);
	
}
