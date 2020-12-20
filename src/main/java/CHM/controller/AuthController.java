package CHM.controller;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
		
		System.out.println("Hit login endpoint");
		System.out.println("LoginDto: " + loginDto.toString());
		
		ResponseEntity<LoginDto> re = new ResponseEntity<LoginDto>(loginDto, HttpStatus.FORBIDDEN);
		String token = authService.authenticateUser(loginDto.getUsername(), loginDto.getPassword(), false);
		if(token != null) {
			response.setHeader("access-control-expose-headers", "Token");
			response.setHeader("Token", token);
			re = new ResponseEntity<LoginDto>(loginDto, HttpStatus.OK);
		}

		return re;
		
	}
	
}
