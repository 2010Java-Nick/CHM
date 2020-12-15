package CHM.dao;

import java.util.List;

import org.hibernate.HibernateException;

import CHM.model.Profile;

public interface ProfileDao {
	
	public int insertProfile(Profile profile) throws HibernateException;
	
	public Profile selectProfile(int profileId) throws HibernateException;
	
	public List<Profile> selectAllProfiles() throws HibernateException;
	
	public Profile updateProfile(Profile profile) throws HibernateException;
	
	public boolean deleteProfile(Profile profile) throws HibernateException;

}
