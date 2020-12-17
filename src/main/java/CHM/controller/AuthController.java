package CHM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import CHM.service.AuthService;

@RestController
public class AuthController {
	
	AuthService authService;
	
	@Autowired
	@Qualifier(value = "authService")
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@RequestMapping(path = "/login", produces = "text/plain", method = RequestMethod.POST)
	public String login() {
		
		System.out.println("Hit login endpoint");
		
		authService.authenticateUser(username, password);

		return "you logged in!";
		
	}
	
}
