package com.ssafy.reflection.factory;

public class Test {
	
	public static void main(String[] args) {
		
		UserDao dao = new OraDao();
		UserService service = new UserService(dao);
		
		User user = new User(10, 30, "김싸피");
		service.add(user);
		
		User user2 = service.get(10);
		System.out.println(user2);
	}
}
