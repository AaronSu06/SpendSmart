/* This class acts as the holder for almost every single JPanel in the application.
 * It is the first class called and also sets the size and position of every single
 * JPanel, and then adds it to the mainframe via itself.
 */

package View;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DashboardPanel extends JPanel {

	// instance variables
	private DashboardButtonPanel dashboardBtn = new DashboardButtonPanel();
	private TotalExpensePanel totalExpense = new TotalExpensePanel();
	private TodayExpensePanel todayExpense = new TodayExpensePanel();
	private TotalIncomePanel totalIncome = new TotalIncomePanel();

	private SidePanel sidePanel = new SidePanel();
	private SurveyPanel surveyPanel = new SurveyPanel();
	private SurveyResultPanel surveyResult = new SurveyResultPanel();

	private JPanel[] panelArr = { totalExpense, todayExpense, totalIncome };

	private Map<JButton, JPanel> btnToPanel = new HashMap<JButton, JPanel>();

	// constructor to set the preferences for the GUI Components
	public DashboardPanel() {
		// set background color
		setBackground(new Color(27, 38, 59));

		// set size and position
		setLayout(null);
		sidePanel.setBounds(0, 0, 215, 720);
		dashboardBtn.setBounds(215, 0, 1065, 170);

		// add to the hashmap and disable all panels except for totalExpense
		for (int i = 0; i < 3; i++) {
			btnToPanel.put(dashboardBtn.getDashboardBtn()[i], panelArr[i]);
			panelArr[i].setBounds(0, 0, 1280, 720);
			panelArr[i].setVisible(false);

			// add to JPanel
			add(panelArr[i]);
		}

		// disable and add the survey panel
		surveyPanel.setBounds(215, 0, 1065, 720);
		surveyPanel.setVisible(false);
		add(surveyPanel);
		
		surveyResult.setBounds(215, 0, 1065, 720);
		surveyResult.setVisible(false);
		add(surveyResult);

		// initially set totalExpense to visible
		totalExpense.setVisible(true);

		// add to the JPanel
		add(sidePanel);
		add(dashboardBtn);
	}

	// getters and setters
	public SidePanel getSidePanel() {
		return sidePanel;
	}

	public JPanel[] getPanelArr() {
		return panelArr;
	}

	public void setPanelArr(JPanel[] panelArr) {
		this.panelArr = panelArr;
	}

	public void setSidePanel(SidePanel sidePanel) {
		this.sidePanel = sidePanel;
	}

	public SurveyPanel getSurveyPanel() {
		return surveyPanel;
	}

	public void setSurveyPanel(SurveyPanel surveyPanel) {
		this.surveyPanel = surveyPanel;
	}

	public DashboardButtonPanel getDashboardBtn() {
		return dashboardBtn;
	}

	public void setDashboardBtn(DashboardButtonPanel dashboardBtn) {
		this.dashboardBtn = dashboardBtn;
	}

	public TotalExpensePanel getTotalExpense() {
		return totalExpense;
	}

	public TodayExpensePanel getTodayExpense() {
		return todayExpense;
	}

	public void setTodayExpense(TodayExpensePanel todayExpense) {
		this.todayExpense = todayExpense;
	}

	public TotalIncomePanel getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(TotalIncomePanel totalIncome) {
		this.totalIncome = totalIncome;
	}

	public void setTotalExpense(TotalExpensePanel totalExpense) {
		this.totalExpense = totalExpense;
	}

	public Map<JButton, JPanel> getBtnToPanel() {
		return btnToPanel;
	}

	public void setBtnToPanel(Map<JButton, JPanel> btnToPanel) {
		this.btnToPanel = btnToPanel;
	}

	public SurveyResultPanel getSurveyResult() {
		return surveyResult;
	}

	public void setSurveyResult(SurveyResultPanel surveyResult) {
		this.surveyResult = surveyResult;
	}
}