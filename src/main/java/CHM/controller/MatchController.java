package CHM.controller;

import java.util.List;

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

import CHM.model.Match;
import CHM.service.MatchService;

@RestController
public class MatchController {
	MatchService matchService;
	
	/**
	 * @param MatchService the MatchService to set
	 */
	@Autowired
	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	@RequestMapping(path = "/match", method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Integer> createMatch(@RequestBody Match match) {
		
		Integer newMatchId;
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(new Integer(-1), HttpStatus.BAD_REQUEST);
			newMatchId = new Integer(matchService.createMatch(match));
			if (newMatchId != -1) {
				re = new ResponseEntity<Integer>(newMatchId, HttpStatus.CREATED);
			}
		return re;
	}
	
	@RequestMapping(path = "/match/{id}", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Match> readMatchById(@PathVariable(name = "id")int matchId) {
		
		Match match = matchService.readMatchById(matchId);
		ResponseEntity<Match> re = new ResponseEntity<Match>(match, match == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/match", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<Match>> readAllMatches() {
		
		List<Match> MatchList = matchService.readAllMatches();
		ResponseEntity<List<Match>> re = new ResponseEntity<List<Match>>(MatchList, MatchList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(path = "/match/profile/{id}", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<Match>> readMatchesByProfileId(@PathVariable(name = "id")int profileId) {
		
		List<Match> matchList = matchService.readMatchesByProfileId(profileId);
		ResponseEntity<List<Match>> re = new ResponseEntity<List<Match>>(matchList, matchList == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK); 
		return re;
	}
	
	@RequestMapping(path = "/match", method = RequestMethod.PATCH)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Match> updateMatch(@RequestBody Match match) {
		
		Match updatedMatch = null;
		ResponseEntity<Match> re = new ResponseEntity<Match>(updatedMatch, HttpStatus.BAD_REQUEST);
		updatedMatch = matchService.updateMatch(match);
		if (updatedMatch != null) {
			re = new ResponseEntity<Match>(updatedMatch, HttpStatus.OK);
		}
		return re;
		
	}
	
	@RequestMapping(path = "/match", method = RequestMethod.DELETE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Boolean> deleteMatch(@RequestBody Match match){
		
		Boolean deleted = matchService.deleteMatch(match);
		ResponseEntity<Boolean> re = new ResponseEntity<Boolean>(deleted, deleted ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
		return re;
	}
}
