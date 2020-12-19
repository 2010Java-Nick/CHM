package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import CHM.dao.PaymentDao;
import CHM.model.Payment;
import CHM.util.InvalidMessageException;
import CHM.util.InvalidPaymentException;

import static CHM.util.HelperFunctions.isValidMessage;
import static CHM.util.HelperFunctions.isValidPayment;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentDao paymentDao;
	
	PaymentService paymentService;
	
	@Autowired
	@Qualifier(value = "paymentDao")
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}
	
	/**
	 * We need an user dao service implmentation
	 * @throws InvalidPaymentException 
	 * @Autowired
	 * public void setUserService(UserService userService) {
	 * 		this.userService = userService;
	 * }
	 */
	
	@Override
	public int createPayment(Payment p) throws InvalidPaymentException {
		
		if (isValidPayment(p)) {
			try {
				return paymentDao.insertPayment(p);
			} catch (HibernateException e) {
				return -1;
			} 
		}
		throw new InvalidPaymentException("Attempting to create invalid payment.");
	}

	@Override
	public List<Payment> readAllPayments() {
		
		return paymentDao.selectAllPayments();
	}

	@Override
	public Payment readPaymentById(int paymentId) {
		return paymentDao.selectPayment(paymentId);
	}

	@Override
	public Payment readPaymentByProfileId(int profileId) {
		return paymentDao.selectPaymentByProfileId(profileId);
	}

	@Override
	public Payment updatePayment(Payment p) throws InvalidPaymentException {
		if (isValidPayment(p)) {
			try {
				return paymentDao.updatePayment(p);
			} catch (HibernateException e) {
				return null;
			} 
		}
		throw new InvalidPaymentException("Attempting to create invalid payment.");
	}

	@Override
	public boolean deletePayment(Payment p) {
		return paymentDao.deletePayment(p);
	}

	
	
}
