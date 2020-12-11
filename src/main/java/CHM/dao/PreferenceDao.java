package CHM.dao;

import java.util.List;

public interface PreferenceDao {
	
public int insertProfile(Preference preference);
	
	public Preference selectProfile(int preferenceInt);
	
	public List<Preference> selectAllProfiles();
	
	public Preference updateUser(int preferenceId, Preference preference);
	
	public boolean deleteProfile(Preference preference);

}
