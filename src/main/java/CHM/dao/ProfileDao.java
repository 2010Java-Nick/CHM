package CHM.dao;

import java.util.List;

import CHM.model.Profile;

public interface ProfileDao {
	
	public int insertProfile(Profile profile);
	
	public Profile selectProfile(int profileInt);
	
	public List<Profile> selectAllProfiles();
	
	public Profile updateUser(int profileId, Profile profile);
	
	public boolean deleteProfile(Profile profile);

}
