/**
 * 
 */
package CHM.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import CHM.model.Profile;

/**
 * @author Group 3
 *
 */
@Repository(value = "profileDao")
public class ProfileDaoHibernate implements ProfileDao {
	
	
	SessionFactory sessionFactory;

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int insertProfile(Profile profile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Profile selectProfile(int profileInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profile> selectAllProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile updateUser(int profileId, Profile profile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProfile(Profile profile) {
		// TODO Auto-generated method stub
		return false;
	}

}
