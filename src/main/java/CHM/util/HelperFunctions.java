package CHM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import CHM.dao.MatchDao;
import CHM.dao.MatchDaoHibernate;
import CHM.dao.ProfileDao;
import CHM.dao.ProfileDaoHibernate;
import CHM.model.Interest;
import CHM.model.Message;
import CHM.model.Payment;
import CHM.model.Profile;
import CHM.service.InterestService;
import CHM.service.InterestServiceImpl;

public class HelperFunctions {
	
	public static boolean isValidEmail(String email) {
	
       EmailValidator validator = EmailValidator.getInstance();

       return validator.isValid(email);
       
	}
	
	public static boolean verifyAge(int age) {
		int MIN_AGE = 18;
		return age >= MIN_AGE;
	}
	
	public static boolean verifyId(int id) {
		return id > 0;
	}
	
	// https://www.journaldev.com/641/regular-expression-phone-number-validation-in-java
	public static boolean validatePhoneNumber(String phoneNo) {
		if (phoneNo == null) return false;
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")) return true;
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		//return false if nothing matches the input
		else return false;
	}
	
	public static boolean validateProfile(Profile profile) {
		return isValidEmail(profile.getEmail()) && verifyAge(profile.getAge()) && validatePhoneNumber(profile.getPhone()) && verifyId(profile.getProfileId());
	}
	
	public static LocalDateTime localDateTimeOfString(String timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);
		return dateTime;
	}
	
	public static boolean isValidLocalDateTimeString(String timestamp) {
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime.parse(timestamp, formatter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isValidExpString(String timestamp) {
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
			LocalDateTime.parse(timestamp, formatter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isValidMessage(Message message) {
		
		try {
			SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
			MatchDao matchDao = new MatchDaoHibernate();
			ProfileDao profileDao = new ProfileDaoHibernate();
			matchDao.setSessionFactory(sessionFactory);
			profileDao.setSessionFactory(sessionFactory);
			matchDao.selectMatch(message.getMatch().getMatchId());
			profileDao.selectProfile(message.getSenderId());
			return isValidLocalDateTimeString(message.getTimestamp()) && message.getMatch().isMatched() && !message.getMatch().isBlocked();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	// https://www.tutorialspoint.com/java-program-for-credit-card-number-validation
	// Return true if the card number is valid
	   public static boolean validitychk(long cnumber) {
	      return (thesize(cnumber) >= 13 && thesize(cnumber) <= 16) && (prefixmatch(cnumber, 4)
	         || prefixmatch(cnumber, 5) || prefixmatch(cnumber, 37) || prefixmatch(cnumber, 6))
	         && ((sumdoubleeven(cnumber) + sumodd(cnumber)) % 10 == 0);
	   }
	   // Get the result from Step 2
	   public static int sumdoubleeven(long cnumber) {
	      int sum = 0;
	      String num = cnumber + "";
	      for (int i = thesize(cnumber) - 2; i >= 0; i -= 2)
	         sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
	      return sum;
	   }
	   // Return this cnumber if it is a single digit, otherwise,
	   // return the sum of the two digits
	   public static int getDigit(int cnumber) {
	      if (cnumber < 9)
	         return cnumber;
	      return cnumber / 10 + cnumber % 10;
	   }
	   // Return sum of odd-place digits in cnumber
	   public static int sumodd(long cnumber) {
	      int sum = 0;
	      String num = cnumber + "";
	      for (int i = thesize(cnumber) - 1; i >= 0; i -= 2)
	         sum += Integer.parseInt(num.charAt(i) + "");
	      return sum;
	   }
	   // Return true if the digit d is a prefix for cnumber
	   public static boolean prefixmatch(long cnumber, int d) {
	      return getprefx(cnumber, thesize(d)) == d;
	   }
	   // Return the number of digits in d
	   public static int thesize(long d) {
	      String num = d + "";
	      return num.length();
	   }
	   // Return the first k number of digits from
	   // number. If the number of digits in number
	   // is less than k, return number.
	   public static long getprefx(long cnumber, int k) {
	      if (thesize(cnumber) > k) {
	         String num = cnumber + "";
	         return Long.parseLong(num.substring(0, k));
	      }
	      return cnumber;
	   }
	   
	   public static boolean isValidCreditCardNum(String num) {
		   try {
			   Long.valueOf(num);
		   } catch (Exception e) {
			   return false;
		   }
		   return validitychk(Long.valueOf(num));
	   }
	   
	   public static boolean isValidCVC(int cvc) {
		   return String.valueOf(cvc).length() == 3;
	   }
	   
	   public static boolean isValidPayment(Payment payment) {
			
			try {
				SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
				ProfileDao profileDao = new ProfileDaoHibernate();
				profileDao.setSessionFactory(sessionFactory);
				profileDao.selectProfile(payment.getProfile().getProfileId());
				return isValidExpString(payment.getExpirationDate()) && isValidCVC(payment.getCvc()) && isValidCreditCardNum(payment.getCreditCardNumber()) && payment.getPaymentAmount() >= 0;
			} catch (Exception e) {
				return false;
			}
		}
	   
	   public static double computeCompatability(Profile p1, Profile p2) {
		   InterestService interestService = new InterestServiceImpl();
		   List<Interest> p1Interests = interestService.readInterestsByProfileId(p1.getProfileId());
		   List<Interest> p2Interests = interestService.readInterestsByProfileId(p2.getProfileId());
		   
		   double ctr = 0;
		   for (int i = 0; i < p1Interests.size(); i++) {
			   for (int j = 0; j < p2Interests.size(); j++) {
				   if (p1Interests.get(i).sameInterest(p2Interests.get(j))) {
					   ctr += 1;
				   }
			   }
		   }
		   return ctr / (double) (p1Interests.size() + p2Interests.size());
		   
	   }
	   
	   
}
