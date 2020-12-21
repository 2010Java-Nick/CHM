package CHM.service;

import java.util.List;

import CHM.dao.MatchDao;
import CHM.dao.ProfileDao;
import CHM.model.Match;

public interface MatchService {
	
	public int createMatch(Match match);
	
	public Match readMatchById(int matchId);
	
	public List<Match> readAllMatches();
	
	public List<Match> readPotentialMatchesByProfileId(int profileId);
	
	public List<Match> readMatchesByProfileId(int profileId);
	
	public Match updateMatch(Match match);
	
	public boolean deleteMatch(Match match);
	
	public void setMatchDao(MatchDao matchDao);

	void setProfileDao(ProfileDao profileDao);

}
