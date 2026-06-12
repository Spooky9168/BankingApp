import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame frame = new JFrame("Stock Taker");
    public static JPanel panel = new JPanel();
    // item container, where all stockitems appear

    public static JPanel stockPane = new JPanel();

    // inputs container, where all inputs appear
    public static JPanel inputsContainer = new JPanel();

    // adds inputs -> input container
    public static PlaceholderTextField codeInput = new PlaceholderTextField("0000 (code)");
    public static PlaceholderTextField quantityInput = new PlaceholderTextField("0-100 (quantity)");
    public static PlaceholderTextField priceInput = new PlaceholderTextField("0.00 (price)");
    public static PlaceholderTextField nameInput = new PlaceholderTextField("name");
    public static PlaceholderTextField descriptionInput = new PlaceholderTextField("description");
    public static JButton submitInput = new JButton("↑");

    public static void main(String[] args) {
        // window initiation

        frame.setMinimumSize(new Dimension(830  ,200));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // content initiation
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        stockPane.setLayout(new BoxLayout(stockPane, BoxLayout.Y_AXIS));

        JScrollPane stockContainer = new JScrollPane(stockPane);
        stockContainer.setPreferredSize(new Dimension(0, 300));
        stockContainer.setSize(new Dimension(100, 50));
        stockContainer.setBorder(BorderFactory.createLoweredBevelBorder());

        inputsContainer.setMaximumSize(new Dimension(1000, 10));

        // submit button listener
        submitInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    try {
                        String code = codeInput.getText().trim();
                        double price = Double.parseDouble(priceInput.getText().trim());
                        String name = nameInput.getText().trim();
                        String description = descriptionInput.getText().trim();
                        int quantity = Integer.parseInt(quantityInput.getText().trim());

                        if (quantity > 100 || 66 < 0) {
                            JOptionPane.showMessageDialog(null, "Quantity may only be between 0 and 100", "Quantity error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            StockItem newItem = new StockItem(code, quantity, price, name, description);
                            stockPane.add(newItem);
                        }

                        // input clears
                        codeInput.setText("");
                        quantityInput.setText("");
                        priceInput.setText("");
                        nameInput.setText("");
                        descriptionInput.setText("");

                        stockPane.revalidate();
                        stockPane.repaint();
                    } catch (NumberFormatException ex) {
                        //catch errors here
                        JOptionPane.showMessageDialog(null, "Enter valid numbers for quantity and price", "Input error", JOptionPane.ERROR_MESSAGE);
                    }

            }
        });

        inputsContainer.add(nameInput);
        inputsContainer.add(descriptionInput);
        inputsContainer.add(codeInput);
        inputsContainer.add(quantityInput);
        inputsContainer.add(priceInput);
        inputsContainer.add(submitInput);

        // adds item and input container to main content panel
        panel.add(stockContainer);
        panel.add(inputsContainer);

        // finalise frame
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
