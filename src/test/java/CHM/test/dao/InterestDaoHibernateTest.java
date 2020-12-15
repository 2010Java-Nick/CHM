package CHM.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CHM.config.AppConfig;
import CHM.dao.InterestDaoHibernate;
import CHM.model.Interest;
import CHM.model.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class InterestDaoHibernateTest {
	
	@Autowired
	private InterestDaoHibernate interestDaoHibernate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	Session mockSession;
	
	@Mock
	Transaction mockTransaction;
	
	private Session sess;
	
	private Session spy;
	
	private Interest interest;	
	
	private Profile profile = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		interest = new Interest(101, profile, "Walking dogs");
		interestDaoHibernate.insertInterest(interest);
		
		MockitoAnnotations.initMocks(this);
		
		// Make a real session
		sess = sessionFactory.openSession();
		
		// Spy on that real session
		spy = Mockito.spy(sess);
		
		// Mock session factory to always return the spied on session 
		when(mockSessionFactory.openSession()).thenReturn(spy);
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		
		// Set profileDao to use that mocked session factory 
		interestDaoHibernate.setSessionFactory(mockSessionFactory);
		
	}

	@After
	public void tearDown() throws Exception {
		
		sess.close();
	}

	@Test
	public void testSelectInterest() {
		try {
			interestDaoHibernate.selectInterest(interest.getInterestId());
			verify(spy).get(Interest.class, interest.getInterestId());
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testInsertInterest() {
		
		try {
			interestDaoHibernate.insertInterest(interest);
			verify(spy).save(interest);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException: " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testSelectAllInterests() {
		try {
			interestDaoHibernate.selectAllInterests();
			verify(spy).getCriteriaBuilder();
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Excepton " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testUpdateInterest() {
		try {
			interestDaoHibernate.updateInterest(interest);
			verify(spy).update(interest);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Excepton " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testDeleteInterest() {
		try {
			interestDaoHibernate.deleteInterest(interest);
			verify(spy).delete(interest);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Excepton " + e);
		}
	}

}
