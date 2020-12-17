package CHM.service;

import CHM.model.User;

public interface AuthService {
	
	public String authenticateUser(String username, String password);
	
	public String createToken(User user);
	
	public Boolean validateToken(String token);

}
