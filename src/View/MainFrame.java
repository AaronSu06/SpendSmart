/* This class is used an object that holds all of the JPanels, adding them
 * to the JFrame itself.
 */

package View;

import javax.swing.JFrame;
import Controller.LoginController;

public class MainFrame extends JFrame {

	// instance variables
	private LoginController loginController = new LoginController();

	// constructor to set the preferences for the GUI Components. It will also add
	// all the JPanels to itself
	public MainFrame() {
		// preferences for the JFrame
		setSize(1280, 720);
		setResizable(false);
		setTitle("Spend Smart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		// add to the JFrame
		add(loginController.getLoginPanel());
		add(loginController.getSignupPanel());
		add(loginController.getDashboard().getDashboardPanel());

		loginController.getDashboard().getDashboardPanel().setVisible(false);
	}
}
