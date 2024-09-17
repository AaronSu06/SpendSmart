/* This class will be used to display all the GUI components for the total expenses.
 * This includes the 3D bar graph and the total pie chart.
 */

package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.DashboardController;
import Controller.LoginController;
import Model.DataModel;
import Model.UserPieTotal;

public class TotalExpensePanel extends JPanel {

	// instance variables
	private DataModel data = new DataModel();
	private Map<String, Double> totalPieInfo = new HashMap<String, Double>();

	private ExpenseIncomePanel expenseIncome = new ExpenseIncomePanel();

	private PiePanel piePanel = new PiePanel("Total Expenses", null);

	// constructor to set the positions of the JPanel and objects
	public TotalExpensePanel() {

		// set size and position of the JPanel and objects
		setLayout(null);

		expenseIncome.setBounds(239, 207, 590, 450);

		add(expenseIncome);

		setOpaque(false);
	}

	// getters and setters
	public PiePanel getPiePanel() {
		return piePanel;
	}

	public void setPiePanel(PiePanel piePanel) {
		this.piePanel = piePanel;
	}

	public ExpenseIncomePanel getExpenseIncome() {
		return expenseIncome;
	}

	public void setExpenseIncome(ExpenseIncomePanel expenseIncome) {
		this.expenseIncome = expenseIncome;
	}

	// fetch the data for the 3D pie graph
	public List<UserPieTotal> getInfo() {
		// fetch the data
		List<UserPieTotal> userPieList = null;

		try {
			userPieList = data.getTotalPie();
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
		List<UserPieTotal> userPieList = getInfo();
		boolean hasInfo = false;

		// display the information (if it exists)
		if (userPieList != null && userPieList.size() > 0) {
			for (UserPieTotal userInfo : userPieList) {

				if (LoginController.getUsername().equals(userInfo.getUsername())) {
					piePanel = new PiePanel("Total Expenses", userInfo.getPieLabel());
					piePanel.displayChart();

					hasInfo = true;

					break;
				}

			}
		}

		// otherwise display a panel with a message to display no text
		else {
			piePanel = new PiePanel("Total Expenses", null);
			piePanel.displayChart();
			piePanel.addNoInfo();

			hasInfo = true;
		}

		if (!hasInfo) {
			// if there is no information of that user, display a panel with a message to
			// display no text
			piePanel = new PiePanel("Total Expenses", null);
			piePanel.displayChart();
			piePanel.addNoInfo();
		}

		piePanel.setBounds(858, 237, 396, 390);
		add(piePanel);
	}

	// this method will be used for when the user logs out, then logs back in again.
	// It will display the pie graphs by repainting them
	public void displayPieNew() {

		// initialize variables / get the user information by calling the getInfo method
		List<UserPieTotal> userPieList = getInfo();
		boolean hasInfo = false;

		// update the information (if it exists)
		if (userPieList != null && userPieList.size() > 0) {
			for (UserPieTotal userInfo : userPieList) {

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

	public void totalExpenseEvent(String selected, double amount, boolean addMoney) {
		// fetch the data
		List<UserPieTotal> userPieList = null;
		try {
			userPieList = data.getTotalPie();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// update values in the file (if it exists)
		if (userPieList != null && userPieList.size() > 0) {
			getInfo(userPieList, selected, amount, addMoney);
		}

		// create a new instance of the map and save the data (this will only run the
		// first time)
		else {
			// create a new value in the map
			totalPieInfo.put(selected, amount);
		}

		// save new information
		try {
			DataModel.generateTotalPie(LoginController.getUsername(), totalPieInfo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// update the GUI
		DashboardController.updateGUI();

		// remove text from graph
		piePanel.getChartPanel().remove(piePanel.getNoInfoText());

		// display / update the pie graph
		piePanel.getPlot().setDataset(piePanel.fillData(totalPieInfo));
		piePanel.getChartPanel().repaint();
	}

	// method will change the information dynamically
	public void getInfo(List<UserPieTotal> userPieList, String selected, double amount, boolean addMoney) {
		for (UserPieTotal userInfo : userPieList) {

			// if information already exists
			if (LoginController.getUsername().equals(userInfo.getUsername())) {
				totalPieInfo = userInfo.getPieLabel();

				// add onto the old key value (if it exists)
				if (totalPieInfo.get(selected) != null) {
					// store the new value in a temporary variable, remove the old key and update
					// the with a new one
					double tempVal = totalPieInfo.get(selected);

					if (addMoney) {
						tempVal += amount;
					}

					else {
						
						if (isValidRemove(selected, amount)) {
							tempVal -= amount;
						}

					}

					totalPieInfo.remove(selected);
					totalPieInfo.put(selected, tempVal);
				}

				// if there is no information, create it
				else {

					if (addMoney) {

						// create a new value in the map
						totalPieInfo.put(selected, amount);
					}

					else {
						if (isValidRemove(selected, amount)) {
							// create a new value in the map
							totalPieInfo.put(selected, amount);
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

	// method will check if the information can be properly removed (as in it
	// doesn't result in a negative balance)
	public boolean isValidRemove(String selected, double amount) {

		if (totalPieInfo.isEmpty() || totalPieInfo == null) {
			return false;
		}

		// loop through the pieInfoMap
		for (Map.Entry<String, Double> map : totalPieInfo.entrySet()) {

			// get the corresponding key
			if (map.getKey().equals(selected)) {
				// if there is no information, return false
				if (map.getValue() == null) {
					return false;
				}

				// if the information in map minus the information we want to subtract is less
				// than 0
				if ((map.getValue() - amount) < 0) {

					return false;
				}
			}
		}

		return true;
	}
}
