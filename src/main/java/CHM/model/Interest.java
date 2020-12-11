/**
 * 
 */
package CHM.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Work From Home
 *
 */
public class Interest {
	
	//Long list of interests
	//TODO set this up as a single interest and decide on what info needs to be here
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interest_id")
	private int interestId;
	
	@Column(name = "theater")
	private boolean theater;
	
	@Column(name = "cooking")
	private boolean cooking;
	
	@Column(name = "fitness")
	private boolean fitness;
	
	@Column(name = "politics")
	private boolean politics;
	
	@Column(name = "technology")
	private boolean technology;
	
}
