/**
 * 
 */
package CHM.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	public int insertProfile(Profile profile) throws HibernateException {

		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(profile);
		System.out.println(profile.toString());
		tx.commit();
		sess.close();
		return profile.getProfileId();
	}

	@Override
	public Profile selectProfile(int profileId) throws HibernateException{
		
		Profile profile;
		Session sess = sessionFactory.openSession();
		profile = sess.get(Profile.class, profileId);
		sess.close();
		return profile;
	}

	@Override
	public List<Profile> selectAllProfiles() throws HibernateException {
		
		List<Profile> profileList = null;
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
		Root<Profile> rootEntry = cq.from(Profile.class);
		CriteriaQuery<Profile> all = cq.select(rootEntry);
		TypedQuery<Profile> allQuery = sess.createQuery(all);
		profileList = allQuery.getResultList();
		sess.close();
		
		return profileList;
	}

	@Override
	public Profile updateProfile(Profile profile) throws HibernateException {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.update(profile);
		tx.commit();
		sess.close();
		return profile;
	}

	@Override
	public boolean deleteProfile(int profileId) throws Exception {
		
		Profile profile = new Profile();
		profile.setProfileId(profileId);
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(profile);
		tx.commit();
		sess.close();
		return true;
	}

}
