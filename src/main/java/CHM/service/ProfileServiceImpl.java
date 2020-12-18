package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.ProfileDao;
import CHM.model.Profile;
import static CHM.util.HelperFunctions.validateProfile;
import CHM.util.InvalidProfileException;

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
	public int createProfile(Profile profile) throws InvalidProfileException {
    
		if (validateProfile(profile)) {
			try {
				return profileDao.insertProfile(profile);
				
			} catch (HibernateException e) {
				return -1; 
			}
		}
		throw new InvalidProfileException("Attempting to create invalid profile.");		
		
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
	public Profile updateProfile(Profile profile) throws InvalidProfileException {
		
		if (validateProfile(profile)) {
			try {
				return profileDao.updateProfile(profile);
				
			} catch (HibernateException e) {
				return null;
			}
		}
		
		throw new InvalidProfileException("Attempting to update profile with invalid data.");
	}

	@Override
	public boolean deleteProfile(Profile profile) {
		
		try {
			return profileDao.deleteProfile(profile);
		} catch (Exception e) {
			return false;
		}
	}

}
