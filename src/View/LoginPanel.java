/* This class is used an object that holds all of the GUI components for the login screen
 */

package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class LoginPanel extends JPanel {

	// instance variables
	private JLabel bg = new JLabel(new ImageIcon("Images/LoginBG.png"));

	private JLabel logo = new JLabel(new ImageIcon("Images/Logo.png"));
	private JLabel welcome = new JLabel("Welcome Back!");
	private JLabel info = new JLabel("Log into an existing account");

	private PlaceholderTextField username = new PlaceholderTextField("");
	private PlaceholderTextField password = new PlaceholderTextField("");
	private JButton login = new JButton("Log In");

	private JButton signup = new JButton(
			"<html><font color=E0E1DD> Don't have an account? </font> <font            color=415A77> Sign Up</font></html>");

	// constructor to set the preferences for the GUI Components
	public LoginPanel() {
		// set preferences of JPanel
		setSize(1280, 720);
		setVisible(true);

		// set placeholder text
		username.setPlaceholder("Username");
		password.setPlaceholder("Password");

		// set fonts
		welcome.setFont(new Font("Sans Serif", Font.PLAIN, 28));
		info.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		username.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		password.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		login.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		signup.setFont(new Font("Sans Serif", Font.PLAIN, 23));

		// set color
		welcome.setForeground(new Color(65, 90, 119));
		info.setForeground(new Color(224, 225, 221));
		username.setForeground(new Color(119, 141, 169));
		password.setForeground(new Color(119, 141, 169));
		login.setForeground(new Color(252, 246, 245));

		// preferences of GUI components
		username.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		username.setOpaque(false);
		password.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		password.setOpaque(false);
		login.setBorderPainted(false);
		login.setFocusPainted(false);
		signup.setBorderPainted(false);
		signup.setFocusPainted(false);

		// set position and size
		setLayout(null);
		bg.setBounds(0, 0, 1280, 720);

		logo.setBounds(475, 23, 277, 224);
		welcome.setBounds(540, 225, 201, 45);
		info.setBounds(515, 270, 252, 30);

		username.setBounds(514, 333, 301, 50);
		password.setBounds(514, 415, 301, 50);
		login.setBounds(465, 497, 350, 50);

		signup.setBounds(445, 625, 393, 37);

		// add to the background
		bg.add(logo);
		bg.add(welcome);
		bg.add(info);

		bg.add(username);
		bg.add(password);
		bg.add(login);

		bg.add(signup);

		// add to JPanel
		add(bg);
	}

	// getters and setters
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

	public void setUsername(PlaceholderTextField username) {
		this.username = username;
	}

	public PlaceholderTextField getPassword() {
		return password;
	}

	public void setPassword(PlaceholderTextField password) {
		this.password = password;
	}

	public JButton getLogin() {
		return login;
	}

	public void setLogin(JButton login) {
		this.login = login;
	}

	public JButton getSignup() {
		return signup;
	}

	public void setSignup(JButton signup) {
		this.signup = signup;
	}
}