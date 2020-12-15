package CHM.dao;

import java.util.List;

import CHM.model.Interest;

public interface InterestDao{
	
	public int insertInterest(Interest interest);
	
	public Interest selectInterest(int interestInt);
	
	public List<Interest> selectAllInterests();
	
	public Interest updateInterest(int interestId, Interest interest);
	
	public boolean deleteInterest(Interest interest);

}
