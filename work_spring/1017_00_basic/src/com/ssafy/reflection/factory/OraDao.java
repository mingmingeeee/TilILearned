package com.ssafy.reflection.factory;

import java.util.HashMap;
import java.util.Map;

public class OraDao implements UserDao {
	
	Map<Integer, User> users = new HashMap<>();

	@Override
	public void insert(User user) {
		System.out.println("OraDao insert 호출");
		users.put(user.getId(), user);
	}

	@Override
	public User select(int id) {
		System.out.println("OraDao select 호출");
		return users.get(id);
	}

}
