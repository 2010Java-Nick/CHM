package CHM.test.util;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CHM.util.SessionFactoryUtil;

public class SessionFactoryUtilTest {
	
	private SessionFactoryUtil sessionFactoryUtil;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		try {
			sessionFactoryUtil = SessionFactoryUtil.getSessionFactoryUtil();
			SessionFactory sessionFactory = sessionFactoryUtil.getSessionFactory();
			if(sessionFactory == null) {
				fail("Session factory was null");
			}
		} catch (Exception e) {
			fail("exception thrown!" + e);
		}
		
	}

}
