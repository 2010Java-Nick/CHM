package CHM.dao;

import java.util.List;

import CHM.model.Payment;


public interface PaymentDao {
	
	public int insertPayment(Payment payment);
	
	public Payment selectPayment(int paymentInt);
	
	public List<Payment> selectAllPayments();
	
	public Payment updatePayment(Payment payment);
	
	public boolean deletePayment(Payment payment);

}
