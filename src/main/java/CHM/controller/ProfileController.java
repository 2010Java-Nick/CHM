package CHM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CHM.model.Profile;
import CHM.service.ProfileServiceImpl;

@RestController
public class ProfileController {
	
	ProfileServiceImpl profileService;
	
	/**
	 * @param profileService the profileService to set
	 */
	@Autowired
	public void setProfileService(ProfileServiceImpl profileService) {
		this.profileService = profileService;
	}

	@RequestMapping(path = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
		
		Profile newProfile = profileService.createProfile(profile);
		
		ResponseEntity<Profile> re = new ResponseEntity<Profile>(newProfile, HttpStatus.CREATED);

		return re;
		
	}

}
