package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.InterestDao;
import CHM.model.Interest;

@Service
public class InterestServiceImpl implements InterestService {
	
	InterestDao interestDao;

	/**
	 * @param interestDao the interestDao to set
	 */
	@Autowired
	public void setInterestDao(InterestDao interestDao) {
		this.interestDao = interestDao;
	}
	
	@Override
	public int createInterest(Interest interest) {
		
		try {
			return interestDao.insertInterest(interest);
		} catch (HibernateException e) {
			return -1;
		}

	}

	@Override
	public Interest readInterestById(int interestId) {
		
		try {
			return interestDao.selectInterest(interestId);
		} catch (HibernateException e) {
			return null;
		}
		
	}

	@Override
	public List<Interest> readAllInterests() {
		
		try {
			return interestDao.selectAllInterests();
		} catch (HibernateException e) {
			return null;
		}
		
	}

	@Override
	public Interest updateInterest(Interest interest) {

		try {
			return interestDao.updateInterest(interest);
		} catch (HibernateException e) {
			return null;
		}
		
	}

	@Override
	public boolean deleteInterest(Interest interest) {
		
		try {
			return interestDao.deleteInterest(interest);
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public List<Interest> readInterestsByProfileId(int profileId) {
		try {
			if (interestDao == null) {
				System.out.println("null dao");
			}
			return interestDao.selectInterestsByProfileId(profileId);
		} catch (HibernateException e) {
			return null;
		}
	}

}
