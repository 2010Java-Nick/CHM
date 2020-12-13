package CHM.service;

import java.util.List;

import CHM.model.Photo;

public interface PhotoService {
	
	public boolean uploadPhoto(Photo p);
	
	public List<Photo> displayPhotoByUserId(int userId);
	
	public boolean deletePhotoById(int photoId);
	
	public boolean updatePhoto(Photo p);
}
