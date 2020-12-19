package CHM.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CHM.config.AppConfig;
import CHM.dao.UserDaoHibernate;
import CHM.model.User;

/**
 * 
 * @author cb-mz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class UserDaoHibernateTest {
	
	@Autowired
	private UserDaoHibernate userDaoHibernate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	Session mockSession;
	
	private Session sess;
	
	private Session spy;
	
	private User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		// Make a real session
		sess = sessionFactory.openSession();
		
		// Spy on that real session
		spy = Mockito.spy(sess);
		
		// Mock session factory to always return the spied on session 
		when(mockSessionFactory.openSession()).thenReturn(spy);
		
		// Set profileDao to use that mocked session factory 
		userDaoHibernate.setSessionFactory(mockSessionFactory);
		
		user = new User(1, "frankp", "hunter2", null, false);
		
	}

	@After
	public void tearDown() throws Exception {
		
		sess.close();
//		userDaoHibernate.setSessionFactory(sessionFactory);
//		userDaoHibernate.deleteUser(user);
		
	}
	
	@Test
	public void testSelectUser() {
		
		try {
			userDaoHibernate.selectUser(user.getUserId());
			verify(spy).get(User.class, user.getUserId());
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
		
	}

	@Test
	@Rollback(true)
	public void testInsertUser() {
		
		try {
			userDaoHibernate.insertUser(user);
			verify(spy).save(user);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException: " + e);
		}
		
	}
	
	@Test
	public void testSelectAllUsers() {
		
		try {
			userDaoHibernate.selectAllUsers();
			verify(spy).getCriteriaBuilder();
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Exception " + e);
		}
		
	}
	
	@Test
	@Rollback(true)
	public void testUpdateUser() {
		
		try {
			sess.save(user);
			user.setUsername("frankq");
			userDaoHibernate.updateUser(user);
			verify(spy).update(user);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Exception " + e);
		}
		
	}
	
	@Test
	@Rollback(true)
	public void testDeleteUser() {
		
		try {
			userDaoHibernate.deleteUser(user);
			verify(spy).delete(user);
			verify(spy).close();
		} catch (Exception e) {
			fail("Exception " + e);
		}
		
	}
	
}
	