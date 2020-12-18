package CHM.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CHM.config.AppConfig;
import CHM.dao.MatchDaoHibernate;
import CHM.model.Match;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class MatchDaoHibernateTest {

	@Autowired
	@InjectMocks
	private MatchDaoHibernate matchDaoHibernate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	Session mockSession;
	
	@Mock
	CriteriaBuilder mockCriteriaBuilder;
	
	@Mock
	CriteriaQuery<Match> mockCriteriaQuery;
	
	@Mock
	CriteriaQuery<Match> mockCriteriaQueryTwo;
	
	@Mock
	Root<Match> mockRoot;
	
	@Mock
	Query<Match> mockTypedQuery;
	
	@Mock
	Transaction mockTransaction;
	
	private Session sess;
	
	private Match toTest;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		
		toTest = new Match(1, null, null, false, 3, true);
		matchDaoHibernate.insertMatch(toTest);
		
		MockitoAnnotations.initMocks(this);
		
		// Make real session 
		sess = sessionFactory.openSession();
		// Mock session factory to always return the mocked session 
		when(mockSessionFactory.openSession()).thenReturn(mockSession);
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		
		//when(mockSessionFactory.openSession()).thenReturn(mockSession);
		when(mockSession.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
		when(mockCriteriaBuilder.createQuery(Match.class)).thenReturn(mockCriteriaQuery);
		when(mockCriteriaQuery.from(Match.class)).thenReturn(mockRoot);
		when(mockCriteriaQuery.select(mockRoot)).thenReturn(mockCriteriaQueryTwo);
		when(mockSession.createQuery(mockCriteriaQueryTwo)).thenReturn(mockTypedQuery);
		when(mockTypedQuery.getResultList()).thenReturn(new ArrayList<Match>());

	}

	@After
	public void tearDown() throws Exception {
		
		matchDaoHibernate.setSessionFactory(sessionFactory);
		try {
			matchDaoHibernate.deleteMatch(toTest);
		} catch (Exception e) {
			
		} finally {
			if (sess.isOpen()) {
				sess.close();
			}
		}
	}
	
	@Test
	public void testSelectMatch() {
		
		try {
			matchDaoHibernate.selectMatch(toTest.getMatchId());
			verify(mockSessionFactory).openSession();
			verify(mockSession).get(Match.class, toTest.getMatchId());
			verify(mockSession).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
	}

	@Test
	@Rollback(true)
	public void testInsertMatch() {
		
		try {
			matchDaoHibernate.insertMatch(toTest);
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
	public void testSelectAllMatchs() {
		
		try {
			
			matchDaoHibernate.selectAllMatches();
			verify(mockSessionFactory).openSession();
			verify(mockSession).getCriteriaBuilder();
			verify(mockCriteriaBuilder).createQuery(Match.class);
			verify(mockCriteriaQuery).from(Match.class);
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
	public void testUpdateMatch() {
		
		try {
			matchDaoHibernate.updateMatch(toTest);
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
	public void testDeleteMatch() {
		
		try {
			matchDaoHibernate.deleteMatch(toTest);
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
