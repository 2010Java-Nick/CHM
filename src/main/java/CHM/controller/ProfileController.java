package CHM.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.Profile;
import CHM.service.AuthService;
import CHM.service.ProfileService;
import CHM.util.InvalidProfileException;

@RestController
public class ProfileController {
	
	ProfileService profileService;
	
	AuthService authService;
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	/**
	 * @param profileService the profileService to set
	 */
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@SuppressWarnings("finally")
	@PostMapping(path = "/profile")
	@CrossOrigin
	public ResponseEntity<Integer> createProfile(@RequestBody Profile profile, HttpServletRequest request,
			HttpServletResponse response) {

		ResponseEntity<Integer> re = new ResponseEntity<Integer>(new Integer(-1), HttpStatus.BAD_REQUEST);
		System.out.println(profile.toString());
		try {
			int newProfileId = new Integer(profileService.createProfile(profile));
			String token = request.getHeader("Auth");
			
			String newToken = authService.updateToken(token, newProfileId);
			
			response.setHeader("access-control-expose-headers", "Token");
			response.setHeader("Token", newToken);
			
			if (newProfileId != -1) {
				re = new ResponseEntity<Integer>(newProfileId, HttpStatus.CREATED);
			}
		} catch (InvalidProfileException e) {
			e.printStackTrace();
		} finally {
			return re;
		}
	}
	
	@GetMapping(path = "/profile/{id}")
	@CrossOrigin
	public ResponseEntity<Profile> readProfileById(@PathVariable(name = "id")int profileId, HttpServletRequest request) {
		
		int profile_Id = authService.profileIdFromToken(request.getHeader("auth").toString());
		Profile profile = profileService.readProfileById(profileId);
		ResponseEntity<Profile> re = new ResponseEntity<Profile>(profile, profile == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@GetMapping(path = "/profile")
	@CrossOrigin
	public ResponseEntity<List<Profile>> readAllProfiles(HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		List<Profile> profileList = profileService.readAllProfiles();
		ResponseEntity<List<Profile>> re = new ResponseEntity<List<Profile>>(profileList, profileList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@SuppressWarnings("finally")
	@PatchMapping(path = "/profile")
	@CrossOrigin
	public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile, HttpServletRequest request) {
		
		int profileId = authService.profileIdFromToken(request.getHeader("auth").toString());
		Profile updatedProfile = null;
		ResponseEntity<Profile> re = new ResponseEntity<Profile>(updatedProfile, HttpStatus.BAD_REQUEST);
		try {
			updatedProfile = profileService.updateProfile(profile);
			if (updatedProfile != null) {
				re = new ResponseEntity<Profile>(updatedProfile, HttpStatus.OK);
			}
		} catch (InvalidProfileException e) {
			e.printStackTrace();
		} finally {
			return re;
		}
		
	}
	
	@DeleteMapping(path = "/profile/{id}")
	@CrossOrigin
	public ResponseEntity<Boolean> deleteProfile(@PathVariable("id")int profileId, HttpServletRequest request){
		
		int profile_Id = authService.profileIdFromToken(request.getHeader("auth").toString());
		Boolean deleted = profileService.deleteProfile(profileId);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}

}
