package CHM.controller;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.LoginDto;
import CHM.service.AuthService;

@RestController
public class AuthController {
	
	AuthService authService;
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@RequestMapping(path = "/login", produces = "text/plain", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Cookie> login(@RequestBody LoginDto loginDto) {
		
		System.out.println("Hit login endpoint");
		
		ResponseEntity<Cookie> re = new ResponseEntity<Cookie>(null, HttpStatus.UNAUTHORIZED);
		String token = authService.authenticateUser(loginDto.getUsername(), loginDto.getPassword(), loginDto.isRememberMe());
		Cookie cookie = new Cookie("token", token);
		if(token != null) {
			
			re = new ResponseEntity<Cookie>(cookie, HttpStatus.OK);
		}

		return re;
		
	}
	
}
