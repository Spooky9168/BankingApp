// MAKE SURE YOU'RE FOLLOWING PEP8 OR SOMETHING GUIDELINES

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockItem extends JPanel{ //stockcode, quantity, price
    String stockCode = "";
    int quantity = 0;
    double price = 0;
    String name = "Unknown Stock Name";
    String description = "Unknown Stock Description";
    
    double VAT = 20;

    // constructor method
    public StockItem(String code, int quantity, double price, String name, String description) {
        this.stockCode = code;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.description = description;

        // assign StockItem values to JSwing elements
        JPanel content = new JPanel();

        JLabel codeLabel = new JLabel("#" + code);
        JLabel quantityLabel = new JLabel(String.valueOf(quantity));

        //JPanel inputsContainer = new JPanel(); //holds two buttons to increase and decrease quantity by 1
        JButton increaseButton = new JButton("add");
        JButton decreaseButton = new JButton("sell");
        JButton editButton = new JButton("⚙\uFE0F");
        JButton deleteButton = new JButton("✕");

        increaseButton.setMaximumSize(new Dimension(50,50));
        decreaseButton.setMaximumSize(new Dimension(    50,50));
        editButton.setMaximumSize(new Dimension(50,50));

        // added increase decrease button functionality
        increaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStock(1, quantityLabel);
            };
        });
        decreaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sellStock(1, quantityLabel);
            };
        });

        JLabel priceLabel = new JLabel("£" + String.valueOf(price));
        JLabel nameLabel = new JLabel(name);
        JLabel descriptionLabel = new JLabel(description);

        // edit button, moves item data back to the editor
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passToInput(Main.codeInput, Main.quantityInput, Main.priceInput, Main.nameInput, Main.descriptionInput);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // set border around panel objects
        content.setBorder(BorderFactory.createEtchedBorder());

        // adds label elements to content panel
        content.add(nameLabel);
        content.add(descriptionLabel);
        content.add(codeLabel);
        content.add(quantityLabel);
        content.add(priceLabel);

        content.add(increaseButton);
        content.add(decreaseButton);
        content.add(editButton);
        content.add(deleteButton);


        add(content);
        setVisible(true);
    }

    // set methods
    public void setCode(String code) {
        this.stockCode = code;
    } public void setQuantity(int quantity) {
        if (quantity <= 100 || quantity <= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("ERROR: Stock must range from 0 to 100 and not beyond.");
        }
    } public void setPrice(double price) {
        this.price = price;
    } public void setName(String name) {
        this.name = name;
    } public void setDescription(String description) {
        this.description = description;
    }

    // get
    public String getCode() {
        if (this.stockCode.length() > 0) {
            return this.stockCode;
        } else {
            return "Unknown Stock Code";
    }} public String getQuantity() {
        if (String.valueOf(this.quantity).length() > 0) {
            return String.valueOf(this.quantity); // REMEMBER TO CAST BACK INTO INT IF NEEDED
        } else {
            return "Unknown Quantity";
    }} public String getPrice() {
        if (String.valueOf(this.price).length() > 0) {
            return String.valueOf(this.price); // REMEMBER TO CAST BACK INTO INT IF NEEDED
        } else {
            return "Unknown Price";
    }} public String getStockName() {
        if (this.name.length() > 0) {
            return this.name;
        } else {
            return "Unknown Stock Name";
    }} public String getStockDescription() {
        if (this.description.length() > 0) {
            return this.description;
        } else {
            return "Unknown Description";
    }}


    // stock manipulation methods
    public void addStock(int num, JLabel label) { //((x+=num)>=100 || (x+=num)<=0)
        int x = this.quantity + num;
        if (x>=0 && x<=100)  {
            this.quantity += num;
            label.setText(String.valueOf(this.quantity));
        } else { //must apply appropriate error prompt
            System.out.println("ERROR: Stock must range from 0 to 100 and not beyond.");
    }} public void sellStock(int num, JLabel label) {
        int x = this.quantity - num;
        if (x>=0 && x<=100)  {
            this.quantity -= num;
            label.setText(String.valueOf(this.quantity));
        } else { //must apply appropriate error prompt
            System.out.println("ERROR: Stock must range from 0 to 100 and not beyond.");
    }} //the above methods could return true or false upon working or not

    // VAT methods
    public double getVAT() {
        return VAT;
    } public String getVATPrice() {
        if (String.valueOf(this.price).length() > 0) {
            return String.valueOf((1 + VAT/100) * this.price); // REMEMBER TO CAST BACK INTO INT IF NEEDED
        } else {
            return "Unknown Price";
    }}

    public String toString() { 
        // returns stock code, name, description, quantity, pre-VAT price, post-VAT price
        String list = stockCode + " " + name + " " + description + " " + String.valueOf(quantity) + " " + String.valueOf(price) + " " + String.valueOf(getVATPrice());
        // , 
        return list;
    }

    public void passToInput(PlaceholderTextField code, PlaceholderTextField quantity, PlaceholderTextField price, PlaceholderTextField name, PlaceholderTextField description) {
        code.setText(getCode());
        quantity.setText(getQuantity());
        price.setText(getPrice());
        name.setText(getStockName());
        description.setText(getStockDescription());
        setVisible(false);
    }
}

