package CHM.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import CHM.model.Interest;

public interface InterestDao{
	
	public int insertInterest(Interest interest) throws HibernateException;
	
	public Interest selectInterest(int interestId) throws HibernateException;
	
	public List<Interest> selectAllInterests() throws HibernateException;
	
	public List<Interest> selectInterestsByProfileId(int profileId) throws HibernateException;
	
	public Interest updateInterest(Interest interest) throws HibernateException;
	
	public boolean deleteInterest(Interest interest) throws Exception;

	public void setSessionFactory(SessionFactory sessionFactory);

}
