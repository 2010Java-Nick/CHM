package CHM.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CHM.config.AppConfig;
import CHM.dao.MessageDaoHibernate;
import CHM.model.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class MessageDaoHibernateTest {
	
	@Autowired
	private MessageDaoHibernate messageDaoHibernate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	Session mockSession;
	
	@Mock
	CriteriaBuilder mockCriteriaBuilder;
	
	@Mock
	CriteriaQuery<Message> mockCriteriaQuery;
	
	@Mock
	CriteriaQuery<Message> mockCriteriaQueryTwo;
	
	@Mock
	Root<Message> mockRoot;
	
	@Mock
	Query<Message> mockTypedQuery;
	
	@Mock
	Transaction mockTransaction;
	
	private Session sess;
	
	private Message toTest;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		
		toTest = new Message(101, null, 101, 102, "test message", LocalTime.now());
		messageDaoHibernate.insertMessage(toTest);
		
		MockitoAnnotations.initMocks(this);
		
		// Make real session 
		sess = sessionFactory.openSession();
		// Mock session factory to always return the mocked session 
		when(mockSessionFactory.openSession()).thenReturn(mockSession);
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		// Set profileDao to use that mocked session factory 
		messageDaoHibernate.setSessionFactory(mockSessionFactory);
		
		//when(mockSessionFactory.openSession()).thenReturn(mockSession);
		when(mockSession.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
		when(mockCriteriaBuilder.createQuery(Message.class)).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.from(Message.class)).thenReturn(mockRoot);
		when(mockCriteriaQuery.select(mockRoot)).thenReturn(mockCriteriaQueryTwo);
		when(mockSession.createQuery(mockCriteriaQueryTwo)).thenReturn(mockTypedQuery);
		when(mockTypedQuery.getResultList()).thenReturn(new ArrayList<Message>());

	}

	@After
	public void tearDown() throws Exception {
		
		messageDaoHibernate.setSessionFactory(sessionFactory);
		try {
			messageDaoHibernate.deleteMessage(toTest);
		} catch (Exception e) {
			
		} finally {
			if (sess.isOpen()) {
				sess.close();
			}
		}
	}
	
	@Test
	public void testSelectProfile() {
		
		try {
			messageDaoHibernate.selectMessage(toTest.getMessageId());
			verify(mockSessionFactory).openSession();
			verify(mockSession).get(Message.class, toTest.getMessageId());
			verify(mockSession).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}

	@Test
	@Rollback(true)
	public void testInsertProfile() {
		
		try {
			messageDaoHibernate.insertMessage(toTest);
			verify(mockSessionFactory).openSession();
			verify(mockSession).beginTransaction();
			verify(mockTransaction).commit();
			verify(mockSession).save(toTest);
			verify(mockSession).close();
		} catch (HibernateException e) {
			fail("Exception " + e);
		}		
	}
	
	@Test
	public void testSelectAllProfiles() {
		
		try {
			
			messageDaoHibernate.selectAllMessages();
			verify(mockSessionFactory).openSession();
			verify(mockSession).getCriteriaBuilder();
			verify(mockCriteriaBuilder).createQuery(Message.class);
			verify(mockCriteriaQuery).from(Message.class);
			verify(mockCriteriaQuery).select(mockRoot);
			verify(mockSession).createQuery(mockCriteriaQueryTwo);
			verify(mockTypedQuery).getResultList();
			verify(mockSession).close();
			
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testUpdateProfile() {
		
		try {
			messageDaoHibernate.updateMessage(toTest);
			verify(mockSessionFactory).openSession();
			verify(mockSession).beginTransaction();
			verify(mockTransaction).commit();
			verify(mockSession).update(toTest);
			verify(mockSession).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testDeleteProfile() {
		
		try {
			messageDaoHibernate.deleteMessage(toTest);
			verify(mockSessionFactory).openSession();
			verify(mockSession).beginTransaction();
			verify(mockTransaction).commit();
			verify(mockSession).delete(toTest);
			verify(mockSession).close();
		} catch (Exception e) {
			fail("Exception " + e);
		}
	}

}

