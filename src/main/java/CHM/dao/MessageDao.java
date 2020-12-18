package CHM.dao;

import java.util.List;

import org.hibernate.HibernateException;

import CHM.model.Message;

public interface MessageDao {
	
	public int insertMessage(Message message) throws HibernateException;
	
	public Message selectMessage(int messageId) throws HibernateException;
	
	public List<Message> selectAllMessages() throws HibernateException;
	
	public Message updateMessage(Message message) throws HibernateException;
	
	public boolean deleteMessage(Message message) throws Exception;

}
