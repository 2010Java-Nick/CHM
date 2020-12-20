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

import CHM.model.Photo;

@Repository(value = "photoDao")
public class PhotoDaoHibernate implements PhotoDao {
	
	SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int insertPhoto(Photo photo) throws HibernateException {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(photo);
		tx.commit();
		sess.close();
		return photo.getPhotoId();
		
	}
	
	@Override
	public Photo selectPhoto(int photoId) throws HibernateException {
		
		Photo photo;
		Session sess = sessionFactory.openSession();
		photo = sess.get(Photo.class, photoId);
		sess.close();
		return photo;
		
	}

	@Override
	public List<Photo> selectAllPhotos() {
		
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
	public Photo updatePhoto(Photo photo) throws HibernateException {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.update(photo);
		tx.commit();
		sess.close();
		return photo;
	}

	@Override
	public boolean deletePhoto(Photo photo) throws Exception {
		
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(photo);
		tx.commit();
		sess.close();
		return true;
		
	}
	
}
