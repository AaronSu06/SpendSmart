/* This class will be used to display all the GUI components for the total income.
 * This includes the income pie chart and the moneyPanels.
 */

package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import Model.UserPieIncome;

public class TotalIncomePanel extends JPanel implements ActionListener {

	// instance variables
	private DataModel data = new DataModel();
	private double[] userFundsInfo;
	private Map<String, Double> incomePieInfo = new HashMap<String, Double>();

	private String[] btnTextArr = { "Investments", "Allowance", "Job", "Other" };
	private AddMoneyPanel moneyPanel = new AddMoneyPanel("Add Income", btnTextArr);
	private JTextField amount = moneyPanel.getAmount();
	private JButton addBtn = moneyPanel.getAddBtn();
	private JButton switchBtn = new JButton();
	private JButton selectedBtn = moneyPanel.getSelectedBtn();
	private MoneyOptionPanel optionPanel = moneyPanel.getOptionPanel();
	private JLabel moneyHeader = moneyPanel.getHeader();

	private PiePanel piePanel = new PiePanel("Total Income", null);

	private boolean addMoney = true;

	// constructor to set the positions of the JPanel and objects
	public TotalIncomePanel() {
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
	public AddMoneyPanel getMoneyPanel() {
		return moneyPanel;
	}

	public void setMoneyPanel(AddMoneyPanel moneyPanel) {
		this.moneyPanel = moneyPanel;
	}

	public PiePanel getPiePanel() {
		return piePanel;
	}

	public void setPiePanel(PiePanel piePanel) {
		this.piePanel = piePanel;
	}

	// fetch the data for the 3D pie graph
	public List<UserPieIncome> getInfo() {
		// fetch the data
		List<UserPieIncome> userPieList = null;

		try {
			userPieList = data.getIncomePie();
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
		List<UserPieIncome> userPieList = getInfo();
		boolean hasInfo = false;

		// display the information (if it exists)
		if (userPieList != null && userPieList.size() > 0) {
			for (UserPieIncome userInfo : userPieList) {

				if (LoginController.getUsername().equals(userInfo.getUsername())) {
					piePanel = new PiePanel("Total Income", userInfo.getPieLabel());
					piePanel.displayChart();

					hasInfo = true;

					break;
				}

			}
		}

		// otherwise display a panel with a message to display no text
		else {
			piePanel = new PiePanel("Total Income", null);
			piePanel.displayChart();
			piePanel.addNoInfo();

			hasInfo = true;
		}

		if (!hasInfo) {
			// if there is no information of that user, display a panel with a message to
			// display no text
			piePanel = new PiePanel("Total Income", null);
			piePanel.displayChart();
			piePanel.addNoInfo();
		}

		piePanel.setBounds(277, 237, 396, 390);
		add(piePanel);
	}

	// this method will be used for when the user logs out, then logs back in again.
	// It will display the pie graphs by repainting them
	public void displayPieNew() {

		// initialize variables / get the user information by calling the getInfo method
		List<UserPieIncome> userPieList = getInfo();
		boolean hasInfo = false;

		// update the information (if it exists)
		if (userPieList != null && userPieList.size() > 0) {
			for (UserPieIncome userInfo : userPieList) {

				if (LoginController.getUsername().equals(userInfo.getUsername())) {
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
		}

		if (!hasInfo) {
			// if there is no information of that user, update with nothing
			piePanel.getPlot().setDataset(piePanel.fillData(null));
			piePanel.addNoInfo();
			piePanel.getChartPanel().repaint();
		}
	}

	// method for all the implementation of the action commands
	@Override
	public void actionPerformed(ActionEvent e) {
		// save the info and update the pie chart
		if (e.getSource() == addBtn) {

			// get the information for the general information
			userFundsInfo = DashboardController.getUserFundsInfo();

			// check if it can be parsed as a double and is greater than / equal to 0
			if (isValid(amount.getText()) && Double.parseDouble(amount.getText()) >= 0) {

				// fetch the data
				List<UserPieIncome> userPieList = null;
				try {
					userPieList = data.getIncomePie();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// update values in the file (if it exists)
				if (userPieList != null && userPieList.size() > 0) {
					getInfo(userPieList);
				}

				// create a new instance of the map and save the data (this will only run the
				// first time)
				else {

					if (isValidRemove()) {
						// create a new value in the map
						incomePieInfo.put(selectedBtn.getText(), Double.parseDouble(amount.getText()));

						// update the accumulated total
						updateFunds();
					}

					else {
						JOptionPane.showMessageDialog(null, "Invalid input! You cannot remove more than you own!");
					}
				}

				// save the values
				try {
					DataModel.generateIncomePie(LoginController.getUsername(), incomePieInfo);
					DataModel.generateUserInfo(LoginController.getUsername(), userFundsInfo[0], userFundsInfo[1],
							userFundsInfo[2]);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// reset text and then display a message (if possible)
				if (addMoney) {
					JOptionPane.showMessageDialog(null,
							String.format("$%.2f has been added!", Double.parseDouble(amount.getText())));
				}

				amount.setText("Amount ($)");

				// update the GUI
				DashboardController.updateGUI();

				// remove text from graph
				piePanel.getChartPanel().remove(piePanel.getNoInfoText());

				// display the graph
				piePanel.getPlot().setDataset(piePanel.fillData(incomePieInfo));
				piePanel.getChartPanel().repaint();
			}

			else {
				JOptionPane.showMessageDialog(null, "Invalid input for Amount ($)");
			}
		}

		// if the switch button is pressed
		else if (e.getSource() == switchBtn) {

			// change to remove income
			if (addMoney) {
				moneyHeader.setText("Remove Income");
				moneyHeader.setBounds(110, 17, (int) moneyHeader.getPreferredSize().getWidth(), 45);

				addMoney = false;
			}

			// change to add income
			else {
				moneyHeader.setText("Add Income");
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
	private void getInfo(List<UserPieIncome> userPieList) {
		for (UserPieIncome userInfo : userPieList) {

			// if information already exists
			if (LoginController.getUsername().equals(userInfo.getUsername())) {
				incomePieInfo = userInfo.getPieLabel();

				// add onto the old key value (if it exists)
				if (incomePieInfo.get(selectedBtn.getText()) != null) {
					// store the new value in a temporary variable, remove the old key and update
					// the with a new one
					double tempVal = incomePieInfo.get(selectedBtn.getText());

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

					incomePieInfo.remove(selectedBtn.getText());
					incomePieInfo.put(selectedBtn.getText(), tempVal);
				}

				// if there is no information, create it
				else {

					if (addMoney) {

						// create a new value in the map
						incomePieInfo.put(selectedBtn.getText(), Double.parseDouble(amount.getText()));

						// update the accumulated total
						updateFunds();
					}

					else {
						if (isValidRemove()) {
							// create a new value in the map
							incomePieInfo.put(selectedBtn.getText(), Double.parseDouble(amount.getText()));

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

	// method used to dynamically update the funds from the userFundsInfo array
	public void updateFunds() {
		// update the accumulated total
		if (addMoney) {
			userFundsInfo[2] += Double.parseDouble(amount.getText());
		}

		// if the user wants to remove the amount
		else {

			// update the accumulated total
			userFundsInfo[2] -= Double.parseDouble(amount.getText());

			// check if the amount is less than 0... if so, reset to 0
			if (userFundsInfo[2] < 0) {
				userFundsInfo[2] = 0;
			}
		}
	}

	// method will check if the information can be properly removed (as in it
	// doesn't result in a negative balance)
	public boolean isValidRemove() {

		if (incomePieInfo.isEmpty() || incomePieInfo == null) {
			return false;
		}

		// loop through the pieInfoMap
		for (Map.Entry<String, Double> map : incomePieInfo.entrySet()) {

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
