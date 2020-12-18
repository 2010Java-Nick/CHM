package CHM.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CHM.dao.PhotoDao;
import CHM.model.Photo;
import CHM.model.Profile;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	PhotoDao photoDao;
	
	@Autowired
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	@Override
	public int createPhoto(Photo photo) {
		
		try {
			return photoDao.insertPhoto(photo);
		} catch (HibernateException e) {
			return -1;
		}
	}

	@Override
	public Photo readPhotoById(int photoId) {

		try {
			return photoDao.selectPhoto(photoId);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public List<Photo> readPhotosByProfileId(int profileId) {
		
		try {
			List<Photo> photoList = photoDao.selectAllPhotos();
			List<Photo> selectedList = new ArrayList<Photo>();
			for(Photo p : photoList) {
				if(p.getProfile().getProfileId() == profileId) {
					selectedList.add(p);
				}
			}
			return selectedList;
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public Photo updatePhoto(Photo photo) {
		
		try {
			return photoDao.updatePhoto(photo);
		} catch (HibernateException e) {
			return null;
		}
	}

	@Override
	public boolean deletePhoto(Photo photo) {

		try {
			return photoDao.deletePhoto(photo);
		} catch (Exception e) {
			return false;
		}
	}
	
}
