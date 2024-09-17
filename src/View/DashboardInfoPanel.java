/* This class acts as the holder for all of the components towards displaying buttons for
 * the dashboard panel.
 */

package View;

import java.awt.FlowLayout;
import javax.swing.JPanel;

public class DashboardInfoPanel extends JPanel {

	// instance variables
	private DashboardInfoObj[] dashboardInfo = new DashboardInfoObj[3];
	private String[] headerArr = { "Emergency Funds", "Today's Net Total", "Lifetime Net Total" };

	// constructor to set the preferences for the GUI Components
	public DashboardInfoPanel() {
		// flow layout to pack horizontally
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 30));

		for (int i = 0; i < 3; i++) {
			// create the object
			dashboardInfo[i] = new DashboardInfoObj(headerArr[i], 0);

			// center and add the object
			dashboardInfo[i].setAlignmentX(CENTER_ALIGNMENT);
			add(dashboardInfo[i]);
		}

		setOpaque(false);
	}

	// getters and setters
	public DashboardInfoObj[] getDashboardInfo() {
		return dashboardInfo;
	}

	public void setDashboardInfo(DashboardInfoObj[] dashboardInfo) {
		this.dashboardInfo = dashboardInfo;
	}

	public String[] getHeaderArr() {
		return headerArr;
	}

	public void setHeaderArr(String[] headerArr) {
		this.headerArr = headerArr;
	}
}
