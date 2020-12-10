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
public class Interests {
	
	//Long list of interests
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interests_id")
	private int interestsId;
	
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
