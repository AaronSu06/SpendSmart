/* This class acts as the object for all of the components towards displaying buttons for
 * the dashboard panel.
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardInfoObj extends JPanel {

	// instance variables
	private JLabel header = new JLabel();
	private JLabel priceText = new JLabel();

	private int price = 0;

	// constructor to set the preferences for the GUI Components, as well as taking
	// in String and int arguments to create different objects
	public DashboardInfoObj(String headerText, int price) {
		// initially set the size of JButton
		setPreferredSize(new Dimension(396, 117));

		// update the integer
		this.price = price;

		// set font and color of the GUI components
		header.setFont(new Font("Sans Serif", Font.BOLD, 22));
		header.setForeground(new Color(224, 225, 221));
		header.setBackground(new Color(13, 27, 42));

		priceText.setFont(new Font("Sans Serif", Font.PLAIN, 19));
		priceText.setForeground(new Color(224, 225, 221));
		priceText.setBackground(new Color(32, 45, 70));

		// set size and position
		setLayout(null);

		header.setBounds(0, 0, 396, 37);
		priceText.setBounds(0, 37, 396, 80);

		header.setText(headerText);
		priceText.setText("$" + String.valueOf(price));

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
