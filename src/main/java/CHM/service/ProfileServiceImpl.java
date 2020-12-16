package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.ProfileDaoHibernate;
import CHM.model.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	ProfileDaoHibernate profileDao;

	/**
	 * @param profileDao the profileDao to set
	 */
	@Autowired
	public void setProfileDao(ProfileDaoHibernate profileDao) {
		this.profileDao = profileDao;
	}

	@Override
	public Profile createProfile(Profile profile) {
		try {
			profileDao.insertProfile(profile);
			return profile;
			
		} catch (HibernateException e) {
			return null;
		}
		
	}

	@Override
	public Profile readProfileById(int profileId) {
		
		
		return null;
	}

	@Override
	public List<Profile> readAllProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile updateProfile(int profileId, Profile profile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProfile(Profile profile) {
		// TODO Auto-generated method stub
		return false;
	}

}
