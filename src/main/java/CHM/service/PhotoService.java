package CHM.service;

import java.util.List;

import CHM.dao.PhotoDao;
import CHM.model.Photo;
import CHM.model.Profile;

public interface PhotoService {
	
	public void setPhotoDao(PhotoDao photoDao);
	
	public int createPhoto(Photo photo);
	
	public Photo readPhotoById(int photoId);
	
	public Photo readPhotoByProfileId(Profile profile);
	
	public Photo updatePhoto(Photo photo);
	
	public boolean deletePhoto(Photo photo);
}
