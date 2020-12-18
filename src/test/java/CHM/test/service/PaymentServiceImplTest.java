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
import CHM.dao.PaymentDao;
import CHM.model.Payment;
import CHM.service.PaymentService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class PaymentServiceImplTest {

	@Autowired
	@InjectMocks
	private PaymentService paymentService;
	
	@Mock
	private PaymentDao mockPaymentDao;
	
	private Payment toTest;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		toTest = new Payment(1, null, null, 0, 0, null, null);
		MockitoAnnotations.initMocks(this);
		
		when(mockPaymentDao.selectPayment(toTest.getPaymentId())).thenReturn(toTest);
		when(mockPaymentDao.insertPayment(toTest)).thenReturn(toTest.getPaymentId());
		when(mockPaymentDao.selectAllPayments()).thenReturn(new ArrayList<Payment>());
		when(mockPaymentDao.updatePayment(toTest)).thenReturn(toTest);
		when(mockPaymentDao.deletePayment(toTest)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreatePayment() {
		
		try {
			paymentService.createPayment(toTest);
			verify(mockPaymentDao).insertPayment(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadPaymentById() {
		
		try {
			paymentService.readPaymentById(toTest.getPaymentId());
			verify(mockPaymentDao).selectPayment(toTest.getPaymentId());
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testReadAllPayments() {
		
		try {
			paymentService.readAllPayments();
			verify(mockPaymentDao).selectAllPayments();
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testUpdatePayment() {
		
		try {
			paymentService.updatePayment(toTest);
			verify(mockPaymentDao).updatePayment(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}
	
	@Test
	public void testDeletePayment() {
		
		try {
			paymentService.deletePayment(toTest);
			verify(mockPaymentDao).deletePayment(toTest);
		} catch (Exception e) {
			fail("Failed with exception " + e);
		}
	}

}
