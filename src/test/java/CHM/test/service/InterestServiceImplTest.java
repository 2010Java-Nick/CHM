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
import CHM.dao.InterestDao;
import CHM.model.Interest;
import CHM.model.Profile;
import CHM.service.InterestService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class InterestServiceImplTest {
	
	@Autowired
	@InjectMocks
	private InterestService interestService;
	
	@Mock
	private InterestDao mockInterestDao;
	
	private Interest toTest;
	
	private Profile profile = null;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		toTest = new Interest(101, profile, "Coding");
		MockitoAnnotations.initMocks(this);
		
		when(mockInterestDao.selectInterest(toTest.getInterestId())).thenReturn(toTest);
		when(mockInterestDao.insertInterest(toTest)).thenReturn(toTest.getInterestId());
		when(mockInterestDao.selectAllInterests()).thenReturn(new ArrayList<Interest>());
		when(mockInterestDao.updateInterest(toTest)).thenReturn(toTest);
		when(mockInterestDao.deleteInterest(toTest)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateInterest() {
		
		try {
			interestService.createInterest(toTest);
			verify(mockInterestDao).insertInterest(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadInterestById() {
		
		try {
			interestService.readInterestById(toTest.getInterestId());
			verify(mockInterestDao).selectInterest(toTest.getInterestId());
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadAllInterests() {
		
		try {
			interestService.readAllInterests();
			verify(mockInterestDao).selectAllInterests();
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testUpdateInterest() {
		
		try {
			interestService.updateInterest(toTest);
			verify(mockInterestDao).updateInterest(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testDeleteInterest() {
		
		try {
			interestService.deleteInterest(toTest);
			verify(mockInterestDao).deleteInterest(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}

}