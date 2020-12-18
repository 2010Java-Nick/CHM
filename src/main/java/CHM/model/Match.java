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
     * @author Group 3
     * 
     */
@Entity
@Table(name = "Match")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "matchId")
public class Match {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_id")
    private int matchId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile1_id")
    private Profile profile1;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile2_id")
    private Profile profile2;

    @Column(name = "blocked")
    private boolean blocked;
    
    @Column(name = "compatability")
    private double compatability;

    @Column(name = "matched")
    private boolean matched;

	/**
	 * 
	 */
	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param matchId
	 * @param profile1
	 * @param profile2
	 * @param blocked
	 * @param compatability
	 * @param matched
	 */
	public Match(int matchId, Profile profile1, Profile profile2, boolean blocked, double compatability,
			boolean matched) {
		super();
		this.matchId = matchId;
		this.profile1 = profile1;
		this.profile2 = profile2;
		this.blocked = blocked;
		this.compatability = compatability;
		this.matched = matched;
	}


	/**
	 * @return the matchId
	 */
	public int getMatchId() {
		return matchId;
	}


	/**
	 * @param matchId the matchId to set
	 */
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}


	/**
	 * @return the profile1
	 */
	public Profile getProfile1() {
		return profile1;
	}


	/**
	 * @param profile1 the profile1 to set
	 */
	public void setProfile1(Profile profile1) {
		this.profile1 = profile1;
	}


	/**
	 * @return the profile2
	 */
	public Profile getProfile2() {
		return profile2;
	}


	/**
	 * @param profile2 the profile2 to set
	 */
	public void setProfile2(Profile profile2) {
		this.profile2 = profile2;
	}


	/**
	 * @return the blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}


	/**
	 * @param blocked the blocked to set
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}


	/**
	 * @return the compatability
	 */
	public double getCompatability() {
		return compatability;
	}


	/**
	 * @param compatability the compatability to set
	 */
	public void setCompatability(double compatability) {
		this.compatability = compatability;
	}


	/**
	 * @return the matched
	 */
	public boolean isMatched() {
		return matched;
	}


	/**
	 * @param matched the matched to set
	 */
	public void setMatched(boolean matched) {
		this.matched = matched;
	}


	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", profile1=" + profile1 + ", profile2=" + profile2 + ", blocked="
				+ blocked + ", compatability=" + compatability + ", matched=" + matched + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (blocked ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(compatability);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + matchId;
		result = prime * result + (matched ? 1231 : 1237);
		result = prime * result + ((profile1 == null) ? 0 : profile1.hashCode());
		result = prime * result + ((profile2 == null) ? 0 : profile2.hashCode());
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
		Match other = (Match) obj;
		if (blocked != other.blocked)
			return false;
		if (Double.doubleToLongBits(compatability) != Double.doubleToLongBits(other.compatability))
			return false;
		if (matchId != other.matchId)
			return false;
		if (matched != other.matched)
			return false;
		if (profile1 == null) {
			if (other.profile1 != null)
				return false;
		} else if (!profile1.equals(other.profile1))
			return false;
		if (profile2 == null) {
			if (other.profile2 != null)
				return false;
		} else if (!profile2.equals(other.profile2))
			return false;
		return true;
	}

    
    
}
