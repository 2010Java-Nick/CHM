package CHM.test.dao;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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
		toTest = new Profile(101, "test", "test", null, null, 0, null, null);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSelectProfile() {
		
		Profile returned = profileDao.selectProfile(toTest.getProfileId());
		
		assertEquals(returned.getProfileId(), toTest.getProfileId());
	}

	@Test
	@Rollback(true)
	public void testInsertProfile() {
		
		int testProfileId = profileDao.insertProfile(toTest);
		
		Profile returned = profileDao.selectProfile(testProfileId);
		
		assertEquals(toTest.getFirstName(), returned.getFirstName());
				
	}

}
