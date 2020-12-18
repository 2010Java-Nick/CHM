package CHM.service;

import java.util.List;

import CHM.dao.MessageDao;
import CHM.model.Message;

public interface MessageService {

	public int createMessage(Message message);
	
	public Message readMessageById(int messageId);
	
	public List<Message> readAllMessages();
	
	public List<Message> readMessagesByMatchId(int matchId);
	
	public List<Message> readMessagesByRecipientId(int recipientId);
	
	public List<Message> readMessagesBySenderId(int senderId);
	
	public Message updateMessage(Message message);
	
	public boolean deleteMessage(Message message);
	
	public void setMessageDao(MessageDao messageDao);
}
