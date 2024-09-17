/* This class is used to display all the visuals for the SidePanel, which 
 * is used for the SidePanelController to open to new JPanels
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SidePanel extends JPanel {

	// instance variables
	private JButton dashboard = new JButton("Dashboard");
	private JButton survey = new JButton("Survey");
	private JButton logout = new JButton("Log Out");

	private JButton[] btnArr = {dashboard, survey, logout};

	// constructor to set the preferences for all the GUI Components
	public SidePanel() {
		// set background
		setBackground(new Color(32, 45, 70));

		// set font and color of the GUI components
		dashboard.setFont(new Font("Sans Serif", Font.BOLD, 28));
		dashboard.setForeground(new Color(224, 225, 221));

		survey.setFont(new Font("Sans Serif", Font.BOLD, 28));
		survey.setForeground(new Color(140, 140, 140));

		logout.setFont(new Font("Sans Serif", Font.BOLD, 28));
		logout.setForeground(new Color(224, 225, 221));

		// set size and position
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// center text
		dashboard.setAlignmentX(CENTER_ALIGNMENT);
		survey.setAlignmentX(CENTER_ALIGNMENT);
		logout.setAlignmentX(CENTER_ALIGNMENT);

		// make invisible
		dashboard.setBorderPainted(false);
		dashboard.setContentAreaFilled(false);
		dashboard.setFocusPainted(false);
		dashboard.setOpaque(false);

		survey.setBorderPainted(false);
		survey.setContentAreaFilled(false);
		survey.setFocusPainted(false);
		survey.setOpaque(false);

		logout.setBorderPainted(false);
		logout.setContentAreaFilled(false);
		logout.setFocusPainted(false);
		logout.setOpaque(false);

		// add to the JLabel
		add(Box.createRigidArea(new Dimension(0, 25)));
		add(dashboard);
		add(Box.createRigidArea(new Dimension(0, 25)));
		add(survey);
		add(Box.createRigidArea(new Dimension(0, 490)));
		add(logout);
	}

	// getters and setters
	public JButton[] getBtnArr() {
		return btnArr;
	}

	public void setBtnArr(JButton[] btnArr) {
		this.btnArr = btnArr;
	}

	public JButton getDashboard() {
		return dashboard;
	}

	public void setDashboard(JButton dashboard) {
		this.dashboard = dashboard;
	}

	public JButton getSurvey() {
		return survey;
	}

	public void setSurvey(JButton survey) {
		this.survey = survey;
	}

	public JButton getLogout() {
		return logout;
	}

	public void setLogout(JButton logout) {
		this.logout = logout;
	}
}
