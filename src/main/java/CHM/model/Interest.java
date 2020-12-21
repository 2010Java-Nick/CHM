/**
 * 
 */
package CHM.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Work From Home
 *
 */
@Entity
@Table(name = "Interest")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "interestId")
public class Interest {
	
	//Long list of interests
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interest_id")
	private int interestId;
	
	//@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Column(name = "profile_id")
	private int profileId;
		
	@Column(name = "interest_name")
	private String interestName;

	/**
	 * 
	 */
	public Interest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param interestId
	 * @param profileId
	 * @param interestName
	 */
	public Interest(int interestId, int profileId, String interestName) {
		super();
		this.interestId = interestId;
		this.profileId = profileId;
		this.interestName = interestName;
	}
	
	public Interest(String interestName, int profileId) {
		super();
		this.profileId = profileId;
		this.interestName = interestName;
	}

	/**
	 * @return the interestId
	 */
	public int getInterestId() {
		return interestId;
	}

	/**
	 * @param interestId the interestId to set
	 */
	public void setInterestId(int interestId) {
		this.interestId = interestId;
	}

	/**
	 * @return the profileId
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	/**
	 * @return the interestName
	 */
	public String getInterestName() {
		return interestName;
	}

	/**
	 * @param interestName the interestName to set
	 */
	public void setInterestName(String interestName) {
		this.interestName = interestName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + interestId;
		result = prime * result + ((interestName == null) ? 0 : interestName.hashCode());
		result = prime * result + profileId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interest other = (Interest) obj;
		if (interestId != other.interestId)
			return false;
		if (interestName == null) {
			if (other.interestName != null)
				return false;
		} else if (!interestName.equals(other.interestName))
			return false;
		if (profileId != other.profileId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interest [interestId=" + interestId + ", profileId=" + profileId + ", interestName=" + interestName
				+ "]";
	}

	
	
}
