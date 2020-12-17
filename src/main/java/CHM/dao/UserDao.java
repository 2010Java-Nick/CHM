package CHM.dao;

import java.util.List;

import CHM.model.User;

public interface UserDao {

	public int insertUser(User user);
	
//	public User selectUser(int userId);
	
	public User selectUser(String username);
	
	public List<User> selectAllUsers();
	
	public User updateUser(int userId, User user);
	
	public boolean deleteUser(User user);
	
}