package CHM.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.security.auth.login.FailedLoginException;

import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import CHM.dao.UserDao;
import CHM.dao.UserDaoHibernate;
import CHM.model.User;

@Service
public class AuthServiceJWT implements AuthService {
	
	UserDao userDao;
	
	@Autowired
	@Qualifier(value = "userDao")
	public void setUserDao(UserDaoHibernate userDao) {
		this.userDao = userDao;
	}

	@Override
	public String authenticateUser(String username, String password) {
		// if good, get user from db
		User user = userDao.selectUser(username);
		
		//if not null, check password
		if(user != null) {
			if(user.getPassword() == password) {
				return createToken(user);
			}
		}
		
		return null;
	}

	@Override
	public String createToken(User user) {
		
		Integer id = user.getUserId();
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		try {
			
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    String token = JWT.create()
		    	.withIssuedAt(now)
		    	.withIssuer(id.toString())
		    	.withClaim("username", user.getUsername())
		    	.sign(algorithm);
		    return token;
		    
		} catch (JWTCreationException exception){
			
		    //Invalid Signing configuration / Couldn't convert Claims.
			return null;
			
		}
		
	}

	@Override
	public Boolean validateToken(String token) {
		
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    return true;
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			return false;
		}

	}

}
