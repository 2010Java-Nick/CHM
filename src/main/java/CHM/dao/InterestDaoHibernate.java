package CHM.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import CHM.model.Interest;

/**
 * @author Group 3
 *
 */
@Repository(value = "interestDao")
public class InterestDaoHibernate implements InterestDao {
	
	SessionFactory sessionFactory;

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int insertInterest(Interest interest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Interest selectInterest(int interestInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interest> selectAllInterests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interest updateInterest(int interestId, Interest interest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteInterest(Interest interest) {
		// TODO Auto-generated method stub
		return false;
	}


}
