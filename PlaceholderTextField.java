//follows JavaSpring Guide: "How to Set a Placeholder in Java Swing JTextField: Step-by-Step Guide with Code"
//https://www.javaspring.net/blog/java-swing-jtextfield-set-placeholder/#why-jtextfield-lacks-built-in-placeholders

import javax.swing.*;
import java.awt.*;

public class PlaceholderTextField extends JTextField {
    private String placeholder;
    private Color placeholderColor = Color.GRAY; // Default placeholder color

    // Constructor
    public PlaceholderTextField(String text) {
        super(13);
        this.placeholder = text;
    }

    // Getters and Setters
    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint(); // Redraw component when placeholder changes
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Draw the default text field (background, border, etc.)

        // Draw placeholder only if conditions are met
        if (placeholder != null && !placeholder.isEmpty() && getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create(); // Create a copy of the graphics context
            g2.setColor(placeholderColor); // Set placeholder color

            // Get font metrics to position the text correctly
            FontMetrics fm = g2.getFontMetrics();
            int x = getInsets().left; // Left padding
            int y = fm.getAscent() + getInsets().top; // Vertical position (top padding + font ascent)

            // Draw the placeholder text
            g2.drawString(placeholder, x, y);
            g2.dispose(); // Clean up the graphics context
        }
    }
}