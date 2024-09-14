/* This class acts as the object for all of the components towards adding funds,
 * used in the TodaysExpense and TotalIncome classes.
 */

package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import Controller.LoginController;
import Model.DataModel;
import Model.UserPieToday;

public class AddMoneyPanel extends JPanel implements FocusListener {

	// instance variables
	private String[] btnTextArr;

	private JLabel bg = new JLabel(new ImageIcon("Images/AddPanelBG.png"));

	private JLabel header = new JLabel();
	private MoneyOptionPanel optionPanel;
	private JTextField amount = new JTextField("Amount ($)");
	private JButton addBtn = new JButton("Save");
	private JButton selectedBtn;

	// constructor to set the preferences and events for the GUI Components
	public AddMoneyPanel(String headerText, String[] btnTextArr) {
		this.btnTextArr = btnTextArr;

		// add focus listeners
		amount.addFocusListener(this);

		// set preferences for JPanel
		setSize(450, 455);

		// set preferences for GUI components
		header.setFont(new Font("Sans Serif", Font.BOLD, 28));
		header.setForeground(new Color(224, 225, 221));
		header.setText(headerText);

		amount.setFont(new Font("Sans Serif", Font.PLAIN, 25));
		amount.setForeground(new Color(119, 141, 169));
		amount.setHorizontalAlignment(JTextField.CENTER);
		amount.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		amount.setOpaque(false);

		addBtn.setFont(new Font("Sans Serif", Font.PLAIN, 25));
		addBtn.setForeground(new Color(224, 225, 221));
		addBtn.setBorderPainted(false);
		addBtn.setFocusPainted(false);

		// set size and position
		setLayout(null);
		optionPanel = new MoneyOptionPanel(btnTextArr);

		bg.setBounds(0, 0, 450, 455);
		header.setBounds(135, 17, (int) header.getPreferredSize().getWidth(), 45);
		optionPanel.setBounds(0, 72, 450, 190);
		amount.setBounds(50, 300, 350, 50);
		addBtn.setBounds(50, 376, 350, 50);

		add(header);
		add(optionPanel);
		add(amount);
		add(addBtn);

		add(bg);

		// initially set the selectedBtn value
		selectedBtn = optionPanel.getBtnArr()[0];
	}

	// getters and setters
	public MoneyOptionPanel getOptionPanel() {
		return optionPanel;
	}

	public void setOptionPanel(MoneyOptionPanel optionPanel) {
		this.optionPanel = optionPanel;
	}

	public JLabel getHeader() {
		return header;
	}

	public void setHeader(JLabel header) {
		this.header = header;
	}

	public JTextField getAmount() {
		return amount;
	}

	public void setAmount(JTextField amount) {
		this.amount = amount;
	}

	public JButton getAddBtn() {
		return addBtn;
	}

	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}

	public String[] getBtnTextArr() {
		return btnTextArr;
	}

	public void setBtnTextArr(String[] btnTextArr) {
		this.btnTextArr = btnTextArr;
	}

	public JButton getSelectedBtn() {
		return selectedBtn;
	}

	public void setSelectedBtn(JButton selectedBtn) {
		this.selectedBtn = selectedBtn;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// When the text field is just being typed in
		if (e.getComponent() == amount) {
			if (amount.getText().equals("Amount ($)")) {
				amount.setText("");
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// If the text is empty, reset the filler
		if (e.getComponent() == amount) {
			if (amount.getText().trim().isEmpty()) {
				amount.setText("Amount ($)");
			}
		}
	}
}
