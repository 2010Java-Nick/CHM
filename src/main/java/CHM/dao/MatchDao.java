package CHM.dao;

import java.util.List;

public interface MatchDao {
	
	public int insertProfile(Match match);
	
	public Match selectProfile(int matchInt);
	
	public List<Match> selectAllProfiles();
	
	public Match updateUser(int matchId, Match match);
	
	public boolean deleteProfile(Match match);

}
