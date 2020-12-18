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

import CHM.model.User;

/**
 * 
 * @author cb-mz
 *
 */
@Repository(value = "userDao")
public class UserDaoHibernate implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int insertUser(User user) throws HibernateException {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		user.setUserId((Integer)sess.save(user));
		tx.commit();
		sess.close();
		return user.getUserId();
		
	}

	@Override
	public User selectUser(int userId) throws HibernateException {
		
		User user;
		Session sess = sessionFactory.openSession();
		user = sess.get(User.class, userId);
		sess.close();
		return user;
		
	}

	@Override
	public List<User> selectAllUsers() throws HibernateException {
		
		List<User> userList = null;
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> rootEntry = cq.from(User.class);
		CriteriaQuery<User> all = cq.select(rootEntry);
		TypedQuery<User> allQuery = sess.createQuery(all);
		userList = allQuery.getResultList();
		sess.close();
		
		return userList;
		
	}

	@Override
	public User updateUser(int userId, User user) throws HibernateException {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.update(user);
		tx.commit();
		sess.close();
		return user;
		
	}

	@Override
	public boolean deleteUser(User user) throws Exception {
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(user);
		tx.commit();
		sess.close();
		return true;
	}

}
