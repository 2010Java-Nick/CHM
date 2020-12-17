package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.ProfileDao;
import CHM.model.Profile;
import static CHM.util.HelperFunctions.validateProfile;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	private ProfileDao profileDao;

	/**
	 * @param profileDao the profileDao to set
	 */
	@Autowired
	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}

	@Override
	public int createProfile(Profile profile) {
		
		if (validateProfile(profile)) {
			try {
				return profileDao.insertProfile(profile);
				
			} catch (HibernateException e) {
				return -1;
			}
		}
		
		return -1;
	}

	@Override
	public Profile readProfileById(int profileId) {
		
		
		try {
			return profileDao.selectProfile(profileId);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public List<Profile> readAllProfiles() {
		
		try {
			return profileDao.selectAllProfiles();
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public Profile updateProfile(Profile profile) {
		
		if (validateProfile(profile)) {
			try {
				return profileDao.updateProfile(profile);
				
			} catch (HibernateException e) {
				return null;
			}
		}
		
		return null;
	}

	@Override
	public boolean deleteProfile(Profile profile) {
		
		try {
			return profileDao.deleteProfile(profile);
		} catch (HibernateException e) {
			return false;
		}
	}

}
