package CHM.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CHM.config.AppConfig;
import CHM.dao.UserDao;
import CHM.model.User;
import CHM.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class UserServiceImpltest {

	@Autowired
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserDao mockUserDao;
	
	private User toTest;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		toTest = new User(101, "user", "pass", null, false);
		MockitoAnnotations.initMocks(this);
		
		when(mockUserDao.selectUser(toTest.getUserId())).thenReturn(toTest);
		when(mockUserDao.insertUser(toTest)).thenReturn(toTest.getUserId());
		when(mockUserDao.selectAllUsers()).thenReturn(new ArrayList<User>());
		when(mockUserDao.updateUser(toTest)).thenReturn(toTest);
		when(mockUserDao.deleteUser(toTest)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateUser() {
		
		try {
			userService.createUser(toTest);
			verify(mockUserDao).insertUser(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadUserById() {
		
		try {
			userService.readUserById(toTest.getUserId());
			verify(mockUserDao).selectUser(toTest.getUserId());
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadAllUsers() {
		
		try {
			userService.readAllUsers();
			verify(mockUserDao).selectAllUsers();
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testUpdateUser() {
		
		try {
			userService.updateUser(toTest);
			verify(mockUserDao).updateUser(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testDeleteUser() {
		
		try {
			userService.deleteUser(toTest);
			verify(mockUserDao).deleteUser(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
}
