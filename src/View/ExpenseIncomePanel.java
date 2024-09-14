/* sourced from:http://www.java2s.com/Code/Java/Chart/JFreeChartBarChart3DDemo3withitemlabelsdisplayed.htm
 * 
 * This class is used to create an object of the 3D Bar Graph using JFreeChart. It extends JPanel so that
 * it can be used as a object.
 */

package View;

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
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ExpenseIncomePanel extends JPanel {

	// instance variables
	private CategoryDataset dataSet = null;
	private JLabel totalExpenseGraph = new JLabel("Total Expenses vs Total Income");
	private Map<Double, String> graphInfo;
	private ChartPanel chartPanel = null;
	private CategoryPlot plot = null;

	// constructor to set the preferences of the GUI components
	public ExpenseIncomePanel() {
		// set background color
		setBackground(new Color(32, 45, 70));

		// set preferences for the headers
		totalExpenseGraph.setFont(new Font("Sans Serif", Font.BOLD, 23));
		totalExpenseGraph.setForeground(new Color(224, 225, 221));

		// create the chart (must use ChartPanel in order to hold the chart... add
		// ChartPanel to the JPanel)
		dataSet = fillData(0, 0);
		JFreeChart chart = createChart(dataSet, "");

		// set size and position
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(590, 450));

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		totalExpenseGraph.setAlignmentX(CENTER_ALIGNMENT);

		add(Box.createRigidArea(new Dimension(0, 10)));
		add(totalExpenseGraph);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(chartPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
	}

	// getters and setters
	public CategoryDataset getDataSet() {
		return dataSet;
	}

	public void setDataSet(CategoryDataset dataSet) {
		this.dataSet = dataSet;
	}

	public Map<Double, String> getGraphInfo() {
		return graphInfo;
	}

	public void setGraphInfo(Map<Double, String> graphInfo) {
		this.graphInfo = graphInfo;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}

	public CategoryPlot getPlot() {
		return plot;
	}

	public void setPlot(CategoryPlot plot) {
		this.plot = plot;
	}

	// method to fill the data
	public CategoryDataset fillData(double graph1, double graph2) {
		DefaultCategoryDataset result = new DefaultCategoryDataset();

		// create an series, and fill it with info
		result.addValue(graph1, "Income", "");
		result.addValue(graph2, "Expense", "");

		// return
		return result;
	}

	// method to create the chart
	public JFreeChart createChart(CategoryDataset dataset, String title) {
		// objects
		JFreeChart chart = ChartFactory.createBarChart3D(title, "Category", "Total ($)", dataset,
				PlotOrientation.VERTICAL, true, false, false);

		plot = chart.getCategoryPlot();
		CategoryItemRenderer renderer = plot.getRenderer();

		// visual settings for the graph
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		
		// enable the counter on the top of the graph
		
		for (int i = 0; i < 2; i++) {
			renderer.setSeriesItemLabelGenerator(i, new StandardCategoryItemLabelGenerator());
			renderer.setSeriesItemLabelFont(i, new Font("Sans Serif", Font.BOLD, 16));
//			renderer.setSeriesItemLabelPaint(i, new Color(224, 225, 221));
		}
		renderer.setBaseItemLabelsVisible(true);
		
		plot.setForegroundAlpha(1f);
		plot.setOutlinePaint(null);
		plot.setBackgroundPaint(null);
		plot.setRenderer(renderer);

		plot.setRangeGridlinePaint(new Color(224, 225, 221));
		plot.getRangeAxis().setAxisLinePaint(new Color(224, 225, 221));
		plot.getRangeAxis().setLabelPaint(new Color(224, 225, 221));
		plot.getRangeAxis().setTickLabelPaint(new Color(224, 225, 221));
		plot.getRangeAxis().setTickMarkPaint(new Color(224, 225, 221));

		plot.setDomainGridlinePaint(new Color(224, 225, 221));
		plot.getDomainAxis().setAxisLinePaint(new Color(224, 225, 221));
		plot.getDomainAxis().setLabelPaint(new Color(224, 225, 221));
		plot.getDomainAxis().setTickLabelPaint(new Color(224, 225, 221));
		plot.getDomainAxis().setTickMarkPaint(new Color(224, 225, 221));

		chart.setBackgroundPaint(new Color(32, 45, 70));
		chart.getLegend().setBackgroundPaint(new Color(32, 45, 70));
		chart.getLegend().setItemPaint(new Color(224, 225, 221));

		return chart;
	}
}
