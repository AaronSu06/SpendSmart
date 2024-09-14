/* This class is used an object that holds all of the GUI components for the login screen
 */

package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignupPanel extends JPanel {

	// instance variables
	private JLabel bg = new JLabel(new ImageIcon("Images/SignupBG.png"));

	private JLabel logo = new JLabel(new ImageIcon("Images/Logo.png"));
	private JLabel welcome = new JLabel("Welcome!");
	private JLabel info = new JLabel("Sign up and create an account");

	private PlaceholderTextField username = new PlaceholderTextField("");
	private PlaceholderTextField password = new PlaceholderTextField("");
	private PlaceholderTextField confirm = new PlaceholderTextField("");
	private JButton signup = new JButton("Sign Up");

	private JButton login = new JButton(
			"<html><font color=E0E1DD> Already have an account? </font> <font            color=415A77> Log In</font></html>");

	// constructor to set the preferences for the GUI Components
	public SignupPanel() {
		// set preferences of JPanel
		setSize(1280, 720);
		setVisible(true);

		// set placeholder text
		username.setPlaceholder("Username");
		password.setPlaceholder("Password");
		confirm.setPlaceholder("Re-Enter Password");

		// set fonts
		welcome.setFont(new Font("Sans Serif", Font.PLAIN, 27));
		info.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		username.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		password.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		confirm.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		signup.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		login.setFont(new Font("Sans Serif", Font.PLAIN, 22));

		// set color
		welcome.setForeground(new Color(65, 90, 119));
		info.setForeground(new Color(224, 225, 221));
		username.setForeground(new Color(119, 141, 169));
		password.setForeground(new Color(119, 141, 169));
		confirm.setForeground(new Color(119, 141, 169));
		signup.setForeground(new Color(252, 246, 245));

		// preferences of GUI components
		username.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		username.setOpaque(false);
		password.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		password.setOpaque(false);
		confirm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		confirm.setOpaque(false);
		signup.setBorderPainted(false);
		signup.setFocusPainted(false);
		login.setBorderPainted(false);
		login.setFocusPainted(false);

		// set position and size
		setLayout(null);
		bg.setBounds(0, 0, 1280, 720);

		logo.setBounds(475, 23, 277, 224);
		welcome.setBounds(574, 225, 128, 45);
		info.setBounds(505, 270, 269, 30);

		username.setBounds(514, 333, 301, 50);
		password.setBounds(514, 415, 301, 50);
		confirm.setBounds(514, 497, 301, 50);
		signup.setBounds(465, 579, 350, 50);

		login.setBounds(446, 643, 393, 37);

		// add to the background
		bg.add(logo);
		bg.add(welcome);
		bg.add(info);

		bg.add(username);
		bg.add(password);
		bg.add(confirm);
		bg.add(login);

		bg.add(signup);

		// add to JPanel
		add(bg);
	}

	// getters and setters
	public PlaceholderTextField getPassword() {
		return password;
	}

	public void setPassword(PlaceholderTextField password) {
		this.password = password;
	}

	public PlaceholderTextField getConfirm() {
		return confirm;
	}

	public void setConfirm(PlaceholderTextField confirm) {
		this.confirm = confirm;
	}

	public void setUsername(PlaceholderTextField username) {
		this.username = username;
	}

	public JLabel getBg() {
		return bg;
	}

	public void setBg(JLabel bg) {
		this.bg = bg;
	}

	public JLabel getLogo() {
		return logo;
	}

	public void setLogo(JLabel logo) {
		this.logo = logo;
	}

	public JLabel getWelcome() {
		return welcome;
	}

	public void setWelcome(JLabel welcome) {
		this.welcome = welcome;
	}

	public JLabel getInfo() {
		return info;
	}

	public void setInfo(JLabel info) {
		this.info = info;
	}

	public PlaceholderTextField getUsername() {
		return username;
	}

	public JButton getSignup() {
		return signup;
	}

	public void setSignup(JButton signup) {
		this.signup = signup;
	}

	public JButton getLogin() {
		return login;
	}

	public void setLogin(JButton login) {
		this.login = login;
	}
}
