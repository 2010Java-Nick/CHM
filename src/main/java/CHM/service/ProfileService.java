package CHM.service;

import java.util.List;

import CHM.model.Profile;

public interface ProfileService {
	
	public Profile createProfile(Profile profile);
	
	public Profile readProfileById(int profileId);
	
	public List<Profile> readAllProfiles();
	
	public Profile updateProfile(int profileId, Profile profile);
	
	public boolean deleteProfile(Profile profile);
}
