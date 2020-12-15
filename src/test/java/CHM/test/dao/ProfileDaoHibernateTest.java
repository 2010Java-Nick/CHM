package CHM.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
import CHM.config.TestConfig;
import CHM.dao.ProfileDaoHibernate;
import CHM.model.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class ProfileDaoHibernateTest {
	
	@Autowired
	private ProfileDaoHibernate profileDaoHibernate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	SessionFactory mockSessionFactoryTwo;
	
	@Mock
	Session mockSession;
	
	@Mock
	CriteriaBuilder mockCriteriaBuilder;
	
	@Mock
	CriteriaQuery<Profile> mockCriteriaQuery;
	
	@Mock
	CriteriaQuery<Profile> mockCriteriaQueryTwo;
	
	@Mock
	Root<Profile> mockRoot;
	
	@Mock
	Query<Profile> mockTypedQuery;
	
	private Session sess;
	
	private Session spy;

	private Profile toTest;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//TODO: get this into mocked DB or test DB
		toTest = new Profile(101, "first", "last", "email", "60332861234", 28, "hello world", "i like dogs", "dogs");
		profileDaoHibernate.insertProfile(toTest);
		
		MockitoAnnotations.initMocks(this);
		
		// Make real session 
		sess = sessionFactory.openSession();
		// Spy on that real session 
		spy = Mockito.spy(sess);
		// Mock session factory to always return the spied on session 
		when(mockSessionFactory.openSession()).thenReturn(spy);
		// Set profileDao to use that mocked session factory 
		profileDaoHibernate.setSessionFactory(mockSessionFactory);
		
		when(mockSessionFactoryTwo.openSession()).thenReturn(mockSession);
		when(mockSession.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
		when(mockCriteriaBuilder.createQuery(Profile.class)).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.from(Profile.class)).thenReturn(mockRoot);
		when(mockCriteriaQuery.select(mockRoot)).thenReturn(mockCriteriaQueryTwo);
		when(mockSession.createQuery(mockCriteriaQueryTwo)).thenReturn(mockTypedQuery);
		when(mockTypedQuery.getResultList()).thenReturn(new ArrayList<Profile>());
	}

	@After
	public void tearDown() throws Exception {
		
		profileDaoHibernate.setSessionFactory(sessionFactory);
		try {
			profileDaoHibernate.deleteProfile(toTest);
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
			profileDaoHibernate.selectProfile(toTest.getProfileId());
			verify(mockSessionFactory).openSession();
			verify(spy).get(Profile.class, toTest.getProfileId());
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}

	@Test
	@Rollback(true)
	public void testInsertProfile() {
		
		try {
			profileDaoHibernate.insertProfile(toTest);
			verify(mockSessionFactory).openSession();
			verify(spy).beginTransaction();
			verify(spy).save(toTest);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Exception " + e);
		}		
	}
	
	@Test
	public void testSelectAllProfiles() {
		
		try {
			
			profileDaoHibernate.setSessionFactory(mockSessionFactoryTwo);
			profileDaoHibernate.selectAllProfiles();
			verify(mockSessionFactoryTwo).openSession();
			verify(mockSession).getCriteriaBuilder();
			verify(mockCriteriaBuilder).createQuery(Profile.class);
			verify(mockCriteriaQuery).from(Profile.class);
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
			profileDaoHibernate.updateProfile(toTest);
			verify(mockSessionFactory).openSession();
			verify(spy).beginTransaction();
			verify(spy).update(toTest);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}
	
	@Test
	@Rollback(true)
	public void testDeleteProfile() {
		
		try {
			profileDaoHibernate.deleteProfile(toTest);
			verify(mockSessionFactory).openSession();
			verify(spy).beginTransaction();
			verify(spy).delete(toTest);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}

}
