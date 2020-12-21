package CHM.model;

public class LoginDto {
	
	private String username;
	
	private String password;
	
	private Boolean rememberMe;
	
	private int profileId;

	/**
	 * 
	 */
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param rememberMe
	 */
	public LoginDto(String username, String password, Boolean rememberMe) {
		super();
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
		this.profileId = -1;
	}
	
	
	/**
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @param profileId
	 */
	public LoginDto(String username, String password, Boolean rememberMe, int profileId) {
		super();
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
		this.profileId = profileId;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the rememberMe
	 */
	public Boolean getRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe the rememberMe to set
	 */
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rememberMe == null) ? 0 : rememberMe.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		LoginDto other = (LoginDto) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rememberMe == null) {
			if (other.rememberMe != null)
				return false;
		} else if (!rememberMe.equals(other.rememberMe))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginDto [username=" + username + ", password=" + password + ", rememberMe=" + rememberMe + "]";
	}
	
	
}
