/* This class acts as the main class for all of the visual events
 * firing in the TotalExpense, TodayExpense, and TotalIncome panels.
 */

package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import Model.DataModel;
import Model.UserInfo;
import View.DashboardPanel;
import View.ExpenseIncomePanel;
import View.MainFrame;
import View.SidePanel;

public class DashboardController implements ActionListener {

	// instance variables
	private static DataModel data = new DataModel();

	private static double[] userFundsInfo = new double[3];

	private static DashboardPanel dashboardPanel = new DashboardPanel();
	private static ExpenseIncomePanel expenseIncome = dashboardPanel.getTotalExpense().getExpenseIncome();
	private static JPanel currentPanel;

	// constructor to add the action listeners and update / declare values
	public DashboardController() {
		// change values in the dashboard panel buttons to the saved information
		updateGUI();

		// add action listeners for the dashboard buttons
		for (JButton b : dashboardPanel.getBtnToPanel().keySet()) {
			b.addActionListener(this);
		}

		// initially set currentPanel
		currentPanel = dashboardPanel.getTotalExpense();
	}

	// getters and setters
	public static double[] getUserFundsInfo() {
		return userFundsInfo;
	}

	public static DashboardPanel getDashboardPanel() {
		return dashboardPanel;
	}

	public static void setDashboardPanel(DashboardPanel dashboardPanel) {
		DashboardController.dashboardPanel = dashboardPanel;
	}

	public static void setUserFundsInfo(double[] userFundsInfo) {
		DashboardController.userFundsInfo = userFundsInfo;
	}

	public static JPanel getCurrentPanel() {
		return currentPanel;
	}

	public static void setCurrentPanel(JPanel currentPanel) {
		DashboardController.currentPanel = currentPanel;
	}

	// action events for the dashboard buttons
	@Override
	public void actionPerformed(ActionEvent e) {

		// loop through all the buttons
		for (int i = 0; i < 3; i++) {

			// get the button clicked
			if (e.getSource() == dashboardPanel.getDashboardBtn().getDashboardBtn()[i]) {

				// update the current panels (display visibility)
				currentPanel.setVisible(false);
				JPanel newPanel = dashboardPanel.getBtnToPanel().get(e.getSource());
				newPanel.setVisible(true);
				currentPanel = newPanel;

				// change the color
				changeColor(i);
			}
		}
	}

	// method will get fetch the data for the total user information array. It will
	// also handle the logic for it as in filling with zeros if need be
	public static double[] getTotalInfo() {

		// fetch the data
		List<UserInfo> userInfoList = null;

		try {
			userInfoList = data.getUserInfo();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// update values in the file (if it exists)
		if (userInfoList != null && userInfoList.size() > 0) {
			for (UserInfo userInfo : userInfoList) {

				// if information already exists
				if (LoginController.getUsername() != null
						&& LoginController.getUsername().equals(userInfo.getUsername())) {

					userFundsInfo[0] = userInfo.getTotalExpense();
					userFundsInfo[1] = userInfo.getTodayExpense();
					userFundsInfo[2] = userInfo.getTotalIncome();

					return userFundsInfo;
				}
			}
		}

		// if no information, fill with zero
		userFundsInfo[0] = 0;
		userFundsInfo[1] = 0;
		userFundsInfo[2] = 0;

		return userFundsInfo;
	}

	// method will dynamically update the GUI components
	public static void updateGUI() {
		// get the general information
		userFundsInfo = getTotalInfo();

		// display / update the dashboard button text
		for (int i = 0; i < 3; i++) {
			dashboardPanel.getDashboardBtn().getDashboardBtn()[i].getPriceText()
					.setText(String.format("$%.2f", userFundsInfo[i]));
			dashboardPanel.getDashboardBtn().getDashboardBtn()[i].repaint();
		}

		// update the bar graph
		expenseIncome.getPlot().setDataset(expenseIncome.fillData(userFundsInfo[2], userFundsInfo[0]));
		expenseIncome.getChartPanel().repaint();
	}

	// method will change the color of the dashboard buttons that are clicked / not
	// clicked
	public void changeColor(int index) {

		// loop through all the dashboard buttons
		for (int i = 0; i < 3; i++) {

			// if the index does not equal to current iteration
			if (i != index) {
				// change color to gray
				dashboardPanel.getDashboardBtn().getDashboardBtn()[i].getHeader()
						.setForeground(new Color(140, 140, 140));
				dashboardPanel.getDashboardBtn().getDashboardBtn()[i].getPriceText()
						.setForeground(new Color(140, 140, 140));
			}

			// otherwise
			else {
				// change color to white
				dashboardPanel.getDashboardBtn().getDashboardBtn()[i].getHeader()
						.setForeground(new Color(224, 225, 221));
				dashboardPanel.getDashboardBtn().getDashboardBtn()[i].getPriceText()
						.setForeground(new Color(224, 225, 221));
			}
		}
	}
}
