package CHM.service;

import java.util.List;

import CHM.dao.MessageDao;
import CHM.model.Message;

public interface MessageService {

	public int createMessage(Message message);
	
	public Message readMessageById(int messageId);
	
	public List<Message> readAllMessages();
	
	public Message updateMessage(Message message);
	
	public boolean deleteMessage(Message message);
	
	public void setMessageDao(MessageDao messageDao);
}
