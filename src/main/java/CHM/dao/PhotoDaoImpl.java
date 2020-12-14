package CHM.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import CHM.model.Photo;

@Repository(value = "photoDao")
public class PhotoDaoImpl implements PhotoDao {
	
	//@Autowired
	SessionFactory sessionFactory;
	
	//@Autowired
	public PhotoDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean insertPhoto(Photo photo) {
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(photo);
		tx.commit();
		sess.close();
		return false;
	}

	@Override
	public List<Photo> readPhotoByUserId(int userId) {
		
		List<Photo> photoList = null;
		Session sess = sessionFactory.openSession();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Photo> cq = cb.createQuery(Photo.class);
		Root<Photo> rootEntry = cq.from(Photo.class);
		CriteriaQuery<Photo> all = cq.select(rootEntry);
		TypedQuery<Photo> allQuery = sess.createQuery(all);
		photoList = allQuery.getResultList();
		sess.close();
		return photoList;
	}

	@Override
	public boolean updatePhoto(Photo photo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePhotoById(int photoId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
