package CHM.service;

import java.util.List;

import CHM.dao.ProfileDao;
import CHM.model.Profile;
import CHM.util.InvalidProfileException;

public interface ProfileService {
	
	public int createProfile(Profile profile) throws InvalidProfileException;
	
	public Profile readProfileById(int profileId);
	
	public List<Profile> readAllProfiles();
	
	public Profile updateProfile(Profile profile) throws InvalidProfileException;
	
	public boolean deleteProfile(int profileId);
	
	public void setProfileDao(ProfileDao profileDao);
}
