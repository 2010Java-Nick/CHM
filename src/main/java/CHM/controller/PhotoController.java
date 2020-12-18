package CHM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.Photo;
import CHM.service.PhotoServiceImpl;

@RestController(value = "/photo")
public class PhotoController {
	
	PhotoServiceImpl photoService;

	/**
	 * @param photoService the photoService to set
	 */
	@Autowired
	public void setPhotoService(PhotoServiceImpl photoService) {
		this.photoService = photoService;
	}
	
	@PostMapping
	public ResponseEntity<Integer> createPhoto(@RequestBody Photo photo) {
		
		Integer photoId = photoService.createPhoto(photo);
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(photoId, HttpStatus.BAD_REQUEST);
		if (photoId != -1) {
			re = new ResponseEntity<Integer>(photoId, HttpStatus.OK);
		}
		return re;
	}
	
	@GetMapping(path = "/{photoId}")
	public ResponseEntity<Photo> readPhotoById(@PathVariable("photoId")int photoId) {
		
		Photo photo = photoService.readPhotoById(photoId);
		ResponseEntity<Photo> re = new ResponseEntity<Photo>(photo, photo == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@GetMapping(path = "/profile/{profileId}")
	public ResponseEntity<List<Photo>> readPhotosByProfile(@PathVariable("profileId")int profileId) {
		
		List<Photo> photoList = photoService.readPhotosByProfileId(profileId);
		ResponseEntity<List<Photo>> re = new ResponseEntity<List<Photo>>(photoList, photoList.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@PatchMapping
	public ResponseEntity<Photo> updatePhoto(@RequestBody Photo photo) {
		
		Photo updatedPhoto = photoService.updatePhoto(photo);
		ResponseEntity<Photo> re = new ResponseEntity<Photo>(updatedPhoto, HttpStatus.BAD_REQUEST);
		if (updatedPhoto != null) {
			re = new ResponseEntity<Photo>(updatedPhoto, HttpStatus.OK);
		}
		return re;
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deletePhoto(@RequestBody Photo photo){
		
		Boolean deleted = photoService.deletePhoto(photo);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}

}
