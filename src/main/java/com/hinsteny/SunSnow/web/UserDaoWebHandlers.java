package com.hinsteny.SunSnow.web;

import java.util.List;

import javax.inject.Inject;

import com.britesnow.snow.web.handler.annotation.WebActionHandler;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.google.inject.Singleton;
import com.hinsteny.SunSnow.dao.UserDao;
import com.hinsteny.SunSnow.entity.User;

@Singleton
public class UserDaoWebHandlers {

	@Inject
	private UserDao userDao;

	@WebActionHandler
	public User daoUserGet(@WebParam("userId") Long id) {
		User user = userDao.get(id);
		return user;
	}

	@WebActionHandler
	public List<User> daoUserList() {
		List<User> users = userDao.list();
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
		User user = userDao.update(userId, userName, password, sex);
		return user;
	}

	@WebActionHandler
	public User daoUserDelete(@WebParam("userId") Long userId) {
		User user = userDao.delete(userId);
		return user;
	}

}
