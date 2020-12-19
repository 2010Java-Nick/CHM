package CHM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.Interest;
import CHM.service.InterestServiceImpl;

@RestController
public class InterestController {
	
	InterestServiceImpl interestService;

	/**
	 * @param interestService the interestService to set
	 */
	@Autowired
	public void setInterestService(InterestServiceImpl interestService) {
		this.interestService = interestService;
	}
	
	@RequestMapping(path = "/interest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> createInterest(@RequestBody Interest interest) {
		
		Integer interestId = interestService.createInterest(interest);
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(interestId, HttpStatus.BAD_REQUEST);
		if (interestId != -1) {
			re = new ResponseEntity<Integer>(interestId, HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(path = "/interest/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Interest> readInterestById(@PathVariable(name = "id")int interestId) {
		
		Interest interest = interestService.readInterestById(interestId);
		ResponseEntity<Interest> re = new ResponseEntity<Interest>(interest, interest == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/interest", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Interest>> readAllInterests() {
		
		List<Interest> interestList = interestService.readAllInterests();
		ResponseEntity<List<Interest>> re = new ResponseEntity<List<Interest>>(interestList, interestList.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	
	@RequestMapping(path = "/interest", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<Interest> updateInterest(@RequestBody Interest interest) {
		
		Interest updatedInterest = interestService.updateInterest(interest);
		ResponseEntity<Interest> re = new ResponseEntity<Interest>(updatedInterest, HttpStatus.BAD_REQUEST);
		if (updatedInterest != null) {
			re = new ResponseEntity<Interest>(updatedInterest, HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(path = "/interest", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteInterest(@RequestBody Interest interest){
		
		Boolean deleted = interestService.deleteInterest(interest);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}
	

}