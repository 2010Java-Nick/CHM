package CHM.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import CHM.dao.UserDao;
import CHM.dao.UserDaoHibernate;
import CHM.model.LoginDto;
import CHM.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthServiceJWT implements AuthService {
	
	UserDao userDao;
	
	@Autowired
	@Qualifier(value = "userDao")
	public void setUserDao(UserDaoHibernate userDao) {
		this.userDao = userDao;
	}

	@Override
	public String authenticateUser(LoginDto loginDto) {
		// if good, get user from db
		User user = userDao.selectUser(loginDto.getUsername());
		
		//if not null, check password
		if(user != null) {
			System.out.println("the user is not null");
			if(user.getPassword().equals(loginDto.getPassword())) {
				String token = createToken(user, loginDto.getRememberMe());
				loginDto.setProfileId(user.getProfile().getProfileId());
				System.out.println(token);
				return token;
			}
		}
		
		return null;
	}

	@Override	
	public String createToken(User user, Boolean remembered) {

		//The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    Integer id = user.getUserId();
	    
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secret");
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder()
	    		.setId(id.toString())
	    		.setSubject(user.getUsername())
	            .setIssuedAt(now)
	            .claim("profileId", user.getProfile().getProfileId())
	            .claim("premium", user.isPremium())
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	    if (!remembered) {
	        long expMillis = nowMillis + 24 * 60 * 60 * 1000;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}

	@Override
	public Boolean validateToken(String token) {
		
		try {
			
			Claims claims = Jwts.parser()
		            .setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
		            .parseClaimsJws(token).getBody();
		    return true;
		} catch (MalformedJwtException exception){
		    //Invalid signature/claims
			return false;
		}

	}
	
	@Override
	public int profileIdFromToken(String token) {
		
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
                .parseClaimsJws(token).getBody();
        
        return (int)claims.get("profileId");
	}
	
	public Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}

}
