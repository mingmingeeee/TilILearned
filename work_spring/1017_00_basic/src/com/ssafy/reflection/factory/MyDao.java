package com.ssafy.reflection.factory;

import java.util.ArrayList;
import java.util.List;

public class MyDao implements UserDao {
	
	List<User> users = new ArrayList<>();

	@Override
	public void insert(User user) {
		System.out.println("MyDao insert 호출");
		users.add(user);
	}

	@Override
	public User select(int id) {
		System.out.println("MyDao select 호출");
		for (User u : users) {
			if (u != null && u.getId() == id) {
				return u;
			}
		}
		
		return null;
	}

}
