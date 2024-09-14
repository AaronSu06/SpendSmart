package Model;

import java.util.Map;
import java.time.LocalDate;

public class UserPieToday {

	// instance variables
	private String username;
	private Map<String, Double> pieLabel;
	private String date;

	public UserPieToday(String username, Map<String, Double> pieLabel, String date) {
		super();
		this.username = username;
		this.pieLabel = pieLabel;
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Map<String, Double> getPieLabel() {
		return pieLabel;
	}

	public void setPieLabel(Map<String, Double> pieLabel) {
		this.pieLabel = pieLabel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UserPieToday [username=" + username + ", pieLabel=" + pieLabel + ", date=" + date + "]";
	}
}
