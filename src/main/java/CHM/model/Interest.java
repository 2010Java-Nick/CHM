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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "profile_id")
	private Profile profile;
		
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
	 * @param profile
	 * @param interestName
	 */
	public Interest(int interestId, Profile profile, String interestName) {
		super();
		this.interestId = interestId;
		this.profile = profile;
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
	public String toString() {
		return "Interest [interestId=" + interestId + ", profile=" + profile + ", interestName=" + interestName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + interestId;
		result = prime * result + ((interestName == null) ? 0 : interestName.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}
	
	public boolean sameInterest(Interest i) {
		return this.getInterestName().equals(i.getInterestName());
	}
	
}
