package com.ssafy.cafe.model.mapper;

import java.util.List;

import com.ssafy.cafe.model.dto.User;

public interface UserDao {
	
	void deleteAll();
	
	int getCount();

	int insert(User user);

	int update(User user);

	int delete(String userId);

	User select(String userId);

	List<User> selectAll();

	User login(User user);

}
