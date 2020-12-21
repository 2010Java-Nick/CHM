package CHM.service;

import org.springframework.stereotype.Service;

import CHM.model.User;

@Service
public interface AuthService {
	
	public String authenticateUser(String username, String password, Boolean remembered);
	
	public String createToken(User user, Boolean remembered);
	
	public Boolean validateToken(String token);
	
	public int profileIdFromToken(String token);
}
