package CHM.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "matchId")
public class Match {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_id")
    private int matchId;

    @ManyToOne
    @Column(name = "user1_id")
    private int user1Id;

    @ManyToOne
    @Column(name = "user2_id")
    private int user2Id;

    @Column(name = "blocked")
    private boolean blocked;
    
    @Column(name = "compatability")
    private double compatability;

    @Column(name = "matched")
    private boolean matched;
    

    public Match() {
    }

    /**
     * 
     * @param matchId
     * @param user1Id
     * @param user2Id
     * @param blocked
     * @param compatability
     * @param matched
     */
    public Match(int matchId, int user1Id, int user2Id, boolean blocked, double compatability, boolean matched) {
        this.matchId = matchId;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.blocked = blocked;
        this.compatability = compatability;
        this.matched = matched;
    }

    /**
     * 
     * @return matchId
     */
    public int getMatchId() {
        return this.matchId;
    }

    /**
     * 
     * @param matchId
     */
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    /**
     * 
     * @return user1Id
     */
    public int getUser1Id() {
        return this.user1Id;
    }

    /**
     * 
     * @param user1Id
     */
    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    /**
     * 
     * @return user2Id
     */
    public int getUser2Id() {
        return this.user2Id;
    }

    /**
     * 
     * @param user2Id
     */
    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }

    /**
     * 
     * @return boolean
     */
    public boolean isBlocked() {
        return this.blocked;
    }

    /**
     * 
     * @return boolean
     */
    public boolean getBlocked() {
        return this.blocked;
    }

    /**
     * 
     * @param blocked
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * 
     * @return compatability
     */
    public double getCompatability() {
        return this.compatability;
    }

    /**
     * 
     * @param compatability
     */
    public void setCompatability(double compatability) {
        this.compatability = compatability;
    }

    /**
     * 
     * @return boolean
     */
    public boolean isMatched() {
        return this.matched;
    }

    /**
     * 
     * @return boolean
     */
    public boolean getMatched() {
        return this.matched;
    }

    /**
     * 
     * @param matched
     */
    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    /**
     * 
     * @param matchId
     * @return matchId
     */
    public Match matchId(int matchId) {
        this.matchId = matchId;
        return this;
    }

    /**
     * 
     * @param user1Id
     * @return user1Id
     */
    public Match user1Id(int user1Id) {
        this.user1Id = user1Id;
        return this;
    }

    /**
     * 
     * @param user2Id
     * @return user2Id
     */
    public Match user2Id(int user2Id) {
        this.user2Id = user2Id;
        return this;
    }

    /**
     * 
     * @param blocked
     * @return boolean
     */
    public Match blocked(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    /**
     * 
     * @param compatability
     * @return compatability
     */
    public Match compatability(double compatability) {
        this.compatability = compatability;
        return this;
    }

    /**
     * 
     * @param matched
     * @return boolean
     */
    public Match matched(boolean matched) {
        this.matched = matched;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Match)) {
            return false;
        }
        Match match = (Match) o;
        return matchId == match.matchId && user1Id == match.user1Id && user2Id == match.user2Id && blocked == match.blocked && compatability == match.compatability && matched == match.matched;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, user1Id, user2Id, blocked, compatability, matched);
    }

    @Override
    public String toString() {
        return "{" +
            " matchId='" + getMatchId() + "'" +
            ", user1_id='" + getUser1Id() + "'" +
            ", user2_id='" + getUser2Id() + "'" +
            ", blocked='" + isBlocked() + "'" +
            ", compatability='" + getCompatability() + "'" +
            ", matched='" + isMatched() + "'" +
            "}";
    }
    
}
