package com.example.contactapp.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.example.contactapp.entity.User;
import com.google.inject.Singleton;

@Singleton
public class UserDao {

	private long idseq = 1;
	private Map<Long, User> userStore = new ConcurrentHashMap<Long, User>();
	private Comparator<User> userComparator = new Comparator<User>() {

		@Override
		public int compare(User o1, User o2) {
			// TODO Auto-generated method stub
			return (int) (o1.getId() - o2.getId());
		}

	};

	public Map<Long, User> getUserStore() {
		return userStore;
	}

	public UserDao() {
		create("Hinsteny", "123", "male");
		create("Hisoka", "456", "male");
		create("Ç³Ñ©Ï¦Ñô", "8247095", "female");
	}

	synchronized private long newId() {
		return idseq++;
	}

	public User create(String userName, String pass, String sex) {
		User user = new User();
		user.setId(newId());
		user.setUserName(userName);
		user.setUserPassword(pass);
		user.setUserSex(sex);
		userStore.put(user.getId(), user);
		return user;
	}

	public User update(Long id, String userName, String userPassword,
			String userSex) {
		// get the group ,then update groupName
		userStore.get(id).setUserName(userName);
		userStore.get(id).setUserPassword(userPassword);
		userStore.get(id).setUserSex(userSex);
		return userStore.get(id);
	}

	public User get(Long id) {
		return userStore.get(id);
	}

	public ArrayList<Long> getUserIds() {
		ArrayList<Long> ids = new ArrayList<Long>();
		List<User> userList = list();
		for (int i = 0; i < userList.size(); i++) {
			ids.add(userList.get(i).getId());
		}
		return ids;
	}

	public List<User> list() {
		List<User> userList = new ArrayList<User>(userStore.values());
		Collections.sort(userList, userComparator);
		return userList;
	}

	public User delete(Long userId) {
		return userStore.remove(userId);
	}

}
