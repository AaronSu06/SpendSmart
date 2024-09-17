/* This class acts as the holder for all of the components towards displaying buttons for
 * the dashboard panel.
 */

package View;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class DashboardButtonPanel extends JPanel {

	// instance variables
	private DashboardButtonObj[] dashboardBtn = new DashboardButtonObj[3];
	private String[] headerArr = { "Total Expenses", "Todays Expenses", "Total Income" };

	// constructor to set the preferences for the GUI Components
	public DashboardButtonPanel() {
		// flow layout to pack horizontally
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
		
		for (int i = 0; i < 3; i++) {
			// create the object
			dashboardBtn[i] = new DashboardButtonObj(headerArr[i]);
			
			// center and add the object
			dashboardBtn[i].setAlignmentX(CENTER_ALIGNMENT);
			add(dashboardBtn[i]);
		}
		
		// initially set the total expense dashboard button to white
		dashboardBtn[0].getHeader().setForeground(new Color(224, 225, 221));
		dashboardBtn[0].getPriceText().setForeground(new Color(224, 225, 221));
		
		setOpaque(false);
	}

	// getters and setters
	public DashboardButtonObj[] getDashboardBtn() {
		return dashboardBtn;
	}

	public void setDashboardBtn(DashboardButtonObj[] dashboardBtn) {
		this.dashboardBtn = dashboardBtn;
	}

	public String[] getHeaderArr() {
		return headerArr;
	}

	public void setHeaderArr(String[] headerArr) {
		this.headerArr = headerArr;
	}
}