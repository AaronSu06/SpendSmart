/* This method is used as an object to hold all GUI Components for the recommended survey resources,
 * used in the SurveyResults class.
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RecPanel extends JPanel implements ActionListener {

	// instance variables
	private JButton[] resourceArr = new JButton[3];

	// important variables
	String[] titleArr = new String[3];
	String[] linkArr = new String[3];
	private Map<String, String> titleToLink = new HashMap<String, String>();

	// constructor to set the prefereneces of GUI components as well as the logic behind
	// getting the resources
	public RecPanel(Map<String, String> titleToLink) {
		// update the hyperlinkArr value
		this.titleToLink = titleToLink;

		// break the map down into arrays
		toArr();

		// using flow layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		for (int i = 0; i < 3; i++) {
			// create a new instance and set the preferences
			resourceArr[i] = new JButton();
			
			resourceArr[i].setFont(new Font("Sans Serif", Font.BOLD, 24));
			resourceArr[i].setForeground(new Color(224, 225, 221));
			resourceArr[i].setOpaque(false);
			resourceArr[i].setContentAreaFilled(false);
			resourceArr[i].setBorderPainted(false);
			resourceArr[i].setText(titleArr[i]);

			add(resourceArr[i]);
			add(Box.createRigidArea(new Dimension(0, 30)));

			// add action listener
			resourceArr[i].addActionListener(this);
		}

		// preferences for the JPanel
		setOpaque(false);
	}

	// getters and setters
	public JButton[] getResourceArr() {
		return resourceArr;
	}

	public void setResourceArr(JButton[] resourceArr) {
		this.resourceArr = resourceArr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// loop through each JButton, and on button press, open the URL corresponding to
		// it
		for (int i = 0; i < 3; i++) {
			if (e.getSource() == resourceArr[i]) {

				// open the URL
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(linkArr[i]));
				} catch (IOException e1) {
					System.out.println("No information");
				}

			}
		}
	}

	// method will break down the map into 2 different arrays
	public void toArr() {
		int i = 0;

		// loop through the map
		for (Map.Entry<String, String> map : titleToLink.entrySet()) {
			
			titleArr[i] = map.getKey();
			linkArr[i] = map.getValue();

			i++;
		}
	}

}
