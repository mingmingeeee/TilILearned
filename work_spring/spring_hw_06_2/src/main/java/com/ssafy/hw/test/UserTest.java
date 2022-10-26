package com.ssafy.hw.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ssafy.hw.model.dao.UserDao;
import com.ssafy.hw.model.dto.User;

public class UserTest {
	public static void main(String[] args) {

		try {
			ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

			UserDao userDao = context.getBean(UserDao.class);
			
			for(User user : userDao.selectAll()) {
				System.out.println(user.toString());
			}
			
			System.out.println(userDao.insert(new User("minjeong", "1234", "민정", " minjeong@gmail.com", 25)));
			
			System.out.println(userDao.searchById("minjeong"));
			
			System.out.println(userDao.searchByName("김싸피"));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
