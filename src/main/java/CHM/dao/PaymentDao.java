package CHM.dao;

import java.util.List;

import CHM.model.Payment;

public interface PaymentDao {
	
public int insertProfile(Payment payment);
	
	public Payment selectProfile(int paymentInt);
	
	public List<Payment> selectAllProfiles();
	
	public Payment updateUser(int paymentId, Payment payment);
	
	public boolean deleteProfile(Payment payment);

}
