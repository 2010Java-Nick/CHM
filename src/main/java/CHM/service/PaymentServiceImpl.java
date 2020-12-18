package CHM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import CHM.dao.PaymentDao;
import CHM.model.Payment;

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
	 * @Autowired
	 * public void setUserService(UserService userService) {
	 * 		this.userService = userService;
	 * }
	 */
	
	@Override
	public int createPayment(Payment p) {
		return paymentDao.insertPayment(p);
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
	public Payment readPaymentByUserId(int userId) {
		return paymentDao.selectPayment(userId);
	}

	@Override
	public Payment updatePayment(Payment p) {
		return paymentDao.updatePayment(p);
	}

	@Override
	public boolean deletePayment(Payment p) {
		return paymentDao.deletePayment(p);
	}

	
	
}
