/* This class acts as the main class for all of the logic and visual events
 * firing in the Login and Signup Panels. It also acts as the holder for the
 * rest of the application, as in declaring the dashboard controller when the 
 * user logs in, leading to the dashboard panels.
 */

package Controller;

import Model.DataModel;
import Model.UserAccount;
import View.LoginPanel;
import View.PlaceholderTextField;
import View.SignupPanel;
import View.TodayExpensePanel;
import View.TotalExpensePanel;
import View.TotalIncomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

public class LoginController implements ActionListener, FocusListener {

	// instance variables
	private static LoginPanel loginPanel = new LoginPanel();
	private SignupPanel signupPanel = new SignupPanel();

	private PlaceholderTextField usernameL = loginPanel.getUsername();
	private PlaceholderTextField passwordL = loginPanel.getPassword();

	private PlaceholderTextField usernameS = signupPanel.getUsername();
	private PlaceholderTextField passwordS = signupPanel.getPassword();
	private PlaceholderTextField confirmPasswordS = signupPanel.getConfirm();

	private SidePanelController sidePanel;
	private DashboardController dashboard;
	private SurveyController survey;

	private TotalExpensePanel totalExpense;
	private TodayExpensePanel todayExpense;
	private TotalIncomePanel totalIncome;

	private boolean incorrectInfo = false;
	private static String username = null;

	private boolean first = true;

	// constructor to add all the event listeners for all necessary components in
	// the Login and Signup Panels
	public LoginController() {
		// initially disable
		signupPanel.setVisible(false);

		// add action listeners / mouse listeners
		usernameL.addFocusListener(this);
		passwordL.addFocusListener(this);
		loginPanel.getLogin().addActionListener(this);
		loginPanel.getSignup().addActionListener(this);

		usernameS.addFocusListener(this);
		passwordS.addFocusListener(this);
		confirmPasswordS.addFocusListener(this);
		signupPanel.getSignup().addActionListener(this);
		signupPanel.getLogin().addActionListener(this);
	}

	// getters and setters
	public static String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isIncorrectInfo() {
		return incorrectInfo;
	}

	public void setIncorrectInfo(boolean incorrectInfo) {
		this.incorrectInfo = incorrectInfo;
	}

	public DashboardController getDashboard() {
		return dashboard;
	}

	public void setDashboard(DashboardController dashboard) {
		this.dashboard = dashboard;
	}

	public static LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public static void setLoginPanel(LoginPanel loginPanel) {
		LoginController.loginPanel = loginPanel;
	}

	public SignupPanel getSignupPanel() {
		return signupPanel;
	}

	public void setSignupPanel(SignupPanel signupPanel) {
		this.signupPanel = signupPanel;
	}

	public SidePanelController getSidePanel() {
		return sidePanel;
	}

	public void setSidePanel(SidePanelController sidePanel) {
		this.sidePanel = sidePanel;
	}

	public SurveyController getSurvey() {
		return survey;
	}

