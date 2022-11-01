package com.ssafy.cafe.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.cafe.model.dto.User;

public interface UserDao {
	
	void deleteAll() throws SQLException;
	
	int getCount() throws SQLException;

	int insert(User user) throws SQLException;

	int update(User user) throws SQLException;

	int delete(String userId) throws SQLException;

	User select(String userId) throws SQLException;

	List<User> selectAll() throws SQLException;

	User login(User user);

	

}
