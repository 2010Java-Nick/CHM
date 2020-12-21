package CHM.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import CHM.model.Photo;
import CHM.service.AuthService;
import CHM.service.PhotoServiceImpl;

@RestController
public class PhotoController {
	
	PhotoServiceImpl photoService;
	
	AuthService authService;
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * @param photoService the photoService to set
	 */
	@Autowired
	@CrossOrigin
	public void setPhotoService(PhotoServiceImpl photoService) {
		this.photoService = photoService;
	}
	
	@CrossOrigin
	@PostMapping(path = "/photo")
	public ResponseEntity<Integer> createPhoto(@RequestBody Photo photo, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		System.out.println(photo.toString());
		Integer photoId = photoService.createPhoto(photo);
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(photoId, HttpStatus.BAD_REQUEST);
		if (photoId != -1) {
			re = new ResponseEntity<Integer>(photoId, HttpStatus.OK);
		}
		return re;
	}
	
	@CrossOrigin
	@GetMapping(path = "/photo/{photoId}")
	public ResponseEntity<Photo> readPhotoById(@PathVariable("photoId")int photoId, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Photo photo = photoService.readPhotoById(photoId);
		ResponseEntity<Photo> re = new ResponseEntity<Photo>(photo, photo == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@GetMapping(path = "/photo/profile/{profileId}")
	@CrossOrigin
	public ResponseEntity<List<Photo>> readPhotosByProfile(@PathVariable("profileId")int profileId, HttpServletRequest request) {
		
		int profile_Id = authService.profileIdFromToken(request.getHeader("auth").toString());
		List<Photo> photoList = photoService.readPhotosByProfileId(profileId);
		ResponseEntity<List<Photo>> re = new ResponseEntity<List<Photo>>(photoList, photoList.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@CrossOrigin
	@PostMapping(path = "/photo/{photoId}")
	public ResponseEntity<Photo> updatePhoto(@PathVariable("photoId")int photoId, @RequestParam("photo") MultipartFile photo, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Photo toUpdate = photoService.readPhotoById(photoId);
		ResponseEntity<Photo> re = new ResponseEntity<Photo>(toUpdate, HttpStatus.BAD_REQUEST); 
		
		try {
			if(!photo.isEmpty()) {
				
				byte[] bytes = photo.getBytes();
				toUpdate.setPhoto(bytes);
				Photo updatedPhoto = photoService.updatePhoto(toUpdate);
				re = new ResponseEntity<Photo>(updatedPhoto, HttpStatus.OK);
			}
		} catch (Exception e) {
			return re;
		}	
		return re;
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/photo")
	public ResponseEntity<Boolean> deletePhoto(@RequestBody Photo photo, HttpServletRequest request){
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Boolean deleted = photoService.deletePhoto(photo);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}

}
