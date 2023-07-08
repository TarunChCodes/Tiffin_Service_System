package TiffinSerSys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class PanelClass implements ActionListener {

    public JPanel vegtiffinPanel, nonvegtiffinPanel, packeditemPanel, headingPanel;
    public JLabel headingLabel;

    //    -- Component for use in Tiffin Panels --

    JComboBox<String> boxnumComboBox, tiffinqtyCommboBox, box1cb, box2cb, box2cb_nonveg, box3cb, box4cb;
    JLabel b4, box1priceLabel, box2priceLabel, box2price_nonvegLabel, box3priceLabel, box4priceLabel;
    JButton addVegTiffiCartButtton, addNonvegTiffinCartButton, cancelButton;

//    -- Component use for PackedItem Panel --

    JLabel iconItem1Label, iconItem2Label, iconItem3Label, iconItem4Label;
    JLabel nameItem1Label, nameItem2Label, nameItem3Label, nameItem4Label;
    JComboBox<String> packeditem1Cb, packeditem2Cb, packeditem3Cb, packeditem4Cb;
    JLabel item1PriceLabel, item2PriceLabel, item3PriceLabel,item4PriceLabel;
    JComboBox<String> qtyitem1Cb, qtyitem2Cb, qtyitem3Cb, qtyitem4Cb;
    JButton additem1Button, additem2Button, additem3Button, additem4Button;

//    -- Use for Database --

    Database db = new Database();
    ResultSet rs;
    // tiffin Data Variable
    String[] box1item = new String[4];
    String[] box2item = new String[4];
    String[] box2item_nonveg = new String[4];
    String[] box3item = new String[4];
    String[] box4item = new String[4];
    String[] box1price = new String[4];
    String[] box2price = new String[4];
    String[] box2price_nonveg = new String[4];
    String[] box3price = new String[4];
    String[] box4price = new String[4];
    // packedItem Data
    String[] packeditem1 = new String[3];
    String[] packeditem2 = new String[3];
    String[] packeditem3 = new String[3];
    String[] packeditem4 = new String[3];
    String[] packeditem1Price = new String[3];
    String[] packeditem2Price = new String[3];
    String[] packeditem3Price = new String[3];
    String[] packeditem4Price = new String[3];

    String cust_id;
    public PanelClass(String menuStr,String id) {
        cust_id = id;

        headingPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/Logo.png"));
                Image i = img.getImage();
                g.drawImage(i, 0, 0, 200, this.getHeight(), this);

                ImageIcon img2 = new ImageIcon(ClassLoader.getSystemResource("icons/HeadingBG.jpg"));
                Image i2 = img2.getImage();
                g.drawImage(i2, 200, 0, 800, this.getHeight(), this);

            }
        };
        headingPanel.setSize(1000,100);
        headingPanel.setLayout(null);
        headingPanel.setVisible(true);

        headingLabel = new JLabel();
        headingLabel.setBounds(450,30,400,30);
        headingLabel.setFont(new Font("Georgia",Font.BOLD,30));
        headingLabel.setForeground(new Color(252, 109, 0));
        headingPanel.add(headingLabel);

        vegtiffinPanel = new JPanel();
        vegtiffinPanel.setSize(1000,725);
        vegtiffinPanel.setLayout(null);
        vegtiffinPanel.setVisible(true);
        vegtiffinPanel.setBackground(new Color(253, 185, 103));

        nonvegtiffinPanel = new JPanel();
        nonvegtiffinPanel.setSize(1000,725);
        nonvegtiffinPanel.setLayout(null);
        nonvegtiffinPanel.setBackground(new Color(176, 208, 71));

        packeditemPanel = new JPanel();
        packeditemPanel.setSize(1000,725);
        packeditemPanel.setLayout(null);
        packeditemPanel.setBackground(Color.WHITE);


//        This following block of statement helps to retrieve the data for the ComboBox of Boxes into the Array of String

