package com.ssafy.ws.model.service;

import com.ssafy.ws.model.dto.User;

public interface UserService {
	
	User select(String id);

	void deleteAll();

	int getCount();

	int insert(User user);

	User login(User user);
	
}
