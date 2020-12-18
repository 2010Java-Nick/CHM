package CHM.service;

import java.util.List;

import CHM.model.Payment;

public interface PaymentService{
	
	public int createPayment(Payment p);
	
	public List<Payment> readAllPayments();
	
	public Payment readPaymentById(int paymentId);
	
	public Payment readPaymentByUserId(int userId);
	
	public Payment updatePayment(Payment p);
	
	public boolean deletePayment(Payment p);

}
