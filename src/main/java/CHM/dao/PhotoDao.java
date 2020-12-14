package CHM.dao;

import java.util.List;

import CHM.model.Photo;

public interface PhotoDao {
	
	public boolean insertPhoto(Photo photo);
	
	public List<Photo> readPhotoByUserId(int userId);
	
	public boolean updatePhoto(Photo photo);
	
	public boolean deletePhotoById(int photoId);
}
