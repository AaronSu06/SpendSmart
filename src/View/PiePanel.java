/* sourced from: https://www.vogella.com/tutorials/JFreeChart/article.html
 * 
 * This class is used to create an object of the 3D Pie Graph using JFreeChart. It extends JPanel so that
 * it can be used as a object.
 */

package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PiePanel extends JPanel {

	// instance variables
	private Map<String, Double> pieInfo;
	private PieDataset dataSet = null;
	private JLabel headerLabel = new JLabel();
	private JLabel noInfoText = new JLabel("No data yet...");
	private ChartPanel chartPanel = null;
	private PiePlot3D plot = null;

	// constructor to set the preferences for the GUI Components
	public PiePanel(String header, Map<String, Double> pieInfo) {	
		// update value with given
		this.pieInfo = pieInfo;
		
		headerLabel.setText(header);

		// set background color
		setBackground(new Color(32, 45, 70));

		// set preferences for the text
		headerLabel.setFont(new Font("Sans Serif", Font.BOLD, 23));
		headerLabel.setForeground(new Color(224, 225, 221));

		noInfoText.setFont(new Font("Sans Serif", Font.BOLD, 23));
		noInfoText.setForeground(new Color(224, 225, 221));
	}

	// getters and setters
	public Map<String, Double> getPieInfo() {
		return pieInfo;
	}

	public void setPieInfo(Map<String, Double> pieInfo) {
		this.pieInfo = pieInfo;
	}

	public PieDataset getDataSet() {
		return dataSet;
	}

	public void setDataSet(PieDataset dataSet) {
		this.dataSet = dataSet;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}

	public PiePlot3D getPlot() {
		return plot;
	}

	public void setPlot(PiePlot3D plot) {
		this.plot = plot;
	}

	public JLabel getNoInfoText() {
		return noInfoText;
	}

	public void setNoInfoText(JLabel noInfoText) {
		this.noInfoText = noInfoText;
	}

	// method to fill the data
	public PieDataset fillData(Map<String, Double> pieInfo) {
		DefaultPieDataset result = new DefaultPieDataset();

		if (pieInfo != null) {
			// loop through the map using a for-each
			for (Map.Entry<String, Double> map : pieInfo.entrySet()) {
				result.setValue(map.getKey(), map.getValue());
			}
		}

		return result;
	}

	// method to create the chart
	public JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);

		// visual settings for the graph
		plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(0);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setOutlinePaint(null);
		plot.setBackgroundPaint(null);
		plot.setLabelGenerator(null);

		// loop through the map using a for-each
		if (pieInfo != null) {
			for (Map.Entry<String, Double> map : pieInfo.entrySet()) {
				plot.setSectionOutlinePaint(map.getKey(), new Color(224, 225, 221));
			}
		}

		chart.setBackgroundPaint(new Color(32, 45, 70));
		chart.getLegend().setBackgroundPaint(new Color(32, 45, 70));
		chart.getLegend().setItemPaint(new Color(224, 225, 221));
		return chart;
	}

	// method to display the chart (after it has been initialized)
	public void displayChart() {
		// create the chart (must use ChartPanel in order to hold the chart... add
		// ChartPanel to the JPanel)
		dataSet = fillData(pieInfo);
		JFreeChart chart = createChart(dataSet, "");

		// set size and position
		chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(null);
		chartPanel.setPreferredSize(new Dimension(396, 390));

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		headerLabel.setAlignmentX(CENTER_ALIGNMENT);

		// add to the JPanel
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(headerLabel);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(chartPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
	}
	
	// display no data message
	public void addNoInfo() {
		chartPanel.setLayout(new BorderLayout());
		chartPanel.add(noInfoText, BorderLayout.CENTER);
		noInfoText.setHorizontalAlignment(noInfoText.CENTER);
	}
}
