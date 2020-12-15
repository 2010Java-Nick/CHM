package CHM.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CHM.config.AppConfig;
import CHM.dao.ProfileDaoHibernate;
import CHM.dao.UserDaoHibernate;
import CHM.model.Profile;
import CHM.model.User;

/**
 * 
 * @author cb-mz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class UserDaoHibernateTest {
	
	@Autowired
	private UserDaoHibernate userDaoHibernate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Mock
	private Session mockSess;

	private Profile toTest;
	
	User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		user = new User(-1, "frankp", "hunter2", null, false);
	}

	@After
	public void tearDown() throws Exception {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(user);
		tx.commit();
		sess.close();
		
	}

	@Test
	public void test() {
//		Session sess = sessionFactory.openSession();
		
		Session sessSpy = Mockito.spy(Session.class);
		
		Transaction transactionSpy = Mockito.spy(Transaction.class);
		
		when(sessionFactory.openSession()).thenReturn(sessSpy);
		
		when(mockSess.beginTransaction()).thenReturn(transactionSpy);
		
		userDaoHibernate.insertUser(user);
		
		verify(sessSpy).save(user);
		verify(transactionSpy).commit();
		
	}
	
//	//Prepared SQL statement prototype
//	private void preparedHelper() {
//		
//		Session sess = sessionFactory.openSession();
//		
//		Transaction spy = Mockito.spy(Transaction.class);
//		
//		when(sessionFactory.openSession()).thenReturn(mockSess);
//		
//		when(mockSess.beginTransaction()).thenReturn(spy);
//
//    }	

}
