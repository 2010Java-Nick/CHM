package CHM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.Photo;
import CHM.service.PhotoServiceImpl;

@RestController
public class PhotoController {
	
	PhotoServiceImpl photoService;

	/**
	 * @param photoService the photoService to set
	 */
	@Autowired
	public void setPhotoService(PhotoServiceImpl photoService) {
		this.photoService = photoService;
	}
	
	@RequestMapping(path = "/photo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> createPhoto(@RequestBody Photo photo) {
		
		Integer photoId = photoService.createPhoto(photo);
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(photoId, HttpStatus.BAD_REQUEST);
		if (photoId != -1) {
			re = new ResponseEntity<Integer>(photoId, HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(path = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Photo> readPhotoById(@PathVariable(name = "id")int photoId) {
		
		Interest interest = interestService.readInterestById(interestId);
		ResponseEntity<Interest> re = new ResponseEntity<Interest>(interest, interest == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/photo/profile/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Interest>> readPhotosByProfile() {
		
		List<Interest> interestList = interestService.readAllInterests();
		ResponseEntity<List<Interest>> re = new ResponseEntity<List<Interest>>(interestList, interestList.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	
	@RequestMapping(path = "/interest", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<Interest> updatePhoto(@RequestBody Interest interest) {
		
		Interest updatedInterest = interestService.updateInterest(interest);
		ResponseEntity<Interest> re = new ResponseEntity<Interest>(updatedInterest, HttpStatus.BAD_REQUEST);
		if (updatedInterest != null) {
			re = new ResponseEntity<Interest>(updatedInterest, HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(path = "/interest", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deletePhoto(@RequestBody Interest interest){
		
		Boolean deleted = interestService.deleteInterest(interest);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}

}
