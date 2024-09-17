/* This class is used as the main class that holds all of the survey question components.
 * It is only used to display the visuals
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SurveyPanel extends JPanel {

	// instance variables
	private JPanel container = new JPanel();
	private JScrollPane scrollBar = new JScrollPane(container);
	private JLabel scaleQuestion = new JLabel(
			"<html><div style='text-align: center;'>On a scale of 1 - 10, how confident are you<br />on Financial Literature?</div></html>");
	private JSlider scale = new JSlider(0, 10, 5);
	private JLabel scaleDisplay = new JLabel();
	private ArrayList<SurveyOptionPanel> surveyOptionArr = new ArrayList<SurveyOptionPanel>();
	private JButton submit = new JButton("Continue");

	// variables for the survey information (questions, possible answers, scores per
	// answer)
	private String[] questionArr = { "What is the range of your monthly income?",
			"What is the range of your monthly expenses?", "How much do you allocate towards investing per month?",
			"Do you have Emergency Funds?" };
	private String[][] optionArr = { { "< $500", "$501 - $1000", "$1001 - $2000", "> $2000" },
			{ "< $100", "$101 - $250", "$251 - $500", "> $500" }, { "< $100", "$101 - $250", "$251 - $500", "> $500" },
			{ "Yes", "No" } };
	private int[][] markArr = { { 1, 2, 3, 4 }, { 4, 3, 2, 1 }, { 1, 2, 3, 4 }, { 4, 1 } };

	// constructor to set the preferences for the GUI Components
	public SurveyPanel() {
		// set preferences (font, color, etc.)
		submit.setFont(new Font("Sans Serif", Font.BOLD, 27));
		submit.setForeground(new Color(224, 225, 221));

		scaleQuestion.setFont(new Font("Sans Serif", Font.BOLD, 27));
		scaleQuestion.setForeground(new Color(224, 225, 221));
		scaleQuestion.setAlignmentX(CENTER_ALIGNMENT);
		scaleQuestion.setHorizontalAlignment(JLabel.CENTER);

		scale.setForeground(new Color(224, 225, 221));
		scale.setFont(new Font("Sans Serif", Font.BOLD, 12));
		scale.setPaintTrack(true);
		scale.setPaintTicks(true);
		scale.setPaintLabels(true);
		scale.setMajorTickSpacing(10);
		scale.setMinorTickSpacing(1);
		scale.setSnapToTicks(true);

		scaleDisplay.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		scaleDisplay.setForeground(new Color(224, 225, 221));
		scaleDisplay.setText("Scale: " + scale.getValue());
		scaleDisplay.setAlignmentX(CENTER_ALIGNMENT);
		
		submit.setFont(new Font("Sans Serif", Font.BOLD, 36));
		submit.setForeground(new Color(224, 225, 221));
		submit.setOpaque(false);
		submit.setContentAreaFilled(false);
		submit.setBorderPainted(false);

		// add change listener
		scale.addChangeListener(new ChangeListener() {

			// updaate the display everytime the slider is changed
			public void stateChanged(ChangeEvent event) {
				scaleDisplay.setText("Scale: " + scale.getValue());
			}
		});

		// set layout manager for the JPanels
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

		// create instances for the survey questions and then add them to the panel
		for (int i = 0; i < 3; i++) {
			SurveyOptionPanel survey = new SurveyOptionPanel(questionArr[i], optionArr[i], markArr[i]);
			survey.setAlignmentX(CENTER_ALIGNMENT);
			surveyOptionArr.add(survey);
			container.add(survey);
			container.add(Box.createRigidArea(new Dimension(0, 40)));
		}

		// add to extras to the container
		container.add(scaleQuestion);
		container.add(Box.createRigidArea(new Dimension(0, 20)));
		container.add(scale);
		add(Box.createRigidArea(new Dimension(0, 20)));
		container.add(scaleDisplay);
		container.add(Box.createRigidArea(new Dimension(0, 40)));

		SurveyOptionPanel survey = new SurveyOptionPanel(questionArr[3], optionArr[3], markArr[3]);
		surveyOptionArr.add(survey);
		container.add(survey);
		add(Box.createRigidArea(new Dimension(0, 20)));

		// set preferences for the scroll bar / container
		container.setOpaque(false);

		scrollBar.setViewportBorder(null);
		scrollBar.setBorder(null);
		scrollBar.getViewport().setOpaque(false);
		scrollBar.setOpaque(false);

		// set size and position
		scrollBar.setPreferredSize(new Dimension(1000, 500));
		scrollBar.getVerticalScrollBar().setUnitIncrement(8);
		scrollBar.getVerticalScrollBar().setBlockIncrement(16);

		submit.setAlignmentX(CENTER_ALIGNMENT);
		submit.setPreferredSize(new Dimension(250, 50));

		// add to JPanel
		add(scrollBar);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(submit);

		// preferences for the JPanel
		setOpaque(false);
		setVisible(true);
	}

	// getters and setters
	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

	public JScrollPane getScrollBar() {
		return scrollBar;
	}

	public void setScrollBar(JScrollPane scrollBar) {
		this.scrollBar = scrollBar;
	}

	public JLabel getScaleQuestion() {
		return scaleQuestion;
	}

	public void setScaleQuestion(JLabel scaleQuestion) {
		this.scaleQuestion = scaleQuestion;
	}

	public JSlider getScale() {
		return scale;
	}

	public void setScale(JSlider scale) {
		this.scale = scale;
	}

	public JLabel getScaleDisplay() {
		return scaleDisplay;
	}

	public void setScaleDisplay(JLabel scaleDisplay) {
		this.scaleDisplay = scaleDisplay;
	}

	public ArrayList<SurveyOptionPanel> getSurveyOptionArr() {
		return surveyOptionArr;
	}

	public void setSurveyOptionArr(ArrayList<SurveyOptionPanel> surveyOptionArr) {
		this.surveyOptionArr = surveyOptionArr;
	}

	public JButton getSubmit() {
		return submit;
	}

	public void setSubmit(JButton submit) {
		this.submit = submit;
	}

	public String[] getQuestionArr() {
		return questionArr;
	}

	public void setQuestionArr(String[] questionArr) {
		this.questionArr = questionArr;
	}

	public String[][] getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(String[][] optionArr) {
		this.optionArr = optionArr;
	}
}