	public void setSurvey(SurveyController survey) {
		this.survey = survey;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// action events for logging in / signing up
		if (e.getSource() == loginPanel.getLogin()) {

			// check if the credentials match an existing account
			List<UserAccount> userInfoList = null;
			userInfoList = getUserInfo(userInfoList);

			// if the data exists
			if (userInfoList != null && userInfoList.size() > 0) {

				// loop through the list and compare values
				for (UserAccount userInfo : userInfoList) {
					if (usernameL.getText().equals(userInfo.getUsername())
							&& passwordL.getText().equals(userInfo.getPassword())) {

						// create an account with the username and password
						JOptionPane.showMessageDialog(null, "User has logged in!");
						incorrectInfo = true;

						// disable / enable specifics (the reason we have a display pie method and a
						// getPiePanel method is because at the start, username is null)
						username = usernameL.getText();
						resetText();
						loginPanel.setVisible(false);

						// declare important variables
						sidePanel = new SidePanelController();

						dashboard = new DashboardController();
						totalExpense = dashboard.getDashboardPanel().getTotalExpense();
						todayExpense = dashboard.getDashboardPanel().getTodayExpense();
						totalIncome = dashboard.getDashboardPanel().getTotalIncome();

						survey = new SurveyController();

						// add new instance to the frame (if the user has logged out and wants to log
						// back in) and then update the information
						if (!first) {
							Application.mainFrame.add(dashboard.getDashboardPanel());
							totalExpense.displayPieNew();
							todayExpense.displayPieNew();
							totalIncome.displayPieNew();
						}

						else {
							totalExpense.displayPie();
							todayExpense.displayPie();
							totalIncome.displayPie();
						}

						todayExpense.declareTotalExpense();
						dashboard.updateGUI();
						dashboard.getDashboardPanel().setVisible(true);

						first = false;
						break;
					}

					else {
						incorrectInfo = false;
					}
				}
			}

			// display a popup if the information is incorrect
			if (!incorrectInfo) {
				JOptionPane.showMessageDialog(null, "Incorrect username and/or password!");
			}
		}

		else if (e.getSource() == signupPanel.getSignup()) {

			// create an account with the username and password
			if (validInfo()) {
				// if valid, write to the .json file by calling the generateUserAccount method
				// in the DataModel class
				try {
					DataModel.generateUserAccount(usernameS.getText(), passwordS.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "User has signed up!");
				resetText();
				signupPanel.setVisible(false);
				loginPanel.setVisible(true);
			}
		}

		// action event for swapping tabs
		else if (e.getSource() == loginPanel.getSignup()) {
			resetText();
			loginPanel.setVisible(false);
			signupPanel.setVisible(true);
		}

		else if (e.getSource() == signupPanel.getLogin()) {
			resetText();
			signupPanel.setVisible(false);
			loginPanel.setVisible(true);
		}
	}

	@Override
	// focus events for the textfields
	public void focusGained(FocusEvent e) {
		// When the text field is just being typed in:
		if (e.getComponent() == usernameL) {
			if (usernameL.getPlaceholder().equals("Username")) {
				usernameL.setPlaceholder("");
			}
		}

		else if (e.getComponent() == passwordL) {
			if (passwordL.getPlaceholder().equals("Password")) {
				passwordL.setPlaceholder("");
			}
		}

		else if (e.getComponent() == usernameS) {
			if (usernameS.getPlaceholder().equals("Username")) {
				usernameS.setPlaceholder("");
			}
		}

		else if (e.getComponent() == passwordS) {
			if (passwordS.getPlaceholder().equals("Password")) {
				passwordS.setPlaceholder("");
			}
		}

		else if (e.getComponent() == confirmPasswordS) {
			if (confirmPasswordS.getPlaceholder().equals("Re-Enter Password")) {
				confirmPasswordS.setPlaceholder("");
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// If the text is empty, reset the filler
		if (e.getComponent() == usernameL) {
			if (usernameL.getText().trim().isEmpty()) {
				usernameL.setPlaceholder("Username");
			}
		}

		else if (e.getComponent() == passwordL) {
			if (passwordL.getText().trim().isEmpty()) {
				passwordL.setPlaceholder("Password");
			}
		}

		else if (e.getComponent() == usernameS) {
			if (usernameS.getText().trim().isEmpty()) {
				usernameS.setPlaceholder("Username");
			}
		}

		else if (e.getComponent() == passwordS) {
			if (passwordS.getText().trim().isEmpty()) {
				passwordS.setPlaceholder("Password");
			}
		}

		else if (e.getComponent() == confirmPasswordS) {
			if (confirmPasswordS.getText().trim().isEmpty()) {
				confirmPasswordS.setPlaceholder("Re-Enter Password");
			}
		}
	}

	// limitations behind the signup (such as passwords must match, no space in
	// username, etc.)
	public boolean validInfo() {
		for (int i = 0; i < usernameS.getText().length(); i++) {
			if (Character.isWhitespace(usernameS.getText().charAt(i))) {
				JOptionPane.showMessageDialog(signupPanel, "Username Must Not Contain Spaces! Please Try Again.");
				return false;
			}
		}

		if (!confirmPasswordS.getText().equals(passwordS.getText())) {
			JOptionPane.showMessageDialog(signupPanel, "Password Does Not Match! Please Try Again.");
			return false;

		} else if (usernameS.getText().length() <= 3) {
			JOptionPane.showMessageDialog(signupPanel, "Username Must Be Greater Than 3 Characters! Please Try Again.");
			return false;
		}

		List<UserAccount> userInfoList = null;
		userInfoList = getUserInfo(userInfoList);

		// if not null, as in a file exists, check if the username and password used to
		// login match any of that in the file
		if (userInfoList != null && userInfoList.size() > 0) {

			for (UserAccount userInfo : userInfoList) {
				if (usernameS.getText().equals(userInfo.getUsername())) {
					JOptionPane.showMessageDialog(signupPanel, "Username Is Already Taken! Please Try Again.");
					return false;
				}
			}
		}
		return true;
	}

	// method will fetch the data for the user information regarding their username
	// and password
	public List<UserAccount> getUserInfo(List<UserAccount> userInfoList) {
		// get the user information stored in the .json file so that we don't overwrite
		// it (if it exists)
		try {
			userInfoList = DataModel.getUserAccounts();
			return userInfoList;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	// reset text for all JTextFields
	public void resetText() {
		usernameL.setText("");
		usernameL.setPlaceholder("Username");
		passwordL.setText("");
		passwordL.setPlaceholder("Password");
		usernameS.setText("");
		usernameS.setPlaceholder("Username");
		passwordS.setText("");
		passwordS.setPlaceholder("Password");
		confirmPasswordS.setText("");
		confirmPasswordS.setPlaceholder("Re-Enter Password");
	}

}
