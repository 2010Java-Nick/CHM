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
import CHM.dao.ProfileDao;
import CHM.model.Profile;
import CHM.service.ProfileService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class ProfileServiceImplTest {

	
	@Autowired
	@InjectMocks
	private ProfileService profileService;
	
	@Mock
	private ProfileDao mockProfileDao;
	
	private Profile toTest;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		toTest = new Profile(101, "first", "last", "email@gmail.com", "6032861234", 28, "hello world", "i like dogs", "dogs");
		MockitoAnnotations.initMocks(this);
		
		when(mockProfileDao.selectProfile(toTest.getProfileId())).thenReturn(toTest);
		when(mockProfileDao.insertProfile(toTest)).thenReturn(toTest.getProfileId());
		when(mockProfileDao.selectAllProfiles()).thenReturn(new ArrayList<Profile>());
		when(mockProfileDao.updateProfile(toTest)).thenReturn(toTest);
		when(mockProfileDao.deleteProfile(toTest)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateProfile() {
		
		try {
			profileService.createProfile(toTest);
			verify(mockProfileDao).insertProfile(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadProfileById() {
		
		try {
			profileService.readProfileById(toTest.getProfileId());
			verify(mockProfileDao).selectProfile(toTest.getProfileId());
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadAllProfiles() {
		
		try {
			profileService.readAllProfiles();
			verify(mockProfileDao).selectAllProfiles();
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testUpdateProfile() {
		
		try {
			profileService.updateProfile(toTest);
			verify(mockProfileDao).updateProfile(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testDeleteProfile() {
		
		try {
			profileService.deleteProfile(toTest);
			verify(mockProfileDao).deleteProfile(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}

}
