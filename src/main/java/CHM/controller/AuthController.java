package CHM.controller;

import javax.security.auth.login.FailedLoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.User;
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
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
		
		System.out.println("Hit login endpoint");
		
		ResponseEntity<String> re = new ResponseEntity<String>(new String("Failed Login"), HttpStatus.UNAUTHORIZED);
		String token = authService.authenticateUser(loginDto.getUsername(), loginDto.getPassword());
		
		if(token != null) {
			re = new ResponseEntity<String>(token, HttpStatus.OK);
		}

		return re;
		
	}
	
}
