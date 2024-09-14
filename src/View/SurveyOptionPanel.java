/* This class is used as an object to hold / create all the JButtons and questions
 * used in the SurveyPanel class
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SurveyOptionPanel extends JPanel {

	// instance variables
	private JLabel question = new JLabel();
	private JPanel optionPanel = new JPanel();
	private ButtonGroup buttonGroup = new ButtonGroup();

	private String[] optionTextArr;
	private int[] markArr;

	// constructor to set the preferences for the GUI Components. Takes in multiple
	// arguments so that it can create different objects
	public SurveyOptionPanel(String questionText, String[] optionTextArr, int[] markArr) {
		// update values
		this.optionTextArr = optionTextArr;
		this.markArr = markArr;

		// set font and color
		question.setFont(new Font("Sans Serif", Font.BOLD, 27));
		question.setForeground(new Color(224, 225, 221));

		// set preferences for the questions
		question.setSize(new Dimension(700, 45));
		question.setText(questionText);
		question.setAlignmentX(CENTER_ALIGNMENT);
		question.setOpaque(false);

		// set layout for option panel
		optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		optionPanel.setOpaque(false);

		// create and store the radio buttons into an array list
		for (int i = 0; i < optionTextArr.length; i++) {

			// initialize variable
			JRadioButton btn = new JRadioButton();

			// set preferences
			btn.setSize(new Dimension(264, 49));
			btn.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			btn.setForeground(new Color(224, 225, 221));
			btn.setAlignmentX(CENTER_ALIGNMENT);
			btn.setText(optionTextArr[i]);

			// add to the button group
			buttonGroup.add(btn);

			// add to the option JPanel
			optionPanel.add(btn);
		}

		// set layout manager for the JPanel
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(question);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(optionPanel);

		// preferences for the JPanel
		setOpaque(false);
	}

	// getters and setters
	public JLabel getQuestion() {
		return question;
	}

	public String[] getOptionTextArr() {
		return optionTextArr;
	}

	public int[] getMarkArr() {
		return markArr;
	}

	public void setMarkArr(int[] markArr) {
		this.markArr = markArr;
	}

	public void setOptionTextArr(String[] optionTextArr) {
		this.optionTextArr = optionTextArr;
	}

	public void setQuestion(JLabel question) {
		this.question = question;
	}

	public JPanel getOptionPanel() {
		return optionPanel;
	}

	public void setOptionPanel(JPanel optionPanel) {
		this.optionPanel = optionPanel;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}
}
