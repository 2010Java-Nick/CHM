package CHM.service;

import org.springframework.stereotype.Service;

import CHM.model.User;

@Service
public interface AuthService {
	
	public String authenticateUser(String username, String password);
	
	public String createToken(User user);
	
	public Boolean validateToken(String token);

}
