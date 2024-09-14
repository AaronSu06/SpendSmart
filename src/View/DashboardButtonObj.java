/* This class acts as the holder for all of the components towards displaying buttons for
 * the dashboard panel.
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

public class DashboardButtonObj extends JButton {

	// instance variables
	private JLabel header = new JLabel();
	private JLabel priceText = new JLabel();

	// constructor to set the preferences for the GUI Components. It also takes in
	// String argument to create different objects
	public DashboardButtonObj(String headerText) {
		// initially set the size of JButton
		setPreferredSize(new Dimension(264, 86));

		// set font and color of the GUI components
		header.setFont(new Font("Sans Serif", Font.BOLD, 22));
		header.setForeground(new Color(140, 140, 140));
		header.setBackground(new Color(13, 27, 42));

		priceText.setFont(new Font("Sans Serif", Font.PLAIN, 19));
		priceText.setForeground(new Color(140, 140, 140));
		priceText.setBackground(new Color(32, 45, 70));

		// set size and position
		setLayout(null);

		header.setBounds(0, 0, 264, 37);
		priceText.setBounds(0, 37, 264, 49);

		header.setText(headerText);
		priceText.setText("$0.00");

		// center text
		header.setHorizontalAlignment(header.CENTER);
		priceText.setHorizontalAlignment(priceText.CENTER);

		// make visible
		header.setOpaque(true);
		priceText.setOpaque(true);

		// add to the JButton
		add(header);
		add(priceText);
	}

	// getters and setters
	public JLabel getHeader() {
		return header;
	}

	public void setHeader(JLabel header) {
		this.header = header;
	}

	public JLabel getPriceText() {
		return priceText;
	}

	public void setPriceText(JLabel priceText) {
		this.priceText = priceText;
	}

}
