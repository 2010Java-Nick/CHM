package CHM.dao;

import java.util.List;

import CHM.model.Interest;

public interface InterestDao{
	
	public int insertProfile(Interest interest);
	
	public Interest selectProfile(int interestInt);
	
	public List<Interest> selectAllProfiles();
	
	public Interest updateUser(int interestId, Interest interest);
	
	public boolean deleteProfile(Interest interest);

}
