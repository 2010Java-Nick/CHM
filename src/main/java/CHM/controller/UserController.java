package CHM.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.User;
import CHM.service.AuthService;
import CHM.service.UserService;

@RestController
public class UserController {

	UserService userService;
	
	AuthService authService;
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@Autowired
	public void setuserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	@CrossOrigin
<<<<<<< HEAD
	public ResponseEntity<User> createUser(@RequestBody User user, HttpServletRequest request) {
=======
	public ResponseEntity<Integer> createUser(@RequestBody User user) {
>>>>>>> 11d528084189684e3cf973804f689b7d5f97d98f
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Integer newuserId;
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(new Integer(-1), HttpStatus.BAD_REQUEST);
		newuserId = new Integer(userService.createUser(user));
		
		if (newuserId != -1) {
			user.setUserId(newuserId);
			re = new ResponseEntity<Integer>(newuserId, HttpStatus.CREATED);
		}
			
		return re;
	}
	
	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<User> readUserById(@PathVariable(name = "id")int userId, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		User user = userService.readUserById(userId);
		ResponseEntity<User> re = new ResponseEntity<User>(user, user == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<User>> readAllUsers(HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		List<User> userList = userService.readAllUsers();
		ResponseEntity<List<User>> re = new ResponseEntity<List<User>>(userList, userList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.PATCH)
	@CrossOrigin
	public ResponseEntity<User> updateuser(@RequestBody User user, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		User updatedUser = null;
		ResponseEntity<User> re = new ResponseEntity<User>(updatedUser, HttpStatus.BAD_REQUEST);
		updatedUser = userService.updateUser(user);
		if (updatedUser != null) {
			re = new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<Boolean> deleteuser(@RequestBody User user, HttpServletRequest request){
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Boolean deleted = userService.deleteUser(user);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}
}
