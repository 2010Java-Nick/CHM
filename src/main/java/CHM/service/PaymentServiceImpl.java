package CHM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import CHM.dao.PaymentDao;
import CHM.model.Payment;
import CHM.model.User;

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
	public Payment createPayment(Payment p) {
		// TODO Auto-generated method stub
		return null;
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
		return paymentDao.selectPaymentByUserId(userId);
	}

	@Override
	public Payment updatePayment(int paymentId, Payment p) {
		return paymentDao.updatePayment(paymentId, p);
	}

	
	
}
