package CHM.service;

import java.util.List;

import CHM.dao.UserDao;
import CHM.model.User;

public interface UserService {

	public int createUser(User user);
	
	public User readUserById(int userId);
	
	public List<User> readAllUsers();
	
	public User readUserByProfileId(int profileId);
	
	public User updateUser(User user);
	
	public boolean deleteUser(User user);
	
	public void setUserDao(UserDao userDao);
}
