/* This class is used an object that holds all of the GUI Components used in
 * classes such as the TotalIncome and TodayExpense classes
 */

package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MoneyOptionPanel extends JPanel {

	// instance variables
	private JButton[] btnArr = new JButton[4];
	
	// constructor to set the preferences for the GUI Components
	public MoneyOptionPanel(String[] btnTextArr) {
		
		setSize(450, 240);
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));
		
		// set preferences for the JButtons
		for (int i = 0; i < btnArr.length; i++) {
			btnArr[i] = new JButton();
			
			btnArr[i].setSize(180, 37);
			btnArr[i].setFont(new Font("Sans Serif", Font.BOLD, 23));
			btnArr[i].setForeground(new Color(140, 140, 140));
			btnArr[i].setText(btnTextArr[i]);
			btnArr[i].setHorizontalAlignment(btnArr[i].CENTER);
			btnArr[i].setBorderPainted(false);
			btnArr[i].setFocusPainted(false);
			
			add(btnArr[i]);
		}

		// initially set the color of the first index to white
		btnArr[0].setForeground(new Color(224, 225, 221));
		
		setOpaque(false);
	}

	// getters and setters
	public JButton[] getBtnArr() {
		return btnArr;
	}

	public void setBtnArr(JButton[] btnArr) {
		this.btnArr = btnArr;
	}
}
