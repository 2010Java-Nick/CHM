package CHM.dao;

import java.util.List;

import CHM.model.Payment;


public interface PaymentDao {
	
	public int insertPayment(Payment payment);
<<<<<<< HEAD
	
	public Payment selectPayment(int paymentInt);
	
	public Payment selectPaymentByUserId(int userId);
=======
	
	public Payment selectPayment(int paymentInt);
>>>>>>> main
	
	public List<Payment> selectAllPayments();
	
	public Payment updatePayment(int paymentId, Payment payment);
	
<<<<<<< HEAD
	//public boolean deleteProfile(Payment payment);
=======
	public boolean deletePayment(Payment payment);
>>>>>>> main

}
