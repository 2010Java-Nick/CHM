package CHM.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.MatchDao;
import CHM.dao.ProfileDao;
import CHM.model.Match;
import CHM.model.Profile;

@Service
public class MatchServiceImpl implements MatchService {

	
	private MatchDao matchDao;
	
	private ProfileDao profileDao;
	
	@Autowired
	@Override
	public void setMatchDao(MatchDao matchDao) {
		this.matchDao = matchDao;

	}

	@Autowired
	@Override
	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
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

	@Override
	public List<Match> readPotentialMatchesByProfileId(int profileId) {
		System.out.println("in this function");
		List<Profile> profileList = profileDao.selectAllProfiles();
		List<Match> matchList = matchDao.selectMatchesByProfileId(profileId);
		for (int i = 0; i < matchList.size(); i++) {
			profileList.remove(matchList.get(i).getProfile1());
			profileList.remove(matchList.get(i).getProfile2());
		}
		Profile profile = profileDao.selectProfile(profileId);
		return matchDao.selectPotentialMatchesByProfile(profile, profileList);
	}

	@Override
	public List<Match> readMatchesByProfileId(int profileId) {
		List<Match> matchList = matchDao.selectMatchesByProfileId(profileId);
		for (int i = 0; i < matchList.size(); i++) {
			Match match = matchList.get(i);
			if (match.getProfile2().getProfileId() == profileId) {
				Profile p1 = match.getProfile1();
				Profile p2 = match.getProfile2();
				match.setProfile1(p2);
				match.setProfile2(p1);
				matchList.remove(i);
				matchList.add(match);
			}
		}
		return matchDao.selectMatchesByProfileId(profileId);
	}
}
