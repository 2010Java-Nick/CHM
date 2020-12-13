package CHM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import CHM.dao.PaymentDao;
import CHM.dao.PhotoDao;
import CHM.model.Photo;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	PhotoDao photoDao;
	
	PhotoService photoService;
	
	@Autowired
	@Qualifier(value = "photoDao")
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	@Override
	public boolean uploadPhoto(Photo p) {
		
		return photoDao.insertPhoto(p);
	}

	@Override
	public List<Photo> displayPhotoByUserId(int userId) {
		
		return photoDao.readPhotoByUserId(userId);
	}

	@Override
	public boolean deletePhotoById(int photoId) {
		return photoDao.deletePhotoById(photoId);
	}

	@Override
	public boolean updatePhoto(Photo p) {
		return photoDao.updatePhoto(p);
	}
	
	
	
	
}
