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
	public int insertInterest(Interest interest) throws HibernateException {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(interest);
		tx.commit();
		sess.close();
		return interest.getInterestId();
	}

	@Override
	public Interest selectInterest(int interestId) throws HibernateException {
		
		Interest interest;
		Session sess = sessionFactory.openSession();
		interest = sess.get(Interest.class, interestId);
		sess.close();
		return interest;
	}

	@Override
	public List<Interest> selectAllInterests() throws HibernateException {
		
		List<Interest> interestList = null;
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Interest> cq = cb.createQuery(Interest.class);
		Root<Interest> rootEntry = cq.from(Interest.class);
		CriteriaQuery<Interest> all = cq.select(rootEntry);
		TypedQuery<Interest> allQuery = sess.createQuery(all);
		interestList = allQuery.getResultList();
		sess.close();
		
		return interestList;
	}

	@Override
	public Interest updateInterest(Interest interest) throws HibernateException {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.update(interest);
		tx.commit();
		sess.close();
		return interest;
	}

	@Override
	public boolean deleteInterest(Interest interest) throws Exception {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(interest);
		tx.commit();
		sess.close();
		return true;
	}


}
