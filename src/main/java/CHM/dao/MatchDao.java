package CHM.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import CHM.model.Match;
import CHM.model.Profile;

public interface MatchDao {
	
	public int insertMatch(Match match) throws HibernateException;
	
	public Match selectMatch(int matchId) throws HibernateException;
	
	public List<Match> selectAllMatches() throws HibernateException;
	
	public List<Match> selectMatchesByProfile(Profile profile, List<Profile> profileList) throws HibernateException;
	
	public Match updateMatch(Match match) throws HibernateException;
	
	public boolean deleteMatch(Match match) throws Exception;
	
	public void setSessionFactory(SessionFactory sessionFactory);

}
