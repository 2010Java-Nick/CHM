package CHM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import CHM.dao.MatchDao;
import CHM.dao.MatchDaoHibernate;
import CHM.dao.ProfileDao;
import CHM.dao.ProfileDaoHibernate;
import CHM.model.Message;
import CHM.model.Profile;

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
	
	public static boolean isValidMessage(Message message) {
		
		try {
			SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
			MatchDao matchDao = new MatchDaoHibernate();
			ProfileDao profileDao = new ProfileDaoHibernate();
			matchDao.setSessionFactory(sessionFactory);
			profileDao.setSessionFactory(sessionFactory);
			matchDao.selectMatch(message.getMatch().getMatchId());
			System.out.println("This is the profile: " + profileDao.selectProfile(message.getReceiverId()));
			profileDao.selectProfile(message.getSenderId());
			return isValidLocalDateTimeString(message.getTimestamp()) && message.getMatch().isMatched() && !message.getMatch().isBlocked();
			//return isValidLocalDateTimeString(message.getTimestamp());
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
