package CHM.util;

import org.apache.commons.validator.routines.EmailValidator;

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
	
	// https://www.journaldev.com/641/regular-expression-phone-number-validation-in-java
	public static boolean validatePhoneNumber(String phoneNo) {
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
		return isValidEmail(profile.getEmail()) && verifyAge(profile.getAge()) && validatePhoneNumber(profile.getPhone());
	}
	
}
