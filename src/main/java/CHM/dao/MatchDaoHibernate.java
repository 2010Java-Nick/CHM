package CHM.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import CHM.model.Match;

@Repository
public class MatchDaoHibernate implements MatchDao {

	SessionFactory sessionFactory;
	
	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
	
	@Override
	public int insertMatch(Match match) throws HibernateException {
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(match);
		tx.commit();
		sess.close();
		return match.getMatchId();
	}

	@Override
	public Match selectMatch(int matchId) throws HibernateException {
		Match match;
		Session sess = sessionFactory.openSession();
		match = sess.get(Match.class, matchId);
		sess.close();
		return match;
	}

	@Override
	public List<Match> selectAllMatches() throws HibernateException {
		List<Match> matchList = null;
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Match> cq = cb.createQuery(Match.class);
		Root<Match> rootEntry = cq.from(Match.class);
		CriteriaQuery<Match> all = cq.select(rootEntry);
		TypedQuery<Match> allQuery = sess.createQuery(all);
		matchList = allQuery.getResultList();
		sess.close();
		
		return matchList;
	}

	@Override
	public Match updateMatch(Match match) throws HibernateException {
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.update(match);
		tx.commit();
		sess.close();
		return match;
	}

	@Override
	public boolean deleteMatch(Match match) throws Exception {
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(match);
		tx.commit();
		sess.close();
		return true;
	}

}
