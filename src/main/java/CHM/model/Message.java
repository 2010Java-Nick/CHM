/**
 * 
 */
package CHM.model;

import java.time.LocalDateTime;
import static CHM.util.HelperFunctions.localDateTimeOfString;

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
 * @author Zachary Leonardo
 *
 */
@Entity
@Table(name = "Message")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "messageId")
public class Message implements Comparable<Message> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private int messageId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "match_id")
	private Match match;
	
	@Column(name = "sender_id")
	private int senderId;
	
	@Column(name = "receiver_id")
	private int receiverId;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "timestamp")
	private String timestamp;

	/**
	 * 
	 */
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param messageId
	 * @param match
	 * @param senderId
	 * @param receiverId
	 * @param message
	 * @param timestamp
	 */
	public Message(int messageId, Match match, int senderId, int receiverId, String message, String timestamp) {
		super();
		this.messageId = messageId;
		this.match = match;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
		this.timestamp = timestamp;
	}



	/**
	 * @return the messageId
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}


	/**
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}

	/**
	 * @param match the match to set
	 */
	public void setMatch(Match match) {
		this.match = match;
	}

	/**
	 * @return the senderId
	 */
	public int getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the receiverId
	 */
	public int getReceiverId() {
		return receiverId;
	}

	/**
	 * @param receiverId the receiverId to set
	 */
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", match=" + match + ", senderId=" + senderId + ", receiverId="
				+ receiverId + ", message=" + message + ", timestamp=" + timestamp + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((match == null) ? 0 : match.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + messageId;
		result = prime * result + receiverId;
		result = prime * result + senderId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		Message other = (Message) obj;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageId != other.messageId)
			return false;
		if (receiverId != other.receiverId)
			return false;
		if (senderId != other.senderId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public int compareTo(Message m) {
		return localDateTimeOfString(this.getTimestamp()).compareTo(localDateTimeOfString(m.getTimestamp()));
	}
}
