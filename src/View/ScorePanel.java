/* This class is used to display the scores for the SurveyResults class
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {

	// instance variables
	private JLabel header = new JLabel("Your Financial Score Is...");
	private JLabel score = new JLabel("0%");

	// constructor to set the preferences for all the GUI Components
	public ScorePanel() {
		// set preferences (font, color, etc.)
		header.setFont(new Font("Sans Serif", Font.BOLD, 27));
		header.setForeground(new Color(224, 225, 221));
		header.setAlignmentX(CENTER_ALIGNMENT);

		score.setFont(new Font("Sans Serif", Font.BOLD, 70));
		score.setForeground(new Color(224, 225, 221));
		score.setAlignmentX(CENTER_ALIGNMENT);
		
		// box layout to position vertically
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// add to the JPanel
		add(Box.createRigidArea(new Dimension(0, 55)));
		add(header);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(score);
		
		// preferences for the JPanel
		setOpaque(false);
	}

	// getters and setters
	public JLabel getHeader() {
		return header;
	}

	public void setHeader(JLabel header) {
		this.header = header;
	}

	public JLabel getScore() {
		return score;
	}

	public void setScore(JLabel score) {
		this.score = score;
	}
}
