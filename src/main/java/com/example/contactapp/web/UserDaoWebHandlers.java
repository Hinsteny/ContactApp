package com.example.contactapp.web;

import java.util.List;
import javax.inject.Inject;
import com.britesnow.snow.web.handler.annotation.WebActionHandler;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.example.contactapp.dao.UserDao;
import com.example.contactapp.entity.User;
import com.google.inject.Singleton;

@Singleton
public class UserDaoWebHandlers {

	@Inject
	private UserDao userDao;

	@WebActionHandler
	public User daoUserGet(@WebParam("userId") Long id) {
		System.out.println("���һ��User����userId:" + id);
		User user = userDao.get(id);
		System.out.println("�Ѿ�����Id�õ�һ��User����username:" + user.getUserName());
		return user;
	}

	@WebActionHandler
	public List<User> daoUserList() {
		System.out.println("������ȫ��User����");
		List<User> users = userDao.list();
		for(User user : users){
			System.out.println(user.getUserName());
		}
		return users;
	}

	@WebActionHandler
	public User daoUserCreate(@WebParam("userName") String userName,
			@WebParam("userPassword") String password,
			@WebParam("userSex") String sex) {
		User user = userDao.create(userName, password, sex);
		return user;
	}

	@WebActionHandler
	public User daoUserUpdate(@WebParam("userId") Long userId,
			@WebParam("userName") String userName,
			@WebParam("userPassword") String password,
			@WebParam("userSex") String sex) {

		System.out.println(":"+userId+":"+userName+":"+password+":"+sex+":");
		User user = userDao.update(userId, userName, password, sex);
		return user;
	}

	@WebActionHandler
	public User daoUserDelete(@WebParam("userId") Long userId) {
		
		System.out.println(userId);
		User user = userDao.delete(userId);
		return user;
	}

}
