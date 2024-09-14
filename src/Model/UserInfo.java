package Model;

public class UserInfo {

	// instance variables
	private String username;
	private double totalExpense;
	private double todayExpense;
	private double totalIncome;

	public UserInfo(String username, double totalExpense, double todayExpense, double totalIncome) {
		super();
		this.username = username;
		this.totalExpense = totalExpense;
		this.todayExpense = todayExpense;
		this.totalIncome = totalIncome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public double getTodayExpense() {
		return todayExpense;
	}

	public void setTodayExpense(double todayExpense) {
		this.todayExpense = todayExpense;
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", totalExpense=" + totalExpense + ", todayExpense=" + todayExpense
				+ ", totalIncome=" + totalIncome + "]";
	}

}
