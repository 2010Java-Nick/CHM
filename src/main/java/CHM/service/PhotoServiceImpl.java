package CHM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import CHM.dao.PaymentDao;
import CHM.dao.PhotoDao;
import CHM.model.Photo;
import CHM.model.Profile;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	private PhotoDao photoDao;
	
	@Autowired
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	@Override
	public int createPhoto(Photo photo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Photo readPhotoById(int photoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo readPhotoByProfileId(Profile profile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo updatePhoto(Photo photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePhoto(Photo photo) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
