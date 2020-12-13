package CHM.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photo_id")
	private int photoId;
	
	@Column(name = "photo")
	private byte[] photo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private Profile profile;

	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param photo
	 * @param user
	 */
	public Photo(byte[] photo, User user) {
		super();
		this.photo = photo;
		this.profile = profile;
	}

	/**
	 * @return the photoId
	 */
	public int getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	
	
}
