package CHM.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
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
import CHM.dao.MessageDao;
import CHM.model.Message;
import CHM.service.MessageService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class MessageServiceImplTest {

	@Autowired
	@InjectMocks
	private MessageService messageService;
	
	@Mock
	private MessageDao mockMessageDao;
	
	private Message toTest;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		toTest = new Message(101, null, 100, 102, "test message", LocalTime.now());
		MockitoAnnotations.initMocks(this);
		
		when(mockMessageDao.selectMessage(toTest.getMessageId())).thenReturn(toTest);
		when(mockMessageDao.insertMessage(toTest)).thenReturn(toTest.getMessageId());
		when(mockMessageDao.selectAllMessages()).thenReturn(new ArrayList<Message>());
		when(mockMessageDao.updateMessage(toTest)).thenReturn(toTest);
		when(mockMessageDao.deleteMessage(toTest)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateMessage() {
		
		try {
			messageService.createMessage(toTest);
			verify(mockMessageDao).insertMessage(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadMessageById() {
		
		try {
			messageService.readMessageById(toTest.getMessageId());
			verify(mockMessageDao).selectMessage(toTest.getMessageId());
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadAllMessages() {
		
		try {
			messageService.readAllMessages();
			verify(mockMessageDao).selectAllMessages();
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testUpdateMessage() {
		
		try {
			messageService.updateMessage(toTest);
			verify(mockMessageDao).updateMessage(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testDeleteMessage() {
		
		try {
			messageService.deleteMessage(toTest);
			verify(mockMessageDao).deleteMessage(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
}
