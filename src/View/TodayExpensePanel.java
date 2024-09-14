/* This class will be used to display all the GUI components for the today expenses.
 * This includes the today expense pie chart and the moneyPanels.
 */

package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.DashboardController;
import Controller.LoginController;
import Model.DataModel;
import Model.UserPieToday;

public class TodayExpensePanel extends JPanel implements ActionListener {

	// instance variables
	private DataModel data = new DataModel();
	private double[] userFundsInfo;
	private Map<String, Double> todayPieInfo = new HashMap<String, Double>();
	private LocalDate date;

	private static String[] btnTextArr = { "Living", "Transportation", "Recreational", "Other" };
	private static AddMoneyPanel moneyPanel = new AddMoneyPanel("Add Expenses", btnTextArr);
	private JTextField amount = moneyPanel.getAmount();
	private JButton addBtn = moneyPanel.getAddBtn();
	private JButton switchBtn = new JButton();
	private static JButton selectedBtn = moneyPanel.getSelectedBtn();
	private MoneyOptionPanel optionPanel = moneyPanel.getOptionPanel();
	private JLabel moneyHeader = moneyPanel.getHeader();

	private PiePanel piePanel = new PiePanel("Todays Expenses", null);
	private TotalExpensePanel totalExpense;

	private static boolean addMoney = true;

