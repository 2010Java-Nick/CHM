package CHM.service;

import java.util.List;

import CHM.dao.ProfileDao;
import CHM.model.Profile;

public interface ProfileService {
	
	public int createProfile(Profile profile);
	
	public Profile readProfileById(int profileId);
	
	public List<Profile> readAllProfiles();
	
	public Profile updateProfile(Profile profile);
	
	public boolean deleteProfile(Profile profile);
	
	public void setProfileDao(ProfileDao profileDao);
}
