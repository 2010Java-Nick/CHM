package CHM.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CHM.config.AppConfig;
import CHM.dao.PaymentDaoImpl;
import CHM.model.Payment;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class PaymentDaoImplTest {
	
	@Autowired
	private PaymentDaoImpl paymentDaoImpl;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	Session mockSession;
	
	private Session sess;
	
	private Session spy;
	
	private Payment payment;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		// Make a real session
		sess = sessionFactory.openSession();
		
		// Spy on that real session
		spy = Mockito.spy(sess);
		
		// Mock session factory to always return the spied on session 
		when(mockSessionFactory.openSession()).thenReturn(spy);
		
		// Set profileDao to use that mocked session factory 
		paymentDaoImpl.setSessionFactory(mockSessionFactory);
		
		payment = new Payment(1, null, "123456789", 111, 9.99,"Michael Zide", "11/21");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectPayment() {
		
		try {
			paymentDaoImpl.selectPayment(payment.getPaymentId());
			verify(spy).get(Payment.class, payment.getPaymentId());
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException " + e);
		}
		
	}

	@Test
	@Rollback(true)
	public void testInsertPayment() {
		
		try {
			paymentDaoImpl.insertPayment(payment);
			verify(spy).save(payment);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("HibernateException: " + e);
		}
		
	}
	
	@Test
	public void testSelectAllPayments() {
		
		try {
			paymentDaoImpl.selectAllPayments();
			verify(spy).getCriteriaBuilder();
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Excepton " + e);
		}
		
	}
	
	@Test
	@Rollback(true)
	public void testUpdatePayment() {
		
		try {
			sess.save(payment);
			payment.setCreditCardNumber("987654321");
			paymentDaoImpl.updatePayment(payment);
			verify(spy).update(payment);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Excepton " + e);
		}
		
	}
	
	@Test
	@Rollback(true)
	public void testDeletePayment() {
		
		try {
			paymentDaoImpl.deletePayment(payment);
			verify(spy).delete(payment);
			verify(spy).close();
		} catch (HibernateException e) {
			fail("Excepton " + e);
		}
		
	}

}
