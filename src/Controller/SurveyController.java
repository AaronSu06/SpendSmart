/* This class acts as the main class for all of the logic and visual events
 * firing in the SurveyOption and SurveyResult Panels. This includes resetting
 * all the survey options to switching JPanels on button press.
 */

package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import View.DashboardPanel;
import View.RecPanel;
import View.SurveyOptionPanel;
import View.SurveyPanel;
import View.SurveyResultPanel;

public class SurveyController implements ActionListener {

	// instance variables
	private static DashboardPanel dashboardPanel = DashboardController.getDashboardPanel();
	private static SurveyPanel surveyPanel = dashboardPanel.getSurveyPanel();
	private static SurveyResultPanel surveyResult = dashboardPanel.getSurveyResult();

	// important variables
	private static Map<String, String> titleToLink = new HashMap<String, String>();
	private String[] titleArr = { "ECampusOntario - Understanding Finances", "Canada.ca - Budgeting", "BMO - Investing",
			"Investopedia - Finance", "DontChangeMuch - Stress" };
	private String[] linkArr = {
			"https://ecampusontario.pressbooks.pub/studyprocaff/chapter/successful-students-understand-their-finances/",

			"https://www.canada.ca/en/financial-consumer-agency/services/pay-down-student-debt.html",

			"https://www.bmo.com/main/personal/investments/learning-centre/common-investing-terms/?ecid=ps-F23OINAOPSIL3-CSUM26&gad_source=1&gclid=Cj0KCQiA2KitBhCIARIsAPPMEhJxRNwvJ4yLHqVfp9DhCXNUSjhCUV1r5nZIh7V_yWv3PN_nEf2hfVEaAr5xEALw_wcB&gclsrc=aw.ds",

			"https://www.investopedia.com/guide-to-financial-literacy-4800530#:~:text=Financial%20literacy%20is%20the%20ability,than%20those%20with%20lower%20levels",

			"https://dontchangemuch.ca/mental-health/how-to-improve-your-financial-wellness/?gad_source=1&gclid=Cj0KCQiA2KitBhCIARIsAPPMEhKsyK6Ybq4gmF3cFfIaK8XvxX-XvFOfPnVcmeuVH0ZQR4i8C8QvVWYaAr9mEALw_wcB" };

	private static int score = 0;

	// constructor to add action listeners
	public SurveyController() {

		// add action listeners
		surveyPanel.getSubmit().addActionListener(this);
		surveyResult.getRetake().addActionListener(this);
	}

	// getters and setters
	public SurveyPanel getSurveyPanel() {
		return surveyPanel;
	}

	public void setSurveyPanel(SurveyPanel surveyPanel) {
		this.surveyPanel = surveyPanel;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		SurveyController.score = score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == surveyPanel.getSubmit()) {

			// if all questions have an answer
			if (checkResponses()) {

				// loop through all the of questions
				for (int i = 0; i < surveyPanel.getSurveyOptionArr().size(); i++) {

					// loop through each button in the corresponding question
					for (Enumeration<AbstractButton> buttons = surveyPanel.getSurveyOptionArr().get(i).getButtonGroup()
							.getElements(); buttons.hasMoreElements();) {
						AbstractButton button = buttons.nextElement();

						// get the selected button
						if (button.isSelected()) {

							// calculate the score by calling the getMark method
							getMark(button.getText(), i);

						}

					}

				}

				// add the slider value to the score
				addToMap(3, surveyPanel.getScale().getValue());
				score += surveyPanel.getScale().getValue();

				// update GUI components in the surveyResult panel
				surveyResult.setRecPanel(new RecPanel(titleToLink));
				surveyResult.getScorePanel().getScore()
						.setText(String.format("%.0f", ((double) score / 26) * 100) + "%");
				changeColor(((double) score / 26) * 100);

				surveyResult.displayRecPanel();

				// display the results
				surveyPanel.setVisible(false);
				surveyResult.setVisible(true);
			}

			else {
				JOptionPane.showMessageDialog(null, "Invalid input! Complete all required questions!");
			}
		}

		else if (e.getSource() == surveyResult.getRetake()) {
			// disable / enable JPanels
			surveyResult.setVisible(false);
			surveyPanel.setVisible(true);

			// call reset method to change within the SurveyPanel
			reset();
		}
	}

	// method will clear all the selections from the SurveyPanel... it will also
	// reset the slider
	public static void reset() {
		// clear all selections via looping through the buttongroups
		for (int i = 0; i < surveyPanel.getSurveyOptionArr().size(); i++) {

			surveyPanel.getSurveyOptionArr().get(i).getButtonGroup().clearSelection();
		}

		// reset slider position and text
		surveyPanel.getScale().setValue(5);
		surveyPanel.getScaleDisplay().setText("Scale: " + surveyPanel.getScale().getValue());

		// reset JScrollPane position
		JScrollBar verticalScrollBar = surveyPanel.getScrollBar().getVerticalScrollBar();
		verticalScrollBar.setValue(verticalScrollBar.getMinimum());

		// remove the old instance of the recPanel (if it exists)
		if (surveyResult.getRecPanel() != null) {
			surveyResult.remove(surveyResult.getRecPanel());
		}

		// reset all values
		score = 0;
		titleToLink.clear();
	}

	// change the color of the score text based on the actual score
	public void changeColor(double score) {

		if (score <= 49) {
			surveyResult.getScorePanel().getScore().setForeground(new Color(255, 71, 76));
		}

		else if (score <= 74) {
			// blank
		}

		else {
			surveyResult.getScorePanel().getScore().setForeground(new Color(0, 212, 84));
		}

	}

	// method will check through every ButtinGroup and then check if they are all
	// clicked (adding up to 4 total)
	public boolean checkResponses() {
		int count = 0;

		for (SurveyOptionPanel panel : surveyPanel.getSurveyOptionArr()) {

			if (panel.getButtonGroup().getSelection() != null) {
				count += 1;
			}
		}

		if (count == 4) {
			return true;
		}

		else {
			return false;
		}
	}

	// method will loop through all the buttons in the corresponding question (from
	// index), and then accumulate a total score
	public void getMark(String button, int index) {

		// loop through all the button
		for (int i = 0; i < surveyPanel.getSurveyOptionArr().get(index).getOptionTextArr().length; i++) {

			// check if the button selected matches one of the buttons in the loop
			if (button.equals(surveyPanel.getSurveyOptionArr().get(index).getOptionTextArr()[i])) {

				// check if it can be added to the map and then increment and break out of the
				// loop
				addToMap(index, surveyPanel.getSurveyOptionArr().get(index).getMarkArr()[i]);
				score += surveyPanel.getSurveyOptionArr().get(index).getMarkArr()[i];
				break;
			}
		}

	}

	// method will check if it can be added to the map
	public void addToMap(int index, int score) {

		if (index == 3) {

			// less than 4 meaning that there can be room for improvement (is different for
			// slider)
			if (score <= 4) {

				// check if the map can still add
				if (titleToLink.size() < 3) {

					titleToLink.put(titleArr[index], linkArr[index]);
				}
			}
		}

		else {
			// less than 2 meaning that there can be room for improvement
			if (score <= 2) {

				// check if the map can still add
				if (titleToLink.size() < 3) {

					titleToLink.put(titleArr[index], linkArr[index]);
				}
			}
		}
	}
}
