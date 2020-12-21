package CHM.service;

import org.springframework.stereotype.Service;

import CHM.model.LoginDto;
import CHM.model.User;
import io.jsonwebtoken.Claims;

@Service
public interface AuthService {
	
	public String authenticateUser(LoginDto loginDto);
	
	public String createToken(User user, Boolean remembered);
	
	public Boolean validateToken(String token);
	
	public int profileIdFromToken(String token);
	
	public Claims decodeJWT(String jwt);
}
