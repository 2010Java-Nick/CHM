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
import CHM.dao.MatchDao;
import CHM.model.Match;
import CHM.service.MatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class MatchServiceImplTest {

	@Autowired
	@InjectMocks
	private MatchService MatchService;
	
	@Mock
	private MatchDao mockMatchDao;
	
	private Match toTest;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		toTest = new Match(105, null, null, false, 0, true);
		MockitoAnnotations.initMocks(this);
		
		when(mockMatchDao.selectMatch(toTest.getMatchId())).thenReturn(toTest);
		when(mockMatchDao.insertMatch(toTest)).thenReturn(toTest.getMatchId());
		when(mockMatchDao.selectAllMatches()).thenReturn(new ArrayList<Match>());
		when(mockMatchDao.updateMatch(toTest)).thenReturn(toTest);
		when(mockMatchDao.deleteMatch(toTest)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateMatch() {
		
		try {
			MatchService.createMatch(toTest);
			verify(mockMatchDao).insertMatch(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadMatchById() {
		
		try {
			MatchService.readMatchById(toTest.getMatchId());
			verify(mockMatchDao).selectMatch(toTest.getMatchId());
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadAllMatches() {
		
		try {
			MatchService.readAllMatches();
			verify(mockMatchDao).selectAllMatches();
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testUpdateMatch() {
		
		try {
			MatchService.updateMatch(toTest);
			verify(mockMatchDao).updateMatch(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testDeleteMatch() {
		
		try {
			MatchService.deleteMatch(toTest);
			verify(mockMatchDao).deleteMatch(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}

}
