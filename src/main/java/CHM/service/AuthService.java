package CHM.service;

import org.springframework.stereotype.Service;

import CHM.model.LoginDto;
import CHM.model.User;

@Service
public interface AuthService {
	
	public String authenticateUser(LoginDto loginDto);
	
	public String createToken(User user, Boolean remembered);
	
	public Boolean validateToken(String token);

}
