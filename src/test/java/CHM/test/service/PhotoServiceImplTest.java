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
import CHM.dao.PhotoDao;
import CHM.model.Photo;
import CHM.model.Profile;
import CHM.service.PhotoService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class PhotoServiceImplTest {
	
	@Autowired
	@InjectMocks
	private PhotoService photoService;
	
	@Mock
	private PhotoDao mockPhotoDao;
	
	private Photo toTest;
	
	private Profile profile = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		toTest = new Photo(101, null, profile);
		MockitoAnnotations.initMocks(this);
		
		when(mockPhotoDao.selectPhoto(toTest.getPhotoId())).thenReturn(toTest);
		when(mockPhotoDao.insertPhoto(toTest)).thenReturn(toTest.getPhotoId());
		when(mockPhotoDao.selectAllPhotos()).thenReturn(new ArrayList<Photo>());
		when(mockPhotoDao.updatePhoto(toTest)).thenReturn(toTest);
		when(mockPhotoDao.deletePhoto(toTest)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreatePhoto() {
		
		try {
			photoService.createPhoto(toTest);
			verify(mockPhotoDao).insertPhoto(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadPhotoById() {
		
		try {
			photoService.readPhotoById(toTest.getPhotoId());
			verify(mockPhotoDao).selectPhoto(toTest.getPhotoId());
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadPhotosByProfile() {
		
		try {
			photoService.readPhotosByProfile(profile);
			verify(mockPhotoDao).selectAllPhotos();
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testUpdatePhoto() {
		
		try {
			profileService.updateProfile(toTest);
			verify(mockProfileDao).updateProfile(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testDeletePhoto() {
		
		try {
			profileService.deleteProfile(toTest);
			verify(mockProfileDao).deleteProfile(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}

}
