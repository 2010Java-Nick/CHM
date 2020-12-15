/**
 * 
 */
package CHM.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Work From Home
 *
 */
@Entity
@Table(name = "payment")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "paymentId")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private int paymentId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	@Column(name = "card_number")
	private String creditCardNumber;
	
	@Column(name = "cvc")
	private int cvc;
	
	@Column(name = "payment_amount")
	private double paymentAmount;
	
	@Column(name = "card_name_holder")
	private String creditcardNameHolder;
	
	@Column(name = "expiration_date")
	private String expirationDate;
	
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param paymentId
	 * @param profile
	 * @param creditCardNumber
	 * @param cvc
	 * @param paymentAmount
	 * @param creditcardNameHolder
	 * @param expirationDate
	 */
	public Payment(int paymentId, Profile profile, String creditCardNumber, int cvc, double paymentAmount,
			String creditcardNameHolder, String expirationDate) {
		super();
		this.paymentId = paymentId;
		this.profile = profile;
		this.creditCardNumber = creditCardNumber;
		this.cvc = cvc;
		this.paymentAmount = paymentAmount;
		this.creditcardNameHolder = creditcardNameHolder;
		this.expirationDate = expirationDate;
	}


	/**
	 * @return the paymentId
	 */
	public int getPaymentId() {
		return paymentId;
	}


	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}


	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}


	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	/**
	 * @return the creditCardNumber
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}


	/**
	 * @param creditCardNumber the creditCardNumber to set
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}


	/**
	 * @return the cvc
	 */
	public int getCvc() {
		return cvc;
	}


	/**
	 * @param cvc the cvc to set
	 */
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}


	/**
	 * @return the paymentAmount
	 */
	public double getPaymentAmount() {
		return paymentAmount;
	}


	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}


	/**
	 * @return the creditcardNameHolder
	 */
	public String getCreditcardNameHolder() {
		return creditcardNameHolder;
	}


	/**
	 * @param creditcardNameHolder the creditcardNameHolder to set
	 */
	public void setCreditcardNameHolder(String creditcardNameHolder) {
		this.creditcardNameHolder = creditcardNameHolder;
	}


	/**
	 * @return the expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}


	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}


	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", profile=" + profile + ", creditCardNumber=" + creditCardNumber
				+ ", cvc=" + cvc + ", paymentAmount=" + paymentAmount + ", creditcardNameHolder=" + creditcardNameHolder
				+ ", expirationDate=" + expirationDate + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
		result = prime * result + ((creditcardNameHolder == null) ? 0 : creditcardNameHolder.hashCode());
		result = prime * result + cvc;
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(paymentAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + paymentId;
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (creditCardNumber == null) {
			if (other.creditCardNumber != null)
				return false;
		} else if (!creditCardNumber.equals(other.creditCardNumber))
			return false;
		if (creditcardNameHolder == null) {
			if (other.creditcardNameHolder != null)
				return false;
		} else if (!creditcardNameHolder.equals(other.creditcardNameHolder))
			return false;
		if (cvc != other.cvc)
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (Double.doubleToLongBits(paymentAmount) != Double.doubleToLongBits(other.paymentAmount))
			return false;
		if (paymentId != other.paymentId)
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}
	
	
}
