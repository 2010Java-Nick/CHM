package CHM.service;

import java.util.List;

import CHM.dao.PhotoDao;
import CHM.model.Photo;

public interface PhotoService {
	
	public void setPhotoDao(PhotoDao photoDao);
	
	public int createPhoto(Photo photo);
	
	public Photo readPhotoById(int photoId);
	
	public List<Photo> readPhotosByProfileId(int profileId);
	
	public Photo updatePhoto(Photo photo);
	
	public boolean deletePhoto(Photo photo);
}
