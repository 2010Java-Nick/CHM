package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.MessageDao;
import CHM.model.Message;
import CHM.util.InvalidMessageException;
import static CHM.util.HelperFunctions.isValidMessage;

@Service
public class MessageServiceImpl implements MessageService {

	private MessageDao messageDao;
	
	@Autowired
	@Override
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;

	}
	
	@Override
	public int createMessage(Message message) throws InvalidMessageException {
		
		if (isValidMessage(message)) {
			try {
				return messageDao.insertMessage(message);
			} catch (HibernateException e) {
				return -1;
			} 
		}
		throw new InvalidMessageException("Attemping to create invalid message.");
	}

	@Override
	public Message readMessageById(int messageId) {
		try {
			return messageDao.selectMessage(messageId);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public List<Message> readAllMessages() {
		try {
			return messageDao.selectAllMessages();
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public Message updateMessage(Message message) throws InvalidMessageException {
		if (isValidMessage(message)) {
			try {
				return messageDao.updateMessage(message);
			} catch (HibernateException e) {
				return null;
			} 
		}
		throw new InvalidMessageException("Attempting to create invalid message.");
	}

	@Override
	public boolean deleteMessage(Message message) {
		try {
			return messageDao.deleteMessage(message);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Message> readMessagesByMatchId(int matchId) {
		try {
			return messageDao.selectMessagesByMatchId(matchId);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public List<Message> readMessagesByRecipientId(int recipientId) {
		try {
			return messageDao.selectMessagesByRecipientId(recipientId);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public List<Message> readMessagesBySenderId(int senderId) {
		try {
			return messageDao.selectMessagesBySenderId(senderId);
		} catch (HibernateException e) {
			return null;
		}
	}
}
