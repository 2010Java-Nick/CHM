package CHM.dao;

import java.util.List;

import org.hibernate.HibernateException;

import CHM.model.User;

public interface UserDao {

	public int insertUser(User user) throws HibernateException;
	
	public User selectUser(int userId) throws HibernateException;
	
	public User selectUser(String username) throws HibernateException;

	public List<User> selectAllUsers() throws HibernateException;

	public User updateUser(User user) throws HibernateException;
	
	public boolean deleteUser(User user) throws Exception;

	
}