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
	
	@RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Profile> readProfileById(@PathVariable(name = "id")int profileId) {
		
		Profile profile = profileService.readProfileById(profileId);
		ResponseEntity<Profile> re = new ResponseEntity<Profile>(profile, profile == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Profile>> readAllProfiles() {
		
		List<Profile> profileList = profileService.readAllProfiles();
		ResponseEntity<List<Profile>> re = new ResponseEntity<List<Profile>>(profileList, profileList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(path = "/profile", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
		
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
	
	@RequestMapping(path = "/profile", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteProfile(@RequestBody Profile profile){
		
		Boolean deleted = profileService.deleteProfile(profile);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}

}
