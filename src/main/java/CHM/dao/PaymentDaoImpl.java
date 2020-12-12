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

import CHM.model.Payment;

@Repository(value = "paymentDao")
public class PaymentDaoImpl implements PaymentDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
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
		Transaction tx = sess.beginTransaction();
		int id = (Integer)sess.save(payment);
		tx.commit();
		sess.close();
		return id;
	}

	@Override
	public Payment selectPayment(int paymentInt) {
		
		Payment p;
		Session sess = sessionFactory.openSession();
		p = sess.get(Payment.class, paymentInt);
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
	public Payment updatePayment(int paymentId, Payment payment) {
		  Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Payment p = 
	                    (Payment)session.get(Payment.class, paymentId); 
	         p.setCreditcardNameHolder(payment.getCreditcardNameHolder());
	         p.setCreditCardNumber(payment.getCreditCardNumber());
	         p.setCvc(payment.getCvc());
	         p.setExpirationDate(payment.getExpirationDate());
	         p.setPaymentAmount(payment.getPaymentAmount());
	         p.setUser(payment.getUser());
	         
	         session.update(p); 
	         
	         tx.commit();
	         
	         return p;
	         
	      }catch (HibernateException e) {
	    	  
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         
	      }finally {
	         session.close(); 
	      }
	      
	      return null;
	   }

	@Override
	public Payment selectPaymentByUserId(int userId) {
		Payment p;
		Session sess = sessionFactory.openSession();
		p = sess.get(Payment.class, userId);
		sess.close();
		return p;
	}

}
	



