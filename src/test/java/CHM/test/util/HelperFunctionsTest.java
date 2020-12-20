package CHM.test.util;

import static CHM.util.HelperFunctions.isValidEmail;
import static CHM.util.HelperFunctions.isValidLocalDateTimeString;
import static CHM.util.HelperFunctions.validatePhoneNumber;
import static CHM.util.HelperFunctions.validateProfile;
import static CHM.util.HelperFunctions.isValidMessage;
import static CHM.util.HelperFunctions.isValidCreditCardNum;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import CHM.dao.MatchDao;
import CHM.dao.MatchDaoHibernate;
import CHM.dao.MessageDao;
import CHM.dao.MessageDaoHibernate;
import CHM.dao.ProfileDao;
import CHM.dao.ProfileDaoHibernate;
import CHM.model.Match;
import CHM.model.Profile;
import CHM.util.SessionFactoryUtil;
import CHM.model.Message;

public class HelperFunctionsTest {
	
	SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
	
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
	public void testIsValidEmail() {
		assertTrue(isValidEmail("marcjacobroy@gmail.com"));
		assertFalse(isValidEmail("marcjacobroy@@gmail.c,om"));
	}
	
	@Test
	public void testValidatePhoneNumber() {
		assertTrue(validatePhoneNumber("6033932434"));
		assertTrue(validatePhoneNumber("(603)-393-2434"));
		assertFalse(validatePhoneNumber(""));
		assertFalse(validatePhoneNumber(null));
		assertFalse(validatePhoneNumber("123123123"));
	}
	
	@Test
	public void testValidateProfile() {
		Profile test1 = new Profile(1, "first", "last", "mroy@hotmail.com", "1231231234", 19, "hi", "yo");
		Profile test2 = new Profile(1, "first", "last", "mroy@hotmail.com", "1231231234", 15, "hi", "yo");
		Profile test3 = new Profile(1, "first", "last", "mroy@hotm.a@il.com", "1231231234", 19, "hi", "yo");
		Profile test4 = new Profile(1, "first", "last", "mroy@hotmail.com", ".231231234", 19, "hi", "yo");
		assertTrue(validateProfile(test1));
		assertFalse(validateProfile(test2));
		assertFalse(validateProfile(test3));
		assertFalse(validateProfile(test4));
	}
	
	@Test
	public void testIsValidLocalDateTimeString() {
		String str1 = "2017-08-11 13:05:10";
		String str2 = "2017-90-11 00:00:00";
		String str3 = "2017-08-45 00:00:00";
		String str4 = "2015-02-32 00:00:00";
		String str5 = "2017-08-11 20:00:00";
		String str6 = "2017-08-11 30:00:00";
		String str7 = "2017-08-11 00:70:00";
		String str8 = "2017-08-11 00:00:68";
		String str9 = "2017-08-11 -10:00:00";
		String str10 = "dummy";
		
		assertTrue(isValidLocalDateTimeString(str1));
		assertFalse(isValidLocalDateTimeString(str2));
		assertFalse(isValidLocalDateTimeString(str3));
		assertFalse(isValidLocalDateTimeString(str4));
		assertTrue(isValidLocalDateTimeString(str5));
		assertFalse(isValidLocalDateTimeString(str6));
		assertFalse(isValidLocalDateTimeString(str7));
		assertFalse(isValidLocalDateTimeString(str8));
		assertFalse(isValidLocalDateTimeString(str9));
		assertFalse(isValidLocalDateTimeString(str10));
	}
	
	@Test
    @Rollback(true)
	public void testIsValidMessage() throws Exception {
		MatchDao matchDao = new MatchDaoHibernate();
		ProfileDao profileDao = new ProfileDaoHibernate();
		MessageDao messageDao = new MessageDaoHibernate();
		matchDao.setSessionFactory(sessionFactory);
		profileDao.setSessionFactory(sessionFactory);
		messageDao.setSessionFactory(sessionFactory);
		Profile p1 = new Profile(1, "first", "last", "mroy@hotmail.com", "1231231234", 19, "hi", "yo");
		Profile p2 = new Profile(2, "first", "last", "mroy@hotmail.com", "1231231234", 19, "hi", "yo");
		Match m = new Match(1, p1, p2, false, 0, true);
		Message msg = new Message(1, m, p1.getProfileId(), p2.getProfileId(), "testMessage", "2017-08-11 13:05:10");
		profileDao.insertProfile(p1);
		profileDao.insertProfile(p2);
		matchDao.insertMatch(m);
		assertTrue(isValidMessage(msg));
		matchDao.deleteMatch(m);
		msg = new Message(2, m, 100, 1, "", "2017-08-11 13:05:10");
		assertFalse(isValidMessage(msg));
	}
	
	@Test
	public void testIsValidCreditCardNum() {
		String s1 = "4111111111111111";
		String s2 = "5500000000000004";
		String s3 = "3400";
		String s4 = null;
		String s5 = "";
		assertTrue(isValidCreditCardNum(s1));
		assertTrue(isValidCreditCardNum(s2));
		assertFalse(isValidCreditCardNum(s3));
		assertFalse(isValidCreditCardNum(s4));
		assertFalse(isValidCreditCardNum(s5));
	}

}