//        Here tiffinBox and there  PRICE are retrieve from the DB in the String

        try {
            rs = db.s.executeQuery("select * from tiffin");
            int i = 0;
            while(rs.next()){

                box1item[i] = rs.getString("box1");
                box2item[i] = rs.getString("box2");
                box2item_nonveg[i] = rs.getString("box2nonveg");
                box3item[i] = rs.getString("box3");
                box4item[i] = rs.getString("box4");
                box1price[i] = rs.getString("box1_itemprice");
                box2price[i] = rs.getString("box2_itemprice");
                box2price_nonveg[i] = rs.getString("box2nonveg_itemprice");
                box3price[i] = rs.getString("box3_itemprice");
                box4price[i] = rs.getString("box4_itemprice");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Here is the Packed Item and There Price are retrieve from the database.

        try {
            rs = db.s.executeQuery("select * from packed");
            int i=0;

            while(rs.next()) {
                packeditem1[i] = rs.getString("icecream");
                packeditem1Price[i] = rs.getString("icecream_price");
                packeditem2[i] = rs.getString("colddrink");
                packeditem2Price[i] = rs.getString("colddrink_price");
                packeditem3[i] = rs.getString("biscuit");
                packeditem3Price[i] = rs.getString("biscuit_price");
                packeditem4[i] = rs.getString("namkeen");
                packeditem4Price[i] = rs.getString("namkeen_price");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        --- Tiffin Panel Components ---

        JLabel numOfBoxLabel = new JLabel(" Box Qty in Tiffin : ");
        numOfBoxLabel.setBounds(10,150,150,20);
        numOfBoxLabel.setFont(new Font("Arial",Font.BOLD,16));

        JLabel qtyLabel = new JLabel(" Tiffin Qty :");
        qtyLabel.setFont(new Font("",Font.BOLD,16));
        qtyLabel.setBounds(350,150,100,20);

        String[] boxnum =  {"4","3"};
        boxnumComboBox = new JComboBox<>(boxnum);
        boxnumComboBox.setBounds(180,150,60,20);
        boxnumComboBox.setFont(new Font("Arial",Font.PLAIN,14));

        String[] tiffinnum = {"1","2","3","4","5"};
        tiffinqtyCommboBox = new JComboBox<>(tiffinnum);
        tiffinqtyCommboBox.setBounds(450,150,60,20);
        tiffinqtyCommboBox.setFont(new Font("Arial",Font.PLAIN,14));

        JLabel boxesLabel = new JLabel("Choose the Food Items for the Respective Boxes");
        boxesLabel.setBounds(10,210,400,20);
        boxesLabel.setFont(new Font("Arial",Font.BOLD,16));

        JLabel b1 = new JLabel("Box 1");
        b1.setBounds(20,250,50,20);
        b1.setFont(new Font("",Font.PLAIN,16));

        JLabel b2 = new JLabel("Box 2");
        b2.setBounds(460,250,50,20);
        b2.setFont(new Font("",Font.PLAIN,16));

        JLabel b3 = new JLabel("Box 3");
        b3.setBounds(20,350,50,20);
        b3.setFont(new Font("",Font.PLAIN,16));

        b4 = new JLabel("Box 4");
        b4.setBounds(460,350,50,20);
        b4.setFont(new Font("",Font.PLAIN,16));

        box1cb = new JComboBox<>(box1item);
        box1cb.setBounds(80,250,130,25);
        box1cb.setFont(new Font("Arial",Font.PLAIN,14));

        box2cb = new JComboBox<>(box2item);
        box2cb.setBounds(520,250,130,25);
        box2cb.setFont(new Font("Arial",Font.PLAIN,14));

        box2cb_nonveg = new JComboBox<>(box2item_nonveg);
        box2cb_nonveg.setBounds(520,250,130,25);
        box2cb_nonveg.setFont(new Font("Arial",Font.PLAIN,14));

        box3cb = new JComboBox<>(box3item);
        box3cb.setBounds(80,350,130,25);
        box3cb.setFont(new Font("Arial",Font.PLAIN,14));

        box4cb = new JComboBox<>(box4item);
        box4cb.setBounds(520,350,130,25);
        box4cb.setFont(new Font("Arial",Font.PLAIN,14));

        // Price Label

        box1priceLabel = new JLabel("₹"+box1price[0]); // "₹"+box1price[0]
        box1priceLabel.setBounds(230,250,100,25);
        box1priceLabel.setFont(new Font("Arial",Font.PLAIN,14));

        box2priceLabel = new JLabel( "₹"+box2price[0]); // "₹"+box2price[0]
        box2priceLabel.setBounds(670,250,100,25);
        box2priceLabel.setFont(new Font("Arial",Font.PLAIN,14));

        box2price_nonvegLabel = new JLabel("₹"+box2price_nonveg[0]); // "₹"+box2price_nonveg[0]
        box2price_nonvegLabel.setBounds(670,250,100,25);
        box2price_nonvegLabel.setFont(new Font("Arial",Font.PLAIN,14));

        box3priceLabel = new JLabel("₹"+box3price[0]); //"₹"+box3price[0]
        box3priceLabel.setBounds(230,350,100,25);
        box3priceLabel.setFont(new Font("Arial",Font.PLAIN,14));

        box4priceLabel = new JLabel("₹"+box4price[0]); //"₹"+box4price[0]
        box4priceLabel.setBounds(670,350,100,25);
        box4priceLabel.setFont(new Font("Arial",Font.PLAIN,14));

//        Packeditem Panel component

        ImageIcon item1Img1 = new ImageIcon(ClassLoader.getSystemResource("icons/icecream.png"));
        Image item1Img2 = item1Img1.getImage().getScaledInstance(150,140,Image.SCALE_SMOOTH);
        ImageIcon item1Img3 = new ImageIcon(item1Img2);

        ImageIcon item2Img1 = new ImageIcon(ClassLoader.getSystemResource("icons/coldrink.png"));
        Image item2Img2 = item2Img1.getImage().getScaledInstance(150,140,Image.SCALE_SMOOTH);
        ImageIcon item2Img3 = new ImageIcon(item2Img2);

        ImageIcon item3Img1 = new ImageIcon(ClassLoader.getSystemResource("icons/biscuit.png"));
        Image item3Img2 = item3Img1.getImage().getScaledInstance(150,140,Image.SCALE_SMOOTH);
        ImageIcon item3Img3 = new ImageIcon(item3Img2);

        ImageIcon item4Img1 = new ImageIcon(ClassLoader.getSystemResource("icons/namkeen.png"));
        Image item4Img2 = item4Img1.getImage().getScaledInstance(150,140,Image.SCALE_SMOOTH);
        ImageIcon item4Img3 = new ImageIcon(item4Img2);

        iconItem1Label = new JLabel(item1Img3);
        iconItem1Label.setBounds(100,150,150,140);
        iconItem1Label.setHorizontalAlignment(JLabel.CENTER);

        iconItem2Label = new JLabel(item2Img3);
        iconItem2Label.setBounds(300,150,150,140);
        iconItem2Label.setHorizontalAlignment(JLabel.CENTER);

        iconItem3Label = new JLabel(item3Img3);
        iconItem3Label.setBounds(500,150,150,140);
        iconItem3Label.setHorizontalAlignment(JLabel.CENTER);

        iconItem4Label = new JLabel(item4Img3);
        iconItem4Label.setBounds(700,150,150,140);
        iconItem4Label.setHorizontalAlignment(JLabel.CENTER);

        nameItem1Label = new JLabel("Ice Cream");
        nameItem1Label.setBounds(100,300,150,25);
        nameItem1Label.setFont(new Font("Arial",Font.BOLD,18));
        nameItem1Label.setHorizontalAlignment(JLabel.CENTER);

        nameItem2Label = new JLabel("Cold Drinks");
        nameItem2Label.setBounds(300,300,150,25);
        nameItem2Label.setFont(new Font("Arial",Font.BOLD,18));
        nameItem2Label.setHorizontalAlignment(JLabel.CENTER);

        nameItem3Label = new JLabel("Biscuit");
        nameItem3Label.setBounds(500,300,150,25);
        nameItem3Label.setFont(new Font("Arial",Font.BOLD,18));
        nameItem3Label.setHorizontalAlignment(JLabel.CENTER);

        nameItem4Label = new JLabel("Namkeen");
        nameItem4Label.setBounds(700,300,150,25);
        nameItem4Label.setFont(new Font("Arial",Font.BOLD,18));
        nameItem4Label.setHorizontalAlignment(JLabel.CENTER);

        packeditem1Cb = new JComboBox<>(packeditem1);
        packeditem1Cb.setBounds(100,340,120,25);
        packeditem1Cb.setFont(new Font("Arial",Font.PLAIN,14));

        packeditem2Cb = new JComboBox<>(packeditem2);
        packeditem2Cb.setBounds(300,340,120,25);
        packeditem2Cb.setFont(new Font("Arial",Font.PLAIN,14));

        packeditem3Cb = new JComboBox<>(packeditem3);
        packeditem3Cb.setBounds(500,340,120,25);
        packeditem3Cb.setFont(new Font("Arial",Font.PLAIN,14));

        packeditem4Cb = new JComboBox<>(packeditem4);
        packeditem4Cb.setBounds(700,340,120,25);
        packeditem4Cb.setFont(new Font("Arial",Font.PLAIN,14));

        item1PriceLabel = new JLabel("₹ "+packeditem1Price[0]);
        item1PriceLabel.setFont(new Font("Arial",Font.PLAIN,14));
        item1PriceLabel.setBounds(230,340,30,20);

        item2PriceLabel = new JLabel("₹ "+packeditem2Price[0]);
        item2PriceLabel.setFont(new Font("Arial",Font.PLAIN,14));
        item2PriceLabel.setBounds(430,340,30,20);

        item3PriceLabel = new JLabel("₹ "+packeditem3Price[0]);
        item3PriceLabel.setFont(new Font("Arial",Font.PLAIN,14));
        item3PriceLabel.setBounds(630,340,30,20);

        item4PriceLabel = new JLabel("₹ "+packeditem4Price[0]);
        item4PriceLabel.setFont(new Font("Arial",Font.PLAIN,14));
        item4PriceLabel.setBounds(830,340,30,20);


        JLabel qtyitem1Label = new JLabel("QTY");
        qtyitem1Label.setFont(new Font("Arial",Font.PLAIN,14));
        qtyitem1Label.setBounds(100,380,30,20);

        JLabel qtyitem2Label = new JLabel("QTY");
        qtyitem2Label.setFont(new Font("Arial",Font.PLAIN,14));
        qtyitem2Label.setBounds(300,380,30,20);

        JLabel qtyitem3Label = new JLabel("QTY");
        qtyitem3Label.setFont(new Font("Arial",Font.PLAIN,14));
        qtyitem3Label.setBounds(500,380,30,20);

        JLabel qtyitem4Label = new JLabel("QTY");
        qtyitem4Label.setFont(new Font("Arial",Font.PLAIN,14));
        qtyitem4Label.setBounds(700,380,30,20);


        String[] qty = {"1","2","3","4","5"};
        qtyitem1Cb = new JComboBox<>(qty);
        qtyitem1Cb.setFont(new Font("Arial",Font.PLAIN,14));
        qtyitem1Cb.setBounds(150,380,50,20);

        qtyitem2Cb = new JComboBox<>(qty);
        qtyitem2Cb.setFont(new Font("Arial",Font.PLAIN,14));
        qtyitem2Cb.setBounds(350,380,50,20);

        qtyitem3Cb = new JComboBox<>(qty);
        qtyitem3Cb.setFont(new Font("Arial",Font.PLAIN,14));
        qtyitem3Cb.setBounds(550,380,50,20);

        qtyitem4Cb = new JComboBox<>(qty);
        qtyitem4Cb.setFont(new Font("Arial",Font.PLAIN,14));
        qtyitem4Cb.setBounds(750,380,50,20);

        additem1Button = new JButton("Add");
        additem1Button.setBounds(100,420,150,25);
        additem1Button.setFont(new Font("MONOSPACED",Font.BOLD,18));
        additem1Button.setBackground(new Color(73, 156, 84));
        additem1Button.setForeground(Color.white);
        additem1Button.setBorderPainted(false);
        additem1Button.setFocusPainted(false);

        additem2Button = new JButton("Add");
        additem2Button.setBounds(300,420,150,25);
        additem2Button.setFont(new Font("MONOSPACED",Font.BOLD,18));
        additem2Button.setBackground(new Color(73, 156, 84));
        additem2Button.setForeground(Color.white);
        additem2Button.setBorderPainted(false);
        additem2Button.setFocusPainted(false);

        additem3Button = new JButton("Add");
        additem3Button.setBounds(500,420,150,25);
        additem3Button.setFont(new Font("MONOSPACED",Font.BOLD,18));
        additem3Button.setBackground(new Color(73, 156, 84));
        additem3Button.setForeground(Color.white);
        additem3Button.setBorderPainted(false);
        additem3Button.setFocusPainted(false);

        additem4Button = new JButton("Add");
        additem4Button.setBounds(700,420,150,25);
        additem4Button.setFont(new Font("MONOSPACED",Font.BOLD,18));
        additem4Button.setBackground(new Color(73, 156, 84));
        additem4Button.setForeground(Color.white);
        additem4Button.setBorderPainted(false);
        additem4Button.setFocusPainted(false);


//  Common button for order and exit from panel/frame

        addVegTiffiCartButtton = new JButton("Add");
        addVegTiffiCartButtton.setBounds(375,600,100,35);
        addVegTiffiCartButtton.setFont(new Font("MONOSPACED",Font.BOLD,18));
        addVegTiffiCartButtton.setBackground(new Color(73, 156, 84));
        addVegTiffiCartButtton.setForeground(Color.white);
        addVegTiffiCartButtton.setBorderPainted(false);
        addVegTiffiCartButtton.setFocusPainted(false);

        addNonvegTiffinCartButton = new JButton("Add");
        addNonvegTiffinCartButton.setBounds(375,600,100,35);
        addNonvegTiffinCartButton.setFont(new Font("MONOSPACED",Font.BOLD,18));
        addNonvegTiffinCartButton.setBackground(new Color(73, 156, 84));
        addNonvegTiffinCartButton.setForeground(Color.white);
        addNonvegTiffinCartButton.setBorderPainted(false);
        addNonvegTiffinCartButton.setFocusPainted(false);


        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(525,600,100,35);
        cancelButton.setFont(new Font("MONOSPACED",Font.BOLD,18));
        cancelButton.setBackground(new Color(162, 61, 78));
        cancelButton.setForeground(Color.white);
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);


        boxnumComboBox.addActionListener(this);
        tiffinqtyCommboBox.addActionListener(this);
        box1cb.addActionListener(this);
        box2cb.addActionListener(this);
        box2cb_nonveg.addActionListener(this);
        box3cb.addActionListener(this);
        box4cb.addActionListener(this);
        packeditem1Cb.addActionListener(this);
        packeditem2Cb.addActionListener(this);
        packeditem3Cb.addActionListener(this);
        packeditem4Cb.addActionListener(this);
        addVegTiffiCartButtton.addActionListener(this);
        addNonvegTiffinCartButton.addActionListener(this);
        cancelButton.addActionListener(this);
        additem1Button.addActionListener(this);
        additem2Button.addActionListener(this);
        additem3Button.addActionListener(this);
        additem4Button.addActionListener(this);


//        Adding Components on the required panels..

        if(menuStr.equals("veg")) {
            vegtiffinPanel.add(headingPanel);
            vegtiffinPanel.add(numOfBoxLabel);
            vegtiffinPanel.add(boxnumComboBox);
            vegtiffinPanel.add(qtyLabel);
            vegtiffinPanel.add(tiffinqtyCommboBox);
            vegtiffinPanel.add(boxesLabel);
            vegtiffinPanel.add(b1);
            vegtiffinPanel.add(box1cb);
            vegtiffinPanel.add(b2);
            vegtiffinPanel.add(box2cb);
            vegtiffinPanel.add(b3);
            vegtiffinPanel.add(box3cb);
            vegtiffinPanel.add(b4);
            vegtiffinPanel.add(box4cb);
            vegtiffinPanel.add(addVegTiffiCartButtton);
            vegtiffinPanel.add(cancelButton);
            vegtiffinPanel.add(box1priceLabel);
            vegtiffinPanel.add(box2priceLabel);
            vegtiffinPanel.add(box3priceLabel);
            vegtiffinPanel.add(box4priceLabel);

        }

        if(menuStr.equals("nonveg")) {
            nonvegtiffinPanel.add(headingPanel);
            nonvegtiffinPanel.add(numOfBoxLabel);
            nonvegtiffinPanel.add(boxnumComboBox);
            nonvegtiffinPanel.add(qtyLabel);
            nonvegtiffinPanel.add(tiffinqtyCommboBox);
            nonvegtiffinPanel.add(boxesLabel);
            nonvegtiffinPanel.add(b1);
            nonvegtiffinPanel.add(box1cb);
            nonvegtiffinPanel.add(b2);
            nonvegtiffinPanel.add(box2cb_nonveg);
            nonvegtiffinPanel.add(b3);
            nonvegtiffinPanel.add(box3cb);
            nonvegtiffinPanel.add(b4);
            nonvegtiffinPanel.add(box4cb);
            nonvegtiffinPanel.add(addNonvegTiffinCartButton);
            nonvegtiffinPanel.add(cancelButton);
            nonvegtiffinPanel.add(box1priceLabel);
            nonvegtiffinPanel.add(box2price_nonvegLabel);
            nonvegtiffinPanel.add(box3priceLabel);
            nonvegtiffinPanel.add(box4priceLabel);
        }

        if(menuStr.equals("packed")) {
            packeditemPanel.add(headingPanel);

            packeditemPanel.add(iconItem1Label);
            packeditemPanel.add(iconItem2Label);
            packeditemPanel.add(iconItem3Label);
            packeditemPanel.add(iconItem4Label);

            packeditemPanel.add(nameItem1Label);
            packeditemPanel.add(nameItem2Label);
            packeditemPanel.add(nameItem3Label);
            packeditemPanel.add(nameItem4Label);

            packeditemPanel.add(packeditem1Cb);
            packeditemPanel.add(packeditem2Cb);
            packeditemPanel.add(packeditem3Cb);
            packeditemPanel.add(packeditem4Cb);

            packeditemPanel.add(item1PriceLabel);
            packeditemPanel.add(item2PriceLabel);
            packeditemPanel.add(item3PriceLabel);
            packeditemPanel.add(item4PriceLabel);

            packeditemPanel.add(qtyitem1Label);
            packeditemPanel.add(qtyitem2Label);
            packeditemPanel.add(qtyitem3Label);
            packeditemPanel.add(qtyitem4Label);

            packeditemPanel.add(qtyitem1Cb);
            packeditemPanel.add(qtyitem2Cb);
            packeditemPanel.add(qtyitem3Cb);
            packeditemPanel.add(qtyitem4Cb);

            packeditemPanel.add(additem1Button);
            packeditemPanel.add(additem2Button);
            packeditemPanel.add(additem3Button);
            packeditemPanel.add(additem4Button);

        }

    }

    public static void main(String[] args) {
        new PanelClass("","");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

//  This block of statement helps show the required number of Box as choose by the user in boxnumComboBox For tiffins...

        if (ae.getSource() == boxnumComboBox) {
            String s = (String) boxnumComboBox.getSelectedItem();

            assert s != null;
            if(s.equals("3")) {
                b4.setVisible(false);
                box4cb.setVisible(false);
                box4priceLabel.setVisible(false);
            } else if (s.equals("4")) {
                b4.setVisible(true);
                box4cb.setVisible(true);
                box4priceLabel.setVisible(true);
            }

        }

//  In TiffinPanels, to show the PRICE on priceLabel Based on the choice of the Item in combobox.
        int i;
        if (ae.getSource() == box1cb ) {
            i = box1cb.getSelectedIndex();
            box1priceLabel.setText("₹"+box1price[i]);
        }

        if (ae.getSource() == box2cb ) {
            i = box2cb.getSelectedIndex();
            box2priceLabel.setText("₹"+box2price[i]);
        }

        if(ae.getSource() == box2cb_nonveg) {
            i = box2cb_nonveg.getSelectedIndex();
            box2price_nonvegLabel.setText("₹"+box2price_nonveg[i]);
        }

        if (ae.getSource() == box3cb ) {
            i = box3cb.getSelectedIndex();
            box3priceLabel.setText("₹"+box3price[i]);
        }

        if (ae.getSource() == box4cb ) {
            i = box4cb.getSelectedIndex();
            box4priceLabel.setText("₹"+box4price[i]);
        }

//  In PackedItem_Panel to show the PRICE at itemPrice based on the choice in the comboBox.

        if (ae.getSource() == packeditem1Cb) {
            i = packeditem1Cb.getSelectedIndex();
            item1PriceLabel.setText("₹ "+packeditem1Price[i]);
        }

        if (ae.getSource() == packeditem2Cb) {
            i = packeditem2Cb.getSelectedIndex();
            item2PriceLabel.setText("₹ "+packeditem2Price[i]);
        }

        if (ae.getSource() == packeditem3Cb) {
            i = packeditem3Cb.getSelectedIndex();
            item3PriceLabel.setText("₹ "+packeditem3Price[i]);
        }

        if (ae.getSource() == packeditem4Cb) {
            i = packeditem4Cb.getSelectedIndex();
            item4PriceLabel.setText("₹ "+packeditem4Price[i]);
        }

//  Adding order to tiffinorder table...
        String boxnumStr, tiffinqtyStr, box1Str, box2Str, box3Str, box4Str;
        int box1PriceStr, box2PriceStr, box3PriceStr, box4PriceStr;
        int totalPriceStr = 0;

        // Veg Tiffins
        if(ae.getSource() == addVegTiffiCartButtton) {

            boxnumStr = (String) boxnumComboBox.getSelectedItem();
            tiffinqtyStr = (String) tiffinqtyCommboBox.getSelectedItem();

            box1Str = (String) box1cb.getSelectedItem();
            box1PriceStr = Integer.parseInt(box1price[box1cb.getSelectedIndex()]); //box1priceLabel.getText();
            box2Str = (String) box2cb.getSelectedItem();
            box2PriceStr = Integer.parseInt(box2price[box2cb.getSelectedIndex()]);
            box3Str = (String) box3cb.getSelectedItem();
            box3PriceStr = Integer.parseInt(box3price[box3cb.getSelectedIndex()]);
            box4Str = (String) box4cb.getSelectedItem();
            box4PriceStr = Integer.parseInt(box4price[box4cb.getSelectedIndex()]);
            assert boxnumStr != null;
            if(boxnumStr.equals("3")) {
                box4Str = "--";
                box4PriceStr = 0; //Integer.parseInt("--");
                totalPriceStr = box1PriceStr + box2PriceStr + box3PriceStr;
            } else if (boxnumStr.equals("4")) {
                totalPriceStr = box1PriceStr + box2PriceStr + box3PriceStr + box4PriceStr;
            }

            // Creating a tiffinId for new customer adding tiffin to tiffin order table.
            String tiffin_id = "";

            boolean flag = true; // true means first order
            boolean flag1 = true; // true means first order
                    
            try {
                ResultSet rs = db.s.executeQuery("select tiffin_id from tiffinorder; "); // 
                if(rs.next()){
                    // it means its not the very first order
                    flag = false;
                    System.out.println("Flag "+flag);
                } 

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(flag == false) {
                try {
                    rs = db.s.executeQuery("select tiffin_id from tiffinorder where cust_id='"+cust_id+"' ;");
                    if(rs.next()){
                        // it means its not the first order of that cust_id customer
                        flag1 = false;
                        tiffin_id = rs.getString("tiffin_id");
                        System.out.println("Tiffin Id in case 3 : "+tiffin_id);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }

                if(flag1 == true) { // it means its the first order of that cust_id customer
                    try {
                        rs = db.s.executeQuery(" select tiffin_id from tiffinorder order by tiffin_id ASC ");

                        while(rs.next() ){
                            tiffin_id = "";
                            tiffin_id = rs.getString("tiffin_id");
                        }
                        if(tiffin_id.length()>0) {
                            int last_digit = tiffin_id.charAt(tiffin_id.length()-1) -'0';
                            last_digit++;
                            tiffin_id = tiffin_id.substring(0,tiffin_id.length()-1);
                            tiffin_id = tiffin_id + last_digit;

                            // System.out.println("Tiffin_id in case 2 : "+tiffin_id);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } 
            else {
                tiffin_id = "T001"; // it means its very First order
                System.out.println("Tiffin_id in case 1 : "+tiffin_id);
            }
            
            // Sending Data to DB in table tiffinorder
            String sql = "INSERT INTO `tiffinorder`(`tiffin_id`, `cust_id`,`box_qty`, `tiffin_qty`, `item1`, `item1_price`, `item2`, `item2_price`, `item3`, `item3_price`, `item4`, `item4_price`, `total_price`) " +
                    "VALUES ('"+tiffin_id+"','"+cust_id+"','"+boxnumStr+"','"+tiffinqtyStr+"','"+box1Str+"','"+box1PriceStr+"','"+box2Str+"','"+box2PriceStr+"','"+box3Str+"','"+box3PriceStr+"','"+box4Str+"','"+box4PriceStr+"','"+totalPriceStr+"')";

            try{
                Database db = new Database();
                rs = db.s.executeQuery("select * from tiffinorder where cust_id='"+cust_id+"' ;");
                if(rs.next()){
                    db.s.executeUpdate("Delete from tiffinorder where cust_id = '"+cust_id+"' ;");
                }
                db.s.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Tiffin Added Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Non-Veg Tiffins
        if(ae.getSource() == addNonvegTiffinCartButton) {
            boxnumStr = (String) boxnumComboBox.getSelectedItem();
            tiffinqtyStr = (String) tiffinqtyCommboBox.getSelectedItem();

            box1Str = (String) box1cb.getSelectedItem();
            box1PriceStr = Integer.parseInt(box1price[box1cb.getSelectedIndex()]); //box1priceLabel.getText();
            box2Str = (String) box2cb_nonveg.getSelectedItem();
            box2PriceStr = Integer.parseInt(box2price[box2cb_nonveg.getSelectedIndex()]);
            box3Str = (String) box3cb.getSelectedItem();
            box3PriceStr = Integer.parseInt(box3price[box3cb.getSelectedIndex()]);
            box4Str = (String) box4cb.getSelectedItem();
            box4PriceStr = Integer.parseInt(box4price[box4cb.getSelectedIndex()]);
            assert boxnumStr != null;
            if(boxnumStr.equals("3")) {
                box4Str = "--";
                box4PriceStr = 0; //Integer.parseInt("--");
                totalPriceStr = box1PriceStr + box2PriceStr + box3PriceStr;
            } else if (boxnumStr.equals("4")) {
                totalPriceStr = box1PriceStr + box2PriceStr + box3PriceStr + box4PriceStr;
            }

            // Creating a tiffinId for new customer adding tiffin to tiffin order table.
            String tiffin_id = "";

            boolean flag = true; // true means first order
            boolean flag1 = true; // true means first order
                    
            try {
                ResultSet rs = db.s.executeQuery("select tiffin_id from tiffinorder; "); // 
                if(rs.next()){
                    // it means its not the very first order
                    flag = false;
                    System.out.println("Flag "+flag);
                } 

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(flag == false) {
                try {
                    rs = db.s.executeQuery("select tiffin_id from tiffinorder where cust_id='"+cust_id+"' ;");
                    if(rs.next()){
                        // it means its not the first order of that cust_id customer
                        flag1 = false;
                        tiffin_id = rs.getString("tiffin_id");
                        System.out.println("Tiffin Id in case 3 : "+tiffin_id);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }

                if(flag1 == true) { // it means its the first order of that cust_id customer
                    try {
                        rs = db.s.executeQuery(" select tiffin_id from tiffinorder order by tiffin_id ASC ");

                        while(rs.next() ){
                            tiffin_id = "";
                            tiffin_id = rs.getString("tiffin_id");
                        }
                        if(tiffin_id.length()>0) {
                            int last_digit = tiffin_id.charAt(tiffin_id.length()-1) -'0';
                            last_digit++;
                            tiffin_id = tiffin_id.substring(0,tiffin_id.length()-1);
                            tiffin_id = tiffin_id + last_digit;

                            // System.out.println("Tiffin_id in case 2 : "+tiffin_id);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } 
            else {
                tiffin_id = "T001"; // it means its very First order
                System.out.println("Tiffin_id in case 1 : "+tiffin_id);
            }

            // Sending Data to DB in table tiffinorder
            String sql = "INSERT INTO `tiffinorder`(`tiffin_id`, `cust_id`,`box_qty`, `tiffin_qty`, `item1`, `item1_price`, `item2`, `item2_price`, `item3`, `item3_price`, `item4`, `item4_price`, `total_price`) " +
                    "VALUES ('"+tiffin_id+"','"+cust_id+"','"+boxnumStr+"','"+tiffinqtyStr+"','"+box1Str+"','"+box1PriceStr+"','"+box2Str+"','"+box2PriceStr+"','"+box3Str+"','"+box3PriceStr+"','"+box4Str+"','"+box4PriceStr+"','"+totalPriceStr+"')";

            try{
                  Database db = new Database();
                rs = db.s.executeQuery("select * from tiffinorder where cust_id='"+cust_id+"' ;");
                if(rs.next()){
                    db.s.executeUpdate("Delete from tiffinorder where cust_id = '"+cust_id+"' ;");
                }
                db.s.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Tiffin Added Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//  Adding Order to packedOrder table...
        String itemNameStr, itemTypeStr;
        int itemQtyStr; // for total Price tiffinorder 'totalPriceStr' is use in packedorder

        String packeditem_id = "";
        if(ae.getSource() == additem1Button || ae.getSource() == additem2Button || ae.getSource() == additem3Button || ae.getSource() == additem4Button){
            // check wheater that customer exist or not 
            String dbcust_id ="";
            try {
                rs = db.s.executeQuery("Select cust_id from packedorder");
                if(rs.next()){
                    dbcust_id = rs.getString("cust_id");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(dbcust_id.equals(cust_id)) {
               
            boolean flag = true; // use for no order in the table
            boolean flag1 = true; // used for repeat order in table 

            try {
                rs = db.s.executeQuery("select * from packedorder");
                if(rs.next()) {
                    flag = false; // not the first order in the table
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(flag==false) {

                try{
                    rs = db.s.executeQuery("Select packeditem_id from packedorder where cust_id = '"+cust_id+"' ");
                    if(rs.next()){
                        packeditem_id = rs.getString("packeditem_id");
                        flag1 = false; // not the first order by the customer 
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                if(flag1==true){ 
                    int last_digit = packeditem_id.charAt(packeditem_id.length()-1) -'0';
                    last_digit++;
                    packeditem_id = packeditem_id.substring(0,packeditem_id.length()-1);
                    packeditem_id = packeditem_id + last_digit;
                }
            } else {
                packeditem_id = "P001";
            }
            } else {
                try {
                    db.s.executeUpdate("TRUNCATE TABLE packedorder");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            boolean flag = true; // use for no order in the table
            boolean flag1 = true; // used for repeat order in table 

            try {
                rs = db.s.executeQuery("select * from packedorder");
                if(rs.next()) {
                    flag = false; // not the first order in the table
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(flag==false) {

                try{
                    rs = db.s.executeQuery("Select packeditem_id from packedorder where cust_id = '"+cust_id+"' ");
                    if(rs.next()){
                        packeditem_id = rs.getString("packeditem_id");
                        flag1 = false; // not the first order by the customer 
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                if(flag1==true){ 
                    int last_digit = packeditem_id.charAt(packeditem_id.length()-1) -'0';
                    last_digit++;
                    packeditem_id = packeditem_id.substring(0,packeditem_id.length()-1);
                    packeditem_id = packeditem_id + last_digit;
                }
            } else {
                packeditem_id = "P001";
            }
            }
        }

        if (ae.getSource() == additem1Button) {
            itemNameStr = nameItem1Label.getText();
            itemTypeStr = (String) packeditem1Cb.getSelectedItem();
            itemQtyStr = Integer.parseInt((String) Objects.requireNonNull(qtyitem1Cb.getSelectedItem()));
            // Total price of item is item_price * item_qty;
            totalPriceStr = Integer.parseInt(packeditem1Price[packeditem1Cb.getSelectedIndex()]) * itemQtyStr;

            try {
                rs = db.s.executeQuery("SELECT * FROM `packedorder` WHERE `item_name` = '"+itemNameStr+"' ");
                if(rs.next()) {
                    db.s.executeUpdate("DELETE FROM `packedorder` WHERE `item_name`= '"+itemNameStr+"' ");
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            try {
                db.s.executeUpdate("Insert into packedorder ( packeditem_id , cust_id , item_name, item_type, item_qty, total_price) VALUES ('"+packeditem_id+"','"+cust_id+"','"+itemNameStr+"', '"+itemTypeStr+"', '"+itemQtyStr+"','"+totalPriceStr+"') ");
                JOptionPane.showMessageDialog(null,"Item Added Successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == additem2Button) {
            itemNameStr = nameItem2Label.getText();
            itemTypeStr = (String) packeditem2Cb.getSelectedItem();
            itemQtyStr = Integer.parseInt((String) Objects.requireNonNull(qtyitem2Cb.getSelectedItem())) ;
            // Total price of item is item_price * item_qty;
            totalPriceStr = Integer.parseInt(packeditem2Price[packeditem1Cb.getSelectedIndex()]) * itemQtyStr;

            try {
                rs = db.s.executeQuery("SELECT * FROM `packedorder` WHERE `item_name` = '"+itemNameStr+"' ");

                if(rs.next()) {
                    db.s.executeUpdate("DELETE FROM `packedorder` WHERE `item_name` = '"+itemNameStr+"' ");
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            try {
                db.s.executeUpdate("Insert into packedorder (`packeditem_id`, `cust_id`,item_name, item_type, item_qty, total_price) VALUES ('"+packeditem_id+"','"+cust_id+"','"+itemNameStr+"', '"+itemTypeStr+"', '"+itemQtyStr+"','"+totalPriceStr+"') ");
                JOptionPane.showMessageDialog(null,"Item Added Successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == additem3Button) {
            itemNameStr = nameItem3Label.getText();
            itemTypeStr = (String) packeditem3Cb.getSelectedItem();
            itemQtyStr = Integer.parseInt((String) Objects.requireNonNull(qtyitem3Cb.getSelectedItem())) ;
            // Total price of item is item_price * item_qty;
            totalPriceStr = Integer.parseInt(packeditem3Price[packeditem1Cb.getSelectedIndex()]) * itemQtyStr;

            try {
                rs = db.s.executeQuery("SELECT * FROM `packedorder` WHERE `item_name` = '"+itemNameStr+"' ");

                if(rs.next()) {
                    db.s.executeUpdate("DELETE FROM `packedorder` WHERE `item_name` = '"+itemNameStr+"' ");
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            try {
                db.s.executeUpdate("Insert into packedorder (`packeditem_id`, `cust_id`,item_name, item_type, item_qty, total_price) VALUES ('"+packeditem_id+"','"+cust_id+"','"+itemNameStr+"', '"+itemTypeStr+"', '"+itemQtyStr+"','"+totalPriceStr+"') ");                JOptionPane.showMessageDialog(null,"Item Added Successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == additem4Button) {
            itemNameStr = nameItem4Label.getText();
            itemTypeStr = (String) packeditem4Cb.getSelectedItem();
            itemQtyStr = Integer.parseInt((String) Objects.requireNonNull(qtyitem4Cb.getSelectedItem())) ;
            // Total price of item is item_price * item_qty;
            totalPriceStr = Integer.parseInt(packeditem4Price[packeditem1Cb.getSelectedIndex()]) * itemQtyStr;

            try {
                rs = db.s.executeQuery("SELECT * FROM `packedorder` WHERE `item_name` = '"+itemNameStr+"' ");

                if(rs.next()) {
                    db.s.executeUpdate("DELETE FROM `packedorder` WHERE `item_name` = '"+itemNameStr+"' ");
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            try {
                db.s.executeUpdate("Insert into packedorder (`packeditem_id`, `cust_id`,item_name, item_type, item_qty, total_price) VALUES ('"+packeditem_id+"','"+cust_id+"','"+itemNameStr+"', '"+itemTypeStr+"', '"+itemQtyStr+"','"+totalPriceStr+"') ");
                JOptionPane.showMessageDialog(null,"Item Added Successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // This is For Cancel Button and The lots of code is to get the feedback from user...
        if (ae.getSource() == cancelButton) {
            String[] options = {"Its Awesome", "It's Good", "Okay",  "Hate It"};
            String feedback = (String) JOptionPane.showInputDialog(null,"How is your experience ?","Feedback",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

            if (feedback == null){ // If user Choose to cancel the Feedback
                // Here is new JOptionPane To request the user to give the feedback
                int input = JOptionPane.showConfirmDialog(null,"Plz give us Feedback!!","Request",JOptionPane.YES_NO_OPTION);

//               yes = 0 and No = 1
                if(input == 1){ // If user still don't want to give the feedback then it exit from the system
                    System.out.println("FeedBack "+ null);
                    System.exit(1);
                } else if( input == 0) { // If user change its mind to give the feedback
                    feedback = (String) JOptionPane.showInputDialog(null,"How is your experience ?","Feedback",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                    System.out.println("FeedBack "+feedback);
                    System.exit(1);
                }
            } else {
                System.out.println("FeedBack "+feedback);
                System.exit(1);
            }
        }

    }
}

