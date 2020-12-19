package CHM.service;

import java.util.List;

import CHM.model.Payment;
import CHM.util.InvalidPaymentException;

public interface PaymentService{
	
	public int createPayment(Payment p) throws InvalidPaymentException;
	
	public List<Payment> readAllPayments();
	
	public Payment readPaymentById(int paymentId);
	
	public Payment readPaymentByProfileId(int userId);
	
	public Payment updatePayment(Payment p) throws InvalidPaymentException;
	
	public boolean deletePayment(Payment p);

}
