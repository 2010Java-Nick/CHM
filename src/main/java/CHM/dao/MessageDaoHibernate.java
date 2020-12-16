package CHM.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import CHM.model.Message;
import CHM.model.Profile;

@Repository(value = "messageDao")
public class MessageDaoHibernate implements MessageDao {

	
	SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int insertMessage(Message message) {
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(message);
		tx.commit();
		sess.close();
		return message.getMessageId();

	}

	@Override
	public Message selectMessage(int messageId) {
		Message message;
		Session sess = sessionFactory.openSession();
		message = sess.get(Message.class, messageId);
		sess.close();
		return message;
	}

	@Override
	public List<Message> selectAllMessages() {
		List<Message> messageList = null;
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> rootEntry = cq.from(Message.class);
		CriteriaQuery<Message> all = cq.select(rootEntry);
		TypedQuery<Message> allQuery = sess.createQuery(all);
		messageList = allQuery.getResultList();
		sess.close();
		
		return messageList;
	}

	@Override
	public Message updateMessage(Message message) {
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.update(message);
		tx.commit();
		sess.close();
		return message;
	}

	@Override
	public boolean deleteMessage(Message message) {
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(message);
		tx.commit();
		sess.close();
		return true;
	}

}
