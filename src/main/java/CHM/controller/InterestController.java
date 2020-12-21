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

import CHM.model.Interest;
import CHM.service.AuthService;
import CHM.service.InterestServiceImpl;

@RestController
public class InterestController {
	
	InterestServiceImpl interestService;
	
	AuthService authService;
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * @param interestService the interestService to set
	 */
	@Autowired
	public void setInterestService(InterestServiceImpl interestService) {
		this.interestService = interestService;
	}
	
	@RequestMapping(path = "/interest", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<Integer> createInterest(@RequestBody String interest, HttpServletRequest request) {
		
		System.out.println(interest);
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Interest newInterest = new Interest(interest, profileId);
		
		Integer interestId = interestService.createInterest(newInterest);
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(interestId, HttpStatus.BAD_REQUEST);
		if (interestId != -1) {
			re = new ResponseEntity<Integer>(interestId, HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(path = "/interest/{id}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Interest> readInterestById(@PathVariable(name = "id")int interestId, HttpServletRequest request) {
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		
		Interest interest = interestService.readInterestById(interestId);
		ResponseEntity<Interest> re = new ResponseEntity<Interest>(interest, interest == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/interest", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<Interest>> readAllInterests(HttpServletRequest request) {
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		
		List<Interest> interestList = interestService.readAllInterests();
		ResponseEntity<List<Interest>> re = new ResponseEntity<List<Interest>>(interestList, interestList.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(path = "/interest/profile/{id}", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<Interest>> readInterestsByProfileId(@PathVariable(name = "id")int profileId, HttpServletRequest request) {
		int profile_Id = authService.profileIdFromToken(request.getHeader("auth").toString());
		
		List<Interest> interestList = interestService.readInterestsByProfileId(profileId);
		ResponseEntity<List<Interest>> re = new ResponseEntity<List<Interest>>(interestList, interestList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	
	@RequestMapping(path = "/interest", method = RequestMethod.PATCH)
	@CrossOrigin
	public ResponseEntity<Interest> updateInterest(@RequestBody Interest interest, HttpServletRequest request) {
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		
		Interest updatedInterest = interestService.updateInterest(interest);
		ResponseEntity<Interest> re = new ResponseEntity<Interest>(updatedInterest, HttpStatus.BAD_REQUEST);
		if (updatedInterest != null) {
			re = new ResponseEntity<Interest>(updatedInterest, HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(path = "/interest", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<Boolean> deleteInterest(@RequestBody Interest interest, HttpServletRequest request){
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		
		Boolean deleted = interestService.deleteInterest(interest);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}
	

}
