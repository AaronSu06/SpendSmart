package Model;

import java.util.Map;

public class UserPieIncome {

	// instance variables
	String username;
	private Map<String, Double> pieLabel;

	public UserPieIncome(String username, Map<String, Double> pieLabel) {
		super();
		this.username = username;
		this.pieLabel = pieLabel;
	}

	public Map<String, Double> getPieLabel() {
		return pieLabel;
	}

	public void setPieLabel(Map<String, Double> pieLabel) {
		this.pieLabel = pieLabel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserPieInfo [username=" + username + ", pieLabel=" + pieLabel + "]";
	}
}
