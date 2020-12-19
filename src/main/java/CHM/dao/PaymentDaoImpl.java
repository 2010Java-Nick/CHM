package CHM.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.Query;
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

import CHM.model.Message;
import CHM.model.Payment;

@Repository(value = "paymentDao")
public class PaymentDaoImpl implements PaymentDao {
	
	SessionFactory sessionFactory;
	
	public PaymentDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public int insertPayment(Payment payment) {
		Session sess = sessionFactory.openSession();
		Transaction tx = null;
		int id = 0;
		try {
			
			tx = sess.beginTransaction();
			id = (Integer)sess.save(payment);
			tx.commit();
			sess.close(); 
			
		}catch (HibernateException e) {
	    	  
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         return 0;
	    }
		return id;
	}

	@Override
	public Payment selectPayment(int id) {
		
		Payment p;
		Session sess = sessionFactory.openSession();
		p = sess.get(Payment.class, id);
		sess.close();
		return p;
		
	}

	@Override
	public List<Payment> selectAllPayments() {
		
		List<Payment> paymentList = null;
		
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
		Root<Payment> rootEntry = cq.from(Payment.class);
		CriteriaQuery<Payment> all = cq.select(rootEntry);
		TypedQuery<Payment> allQuery = sess.createQuery(all);
		paymentList = allQuery.getResultList();
		sess.close();
		return paymentList;
		
	}

	@Override
	public Payment updatePayment(Payment payment){
		  Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         
	         session.update(payment); 
	         
	         tx.commit();
	         
	         return payment;
	         
	      }catch (HibernateException e) {
	    	  
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         
	      }finally {
	    	  session.close(); 
	      }
	      
	      return null;
	   }


	@Override
	public boolean deletePayment(Payment payment) throws HibernateException{
		Session sess = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = sess.beginTransaction();
			sess.delete(payment);
			tx.commit();
			sess.close();
		}catch (HibernateException e) {
	    	  
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         return false;
	    }
		
		return true;
	}

	@Override
	public Payment selectPaymentByProfileId(int profileId) {
		try {
			Session sess = sessionFactory.openSession();
			String hql = "from Payment WHERE profile_id = :profileId";
			Query query = sess.createQuery(hql);
			query.setParameter("profileId", profileId);
			List<Payment> results = (List<Payment>)query.getResultList();
			sess.close();
			return results.get(0);
		} catch (HibernateException e) {
			return null;
		}
	}

}
	



