package CHM.service;

import java.util.List;

import CHM.dao.InterestDao;
import CHM.model.Interest;

public interface InterestService {
	
	public void setInterestDao(InterestDao interestDao);
	
	public int createInterest(Interest interest);
	
	public Interest readInterestById(int interestId);
	
	public List<Interest> readAllInterests();
	
	public List<Interest> readInterestsByProfileId(int profileId);
	
	public Interest updateInterest(Interest interest);
	
	public boolean deleteInterest(Interest interest);

}