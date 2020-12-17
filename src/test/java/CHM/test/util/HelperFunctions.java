package CHM.test.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CHM.model.Profile;

import static CHM.util.HelperFunctions.*;

public class HelperFunctions {

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
		Profile test1 = new Profile(1, "first", "last", "mroy@hotmail.com", "1231231234", 19, "hi", "yo", "building");
		Profile test2 = new Profile(1, "first", "last", "mroy@hotmail.com", "1231231234", 15, "hi", "yo", "building");
		Profile test3 = new Profile(1, "first", "last", "mroy@hotm.a@il.com", "1231231234", 19, "hi", "yo", "building");
		Profile test4 = new Profile(1, "first", "last", "mroy@hotmail.com", ".231231234", 19, "hi", "yo", "building");
		assertTrue(validateProfile(test1));
		assertFalse(validateProfile(test2));
		assertFalse(validateProfile(test3));
		assertFalse(validateProfile(test4));
	}

}
