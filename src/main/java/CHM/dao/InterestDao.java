package CHM.dao;

import java.util.List;

import org.hibernate.HibernateException;

import CHM.model.Interest;

public interface InterestDao{
	
	public int insertInterest(Interest interest) throws HibernateException;
	
	public Interest selectInterest(int interestId) throws HibernateException;
	
	public List<Interest> selectAllInterests() throws HibernateException;
	
	public void updateInterest(Interest interest) throws HibernateException;
	
	public void deleteInterest(Interest interest) throws HibernateException;

}
