package CHM.dao;

import java.util.List;

import org.hibernate.HibernateException;

import CHM.model.Photo;

public interface PhotoDao {
	
	public int insertPhoto(Photo photo) throws HibernateException;
	
	public Photo selectPhoto(int photoId) throws HibernateException;
	
	public List<Photo> selectAllPhotos() throws HibernateException;
	
	public Photo updatePhoto(Photo photo) throws HibernateException;
	
	public boolean deletePhoto(Photo photo) throws Exception;
	
}