	// constructor to set the positions of the JPanel and objects
	public TodayExpensePanel() {
		// set preferences for the switchBtn
		switchBtn.setIcon((new ImageIcon(
				new ImageIcon("Images/SwapArrow.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))));
		switchBtn.setBorderPainted(false);
		switchBtn.setContentAreaFilled(false);
		switchBtn.setFocusPainted(false);

		// set size and position of GUI components
		setLayout(null);
		moneyPanel.setBounds(761, 205, 450, 455);
		switchBtn.setBounds(776, 225, 40, 40);

		add(switchBtn);
		add(moneyPanel);

		// set panel invisible
		setOpaque(false);

		// add action listeners
		addBtn.addActionListener(this);
		switchBtn.addActionListener(this);

		for (int i = 0; i < btnTextArr.length; i++) {
			optionPanel.getBtnArr()[i].addActionListener(this);
		}
	}

	// getters and setters
	public PiePanel getPiePanel() {
		return piePanel;
	}

	public static AddMoneyPanel getMoneyPanel() {
		return moneyPanel;
	}

	public static void setMoneyPanel(AddMoneyPanel moneyPanel) {
		TodayExpensePanel.moneyPanel = moneyPanel;
	}

	public void setPiePanel(PiePanel piePanel) {
		this.piePanel = piePanel;
	}

	public static JButton getSelectedBtn() {
		return selectedBtn;
	}

	public static void setSelectedBtn(JButton selectedBtn) {
		TodayExpensePanel.selectedBtn = selectedBtn;
	}

	public static boolean isAddMoney() {
		return addMoney;
	}

	public static void setAddMoney(boolean addMoney) {
		TodayExpensePanel.addMoney = addMoney;
	}
	
	// declare variable after the instance is created
	public void declareTotalExpense() {
		totalExpense = DashboardController.getDashboardPanel().getTotalExpense();
	}

	// fetch the data for the 3D pie graph
	public List<UserPieToday> getInfo() {
		// fetch the data
		List<UserPieToday> userPieList = null;

		try {
			userPieList = data.getTodayPie();
			return userPieList;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// method to display the pie graphs
	public void displayPie() {

		// initialize variables / get the user information by calling the getInfo method
		List<UserPieToday> userPieList = getInfo();
		boolean hasInfo = false;

		// update the value for the current date
		date = LocalDate.now();

		// display the information (if it exists)
		if (userPieList != null && userPieList.size() > 0) {
			for (UserPieToday userInfo : userPieList) {

				// check if the user has existing information and if the dates are the same
				if (LoginController.getUsername().equals(userInfo.getUsername())
						&& date.toString().equals(userInfo.getDate())) {
					piePanel = new PiePanel("Todays Expenses", userInfo.getPieLabel());
					piePanel.displayChart();

					hasInfo = true;

					break;
				}

			}
		}

		// otherwise display a panel with a message to display no text
		else {
			piePanel = new PiePanel("Todays Expenses", null);
			piePanel.displayChart();
			piePanel.addNoInfo();

			hasInfo = true;

			// get the information for the general information
			userFundsInfo = DashboardController.getUserFundsInfo();

			// reset all information (because the dates do not match or some other error)
			todayPieInfo.clear();
			userFundsInfo[1] = 0;

			// save the changes
			saveInfo();
		}

		if (!hasInfo) {
			// if there is no information of that user, display a panel with a message to
			// display no text
			piePanel = new PiePanel("Todays Expenses", null);
			piePanel.displayChart();
			piePanel.addNoInfo();

			// get the information for the general information
			userFundsInfo = DashboardController.getUserFundsInfo();

			// reset all information (because the dates do not match or some other error)
			todayPieInfo.clear();
			userFundsInfo[1] = 0;

			// save the changes
			saveInfo();
		}

		piePanel.setBounds(277, 237, 396, 390);
		add(piePanel);
	}

	// this method will be used for when the user logs out, then logs back in again.
	// It will display the pie graphs by repainting them
	public void displayPieNew() {

		// initialize variables / get the user information by calling the getInfo method
		List<UserPieToday> userPieList = getInfo();
		boolean hasInfo = false;

		// update the value for the current date
		date = LocalDate.now();

		// update the information (if it exists)
		if (userPieList != null && userPieList.size() > 0) {
			for (UserPieToday userInfo : userPieList) {

				if (LoginController.getUsername().equals(userInfo.getUsername())
						&& date.toString().equals(userInfo.getDate())) {
					piePanel.getPlot().setDataset(piePanel.fillData(userInfo.getPieLabel()));
					piePanel.getChartPanel().repaint();

					hasInfo = true;

					break;
				}
			}
		}

		// otherwise, update with nothing
		else {
			piePanel.getPlot().setDataset(piePanel.fillData(null));
			piePanel.addNoInfo();
			piePanel.getChartPanel().repaint();

			hasInfo = true;

			// get the information for the general information
			userFundsInfo = DashboardController.getUserFundsInfo();

			// reset all information (because the dates do not match or some other error)
			todayPieInfo.clear();
			userFundsInfo[1] = 0;

			// save the changes
			saveInfo();
		}

		if (!hasInfo) {
			// if there is no information of that user, update with nothing
			piePanel.getPlot().setDataset(piePanel.fillData(null));
			piePanel.addNoInfo();
			piePanel.getChartPanel().repaint();

			// get the information for the general information
			userFundsInfo = DashboardController.getUserFundsInfo();

			// reset all information (because the dates do not match or some other error)
			todayPieInfo.clear();
			userFundsInfo[1] = 0;

			// save the changes
			saveInfo();
		}
	}

	// method for all the implementation of the action commands
	@Override
	public void actionPerformed(ActionEvent e) {
		// save the info and update the pie chart
		if (e.getSource() == addBtn) {

			// get the information for the general information
			userFundsInfo = DashboardController.getUserFundsInfo();

			// update the value for the current date
			date = LocalDate.now();

			// check if it can be parsed as a double and is greater than / equal to 0
			if (isValid(amount.getText()) && Double.parseDouble(amount.getText()) >= 0) {
				
				// call the event method in the TotalExpensePanel
				totalExpense.totalExpenseEvent(selectedBtn.getText(), Double.valueOf(amount.getText()), addMoney);

				// fetch the data
				List<UserPieToday> userPieList = null;
				try {
					userPieList = data.getTodayPie();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// update values in the file (if it exists)
				if (userPieList != null && userPieList.size() > 0) {

					// call the getInfo method to do so
					getInfo(userPieList);
				}

				// create a new instance of the map and save the data (this will only run the
				// first time)
				else {

					if (isValidRemove()) {
						// create a new value in the map
						todayPieInfo.put(selectedBtn.getText(), Double.parseDouble(amount.getText()));

						// update the accumulated total
						updateFunds();
					}

					else {
						JOptionPane.showMessageDialog(null, "Invalid input! You cannot remove more than you own!");
					}
				}

				// call the saveInfo method to save new information
				saveInfo();

				// reset text and then display a message (if possible)
				if (addMoney) {
					JOptionPane.showMessageDialog(null,
							String.format("$%.2f has been added!", Double.parseDouble(amount.getText())));
				}
				
				// reset text
				amount.setText("Amount ($)");

				// update the GUI
				DashboardController.updateGUI();

				// remove text from graph
				piePanel.getChartPanel().remove(piePanel.getNoInfoText());

				// display / update the pie graph
				piePanel.getPlot().setDataset(piePanel.fillData(todayPieInfo));
				piePanel.getChartPanel().repaint();
			}

			else {
				JOptionPane.showMessageDialog(null, "Invalid input for Amount ($)");
			}
		}

		// if the switch button is pressed
		else if (e.getSource() == switchBtn) {

			// change to remove expense
			if (addMoney) {
				moneyHeader.setText("Remove Expenses");
				moneyHeader.setBounds(100, 17, (int) moneyHeader.getPreferredSize().getWidth(), 45);

				addMoney = false;
			}

			// change to add expense
			else {
				moneyHeader.setText("Add Expenses");
				moneyHeader.setBounds(135, 17, (int) moneyHeader.getPreferredSize().getWidth(), 45);

				addMoney = true;
			}
		}

		// create action events for swapping between the types of income / expense
		for (int i = 0; i < btnTextArr.length; i++) {
			if (e.getSource() == optionPanel.getBtnArr()[i]) {
				selectedBtn.setForeground(new Color(140, 140, 140));
				optionPanel.getBtnArr()[i].setForeground(new Color(224, 225, 221));
				selectedBtn = optionPanel.getBtnArr()[i];
			}
		}
	}

	// method will change the information dynamically
	public void getInfo(List<UserPieToday> userPieList) {
		for (UserPieToday userInfo : userPieList) {

			// if information already exists
			if (LoginController.getUsername().equals(userInfo.getUsername())) {

				// if the dates are not matching, reset everything (new day meaning new
				// information)
				if (!date.toString().equals(userInfo.getDate())) {
					todayPieInfo.clear();
					piePanel.getChartPanel().add(piePanel.getNoInfoText());
					piePanel.getPlot().setDataset(piePanel.fillData(null));
					piePanel.addNoInfo();
					piePanel.getChartPanel().repaint();
					userFundsInfo[1] = 0;

					// exit out of the loop
					break;
				}

				todayPieInfo = userInfo.getPieLabel();

				// add onto the old key value (if it exists)
				if (todayPieInfo.get(selectedBtn.getText()) != null) {
					// store the new value in a temporary variable, remove the old key and add the
					// new one in with updated value
					double tempVal = todayPieInfo.get(selectedBtn.getText());

					if (addMoney) {
						tempVal += Double.parseDouble(amount.getText());

						// increment the funds
						updateFunds();
					}

					else {

						if (isValidRemove()) {
							tempVal -= Double.parseDouble(amount.getText());

							// increment the funds
							updateFunds();

							JOptionPane.showMessageDialog(null,
									String.format("$%.2f has been removed!", Double.parseDouble(amount.getText())));
						}

						else {
							JOptionPane.showMessageDialog(null, "Invalid input! You cannot remove more than you own!");
						}
					}

					todayPieInfo.remove(selectedBtn.getText());
					todayPieInfo.put(selectedBtn.getText(), tempVal);
				}

				// if there is no information, create it
				else {

					if (addMoney) {

						// create a new value in the map
						todayPieInfo.put(selectedBtn.getText(), Double.parseDouble(amount.getText()));

						// update the accumulated total
						updateFunds();
					}

					else {
						if (isValidRemove()) {
							// create a new value in the map
							todayPieInfo.put(selectedBtn.getText(), Double.parseDouble(amount.getText()));

							// update the accumulated total
							updateFunds();
						}

						else {
							JOptionPane.showMessageDialog(null, "Invalid input! You cannot remove more than you own!");
						}
					}
				}
			}
		}
	}

	// method to determine if the string can be parsed as a double
	public boolean isValid(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// method will call the corresponding methods in the DataModel class to update
	// saved information
	public void saveInfo() {
		// save new information
		try {
			DataModel.generateTodayPie(LoginController.getUsername(), todayPieInfo, date.toString());
			DataModel.generateUserInfo(LoginController.getUsername(), userFundsInfo[0], userFundsInfo[1],
					userFundsInfo[2]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// method used to dynamically update the funds from the userFundsInfo array
	public void updateFunds() {
		// update the accumulated total
		if (addMoney) {
			userFundsInfo[1] += Double.parseDouble(amount.getText());
			userFundsInfo[0] += Double.parseDouble(amount.getText());
		}

		// if the user wants to remove the amount
		else {

			// update the accumulated total
			userFundsInfo[1] -= Double.parseDouble(amount.getText());
			userFundsInfo[0] -= Double.parseDouble(amount.getText());

			// check if the amount is less than 0... if so, reset to 0
			if (userFundsInfo[1] < 0) {
				userFundsInfo[1] = 0;
			}

			if (userFundsInfo[0] < 0) {
				userFundsInfo[0] = 0;
			}
		}
	}

	// method will check if the information can be properly removed (as in it
	// doesn't result in a negative balance)
	public boolean isValidRemove() {

		if (todayPieInfo.isEmpty() || todayPieInfo == null) {
			return false;
		}

		// loop through the pieInfoMap
		for (Map.Entry<String, Double> map : todayPieInfo.entrySet()) {

			// get the corresponding key
			if (map.getKey().equals(selectedBtn.getText())) {
				// if there is no information, return false
				if (map.getValue() == null) {
					return false;
				}

				// if the information in map minus the information we want to subtract is less
				// than 0
				if ((map.getValue() - Double.parseDouble(amount.getText())) < 0) {

					return false;
				}
			}
		}

		return true;
	}
}
