package CHM.dao;

import java.util.List;

import CHM.model.Message;

public interface MessageDao {
	
	public int insertMessage(Message message);
	
	public Message selectMessage(int messageId);
	
	public List<Message> selectAllMessages();
	
	public Message updateMessage(Message message);
	
	public boolean deleteMessage(Message message);

}
