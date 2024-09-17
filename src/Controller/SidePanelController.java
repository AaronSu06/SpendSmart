/* This class acts as the main class for all of the logic and visual events
 * firing in the SidePanel class. This includes changing the color to notify the
 * user the tab they're on as well as change the panels correspondingly.
 */

package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import View.DashboardPanel;
import View.SidePanel;
import View.SurveyPanel;
import View.SurveyResultPanel;

public class SidePanelController implements ActionListener {

	// instance variables
	private DashboardPanel dashboardPanel = DashboardController.getDashboardPanel();
	private SurveyPanel surveyPanel = dashboardPanel.getSurveyPanel();
	private SurveyResultPanel surveyResult = dashboardPanel.getSurveyResult();
	private SidePanel sidePanel = dashboardPanel.getSidePanel();

	// constructor to add action listeners
	public SidePanelController() {
		// add action listeners for the side panels
		for (JButton b : sidePanel.getBtnArr()) {
			b.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// action events for the side panel buttons
		// disable the survey panel / GUI components
		if (e.getSource() == sidePanel.getDashboard()) {
			surveyPanel.setVisible(false);
			surveyResult.setVisible(false);

			dashboardPanel.getDashboardBtn().setVisible(true);

			// update the current panel
			DashboardController.getCurrentPanel().setVisible(false);
			dashboardPanel.getTotalExpense().setVisible(true);
			DashboardController.setCurrentPanel(dashboardPanel.getTotalExpense());

			// change colors
			sidePanel.getSurvey().setForeground(new Color(140, 140, 140));
			sidePanel.getDashboard().setForeground(new Color(224, 225, 221));
			
			DashboardController.changeColor(0);
		}

		// disable the dashboard panel / GUI components
		else if (e.getSource() == sidePanel.getSurvey()) {
			dashboardPanel.getDashboardBtn().setVisible(false);
			for (JButton b : dashboardPanel.getBtnToPanel().keySet()) {
				dashboardPanel.getBtnToPanel().get(b).setVisible(false);
			}
			surveyResult.setVisible(false);
			SurveyController.reset();

			// update current panel
			surveyPanel.setVisible(true);

			// change colors
			sidePanel.getDashboard().setForeground(new Color(140, 140, 140));
			sidePanel.getSurvey().setForeground(new Color(224, 225, 221));
		}

		// remove all
		else if (e.getSource() == sidePanel.getLogout()) {
			
			SurveyController.setScore(0);

			Application.mainFrame.remove(dashboardPanel);
			Application.mainFrame.remove(sidePanel);

			LoginController.getLoginPanel().setVisible(true);
		}
	}
}
