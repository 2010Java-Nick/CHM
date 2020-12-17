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
import CHM.util.InvalidProfileException;

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

	@SuppressWarnings("finally")
	@RequestMapping(path = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> createProfile(@RequestBody Profile profile) {
		
		Integer newProfileId;
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(new Integer(-1), HttpStatus.BAD_REQUEST);
		try {
			newProfileId = new Integer(profileService.createProfile(profile));
			if (newProfileId != -1) {
				re = new ResponseEntity<Integer>(newProfileId, HttpStatus.CREATED);
			}
		} catch (InvalidProfileException e) {
			e.printStackTrace();
		} finally {
			return re;
		}
	}

}
