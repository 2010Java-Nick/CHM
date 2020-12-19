package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.MatchDao;
import CHM.model.Match;

@Service
public class MatchServiceImpl implements MatchService {

	
	private MatchDao matchDao;
	
	@Autowired
	@Override
	public void setMatchDao(MatchDao matchDao) {
		this.matchDao = matchDao;

	}
	
	@Override
	public int createMatch(Match match) {
		try {
			return matchDao.insertMatch(match);
		} catch (HibernateException e) {
			return -1;
		}
	}

	@Override
	public Match readMatchById(int matchId) {
		try {
			return matchDao.selectMatch(matchId);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public List<Match> readAllMatches() {
		try {
			return matchDao.selectAllMatches();
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public Match updateMatch(Match match) {
		try {
			return matchDao.updateMatch(match);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public boolean deleteMatch(Match match) {
		try {
			return matchDao.deleteMatch(match);
		} catch (Exception e) {
			return false;
		}
	}

}
