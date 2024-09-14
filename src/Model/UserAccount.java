package Model;

public class UserAccount {

	// instance variables
	private String username, password;

	// constructor
	public UserAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", password=" + password + "]";
	}
}
