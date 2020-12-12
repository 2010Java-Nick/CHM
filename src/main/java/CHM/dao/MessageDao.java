package CHM.dao;

import java.util.List;

import CHM.model.Message;

public interface MessageDao {
	
	public int insertProfile(Message message);
	
	public Message selectProfile(int messageInt);
	
	public List<Message> selectAllProfiles();
	
	public Message updateUser(int messageId, Message message);
	
	public boolean deleteProfile(Message message);

}
