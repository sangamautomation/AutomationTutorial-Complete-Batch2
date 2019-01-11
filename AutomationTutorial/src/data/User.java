package data;

/**
 * Description : All Data Level  Functions
 * 
 * @author Sangam
 */

public class User
{
	
	private String cwUsername;
	private String cwPassword;
	
	public User(String cwUsername, String cwPassword) {
		
		this.cwUsername = cwUsername;
		this.cwPassword = cwPassword;
	}

	public String getCwUsername() {
		return cwUsername;
	}

	public void setCwUsername(String cwUsername) {
		this.cwUsername = cwUsername;
	}

	public String getCwPassword() {
		return cwPassword;
	}

	public void setCwPassword(String cwPassword) {
		this.cwPassword = cwPassword;
	}
	
}

