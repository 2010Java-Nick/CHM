package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.UserDao;
import CHM.model.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Autowired
	@Override
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;

	}
	
	@Override
	public int createUser(User user) {
		try {
			return userDao.insertUser(user);
		} catch (HibernateException e) {
			return -1;
		}
	}

	@Override
	public User readUserById(int userId) {
		try {
			return userDao.selectUser(userId);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public List<User> readAllUsers() {
		try {
			return userDao.selectAllUsers();
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public User readUserByProfileId(int profileId) {
		// call 
		return null;
	}

	@Override
	public User updateUser(User user) {
		try {
			return userDao.updateUser(user);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			return userDao.deleteUser(user);
		} catch (Exception e) {
			return false;
		}
	}

}
