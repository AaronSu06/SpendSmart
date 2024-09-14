/* sourced from: https://stackoverflow.com/questions/16213836/java-swing-jtextfield-set-placeholder
 * 
 * This class is used to create an object of type JTextField, adding in a unique feature for 
 * placeholder text
 */

package View;
import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;

public class PlaceholderTextField extends JTextField {

	// instance variables
    private String placeholder;

    // multiple constructors of different types
    public PlaceholderTextField() {
    }

    public PlaceholderTextField(
        final Document pDoc,
        final String pText,
        final int pColumns)
    {
        super(pDoc, pText, pColumns);
    }

    // getters and setters
    public PlaceholderTextField(final int pColumns) {
        super(pColumns);
    }

    public PlaceholderTextField(final String pText) {
        super(pText);
    }

    public PlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(119, 141, 169));
        g.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        g.drawString(placeholder, getInsets().left, (int) (pG.getFontMetrics()
            .getHeight() * 1.475));
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}