/* This class is used as the main class that holds all of the survey result components.
 * It is only used to display the visuals
 */

package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SurveyResultPanel extends JPanel {

	// instance variables
	private ScorePanel scorePanel = new ScorePanel();
	private JLabel rec = new JLabel("Consider checking out the following resources...");
	private RecPanel recPanel;
	private JButton retake = new JButton("Retake Survey");

	// constructor to set the preferences for the GUI Components
	public SurveyResultPanel() {

		// set preferences for the GUI components
		rec.setFont(new Font("Sans Serif", Font.BOLD, 27));
		rec.setForeground(new Color(224, 225, 221));
		
		retake.setFont(new Font("Sans Serif", Font.BOLD, 32));
		retake.setForeground(new Color(224, 225, 221));
		retake.setOpaque(false);
		retake.setContentAreaFilled(false);
		retake.setBorderPainted(false);

		// null layout to set everything manually
		setLayout(null);

		scorePanel.setBounds(0, 0, 425, 250);
		rec.setBounds(49, 260, 679, 45);
		retake.setBounds(386, 607, 280, 40);

		// add to the JPanel
		add(scorePanel);
		add(rec);
		add(retake);

		// set preferences for the JPanel
		setOpaque(false);
	}
	
	// method will add the recPanel object to the JPanel
	public void displayRecPanel() {	
		recPanel.setBounds(49, 347, 840, 200);
		add(recPanel);
	}

	// getters and setters
	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}

	public JLabel getRec() {
		return rec;
	}

	public void setRec(JLabel rec) {
		this.rec = rec;
	}

	public RecPanel getRecPanel() {
		return recPanel;
	}

	public void setRecPanel(RecPanel recPanel) {
		this.recPanel = recPanel;
	}

	public JButton getRetake() {
		return retake;
	}

	public void setRetake(JButton retake) {
		this.retake = retake;
	}
}
