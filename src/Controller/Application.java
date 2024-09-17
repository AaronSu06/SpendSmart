/* Aaron Su
 * Friday, January 19, 2024
 * ICS4U1-02, Mr.Fernandes
 * SpendSmart - Aaron Su
 * 
 * Description:
 * This Java application is designed to help high-school students / post-secondary students 
 * budget their finances by tracking their income and expenses. Additionally, the application
 * will provide a section to rate the users' knowledge on finance and give them resources based
 * on their results.
 * 
 * Features:
 * Real-time information updating, re-direct to resource origin on click, usage of JFreeChart
 * and Gson to properly save and display data, Algorithm to display resources corresponding
 * to the users responses in the survey, fully functional GUI
 * 
 * Major Skills:
 * Usage of Object-Oriented Programming, Loops, Hashmaps, Arrays / ArrayLists, APIs such as JFreeChart, Gson,
 * Date, Java Swing Components
 * 
 * Areas of Concern:
 * If the resource of origin is taken down by the host, the code may bug out. Additionally, the JFreeChart
 * I am using is an old version because the new one has removed the 3D bar graph. For this reason, the 
 * information on the axis may be low quality.
 */

package Controller;

import View.MainFrame;

public class Application {

	// start the application
	public static MainFrame mainFrame = new MainFrame();

	public static void main(String[] args) {

	}

}
