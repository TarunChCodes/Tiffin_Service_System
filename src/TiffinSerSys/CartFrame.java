package TiffinSerSys;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class CartFrame implements ActionListener {
    JFrame cartFrame;
    JPanel headingPanel,cartPanel;
    JLabel heading1Label, heading2Label, heading3Label; // Personal Info, Tiffin, Packed Items, Payment
    JLabel nameLabel, contactLabel, addLabel;
    JTextArea tiffinTextArea, packeditemTextArea;
    JLabel tiffinPriceLabel, packeditemPriceLabel, totalPriceLabel; // Price Label use to show the Price according to the tiffin and Packeditem cost
    JComboBox<String> paymentOptComboBox;
    JButton clearTiffinCartButton, clearPackeditemCartButton, payButton, orderButton; // The clearCartButton is used to clear the tiffin and packeditem from the order Table

    Database db = new Database();
    ResultSet rs;

    CartFrame() {
        cartFrame = new JFrame("Cart");
        cartFrame.setSize(715,720);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setLayout(null);
        cartFrame.setLocationRelativeTo(null);
        cartFrame.setBackground(Color.WHITE);


//        ---- Heading Panel Component ----
        headingPanel = new JPanel(null) {
            public void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/cart1.1.jpg"));
                Image i = img.getImage();
                g.drawImage(i, 0, 0, 700, 80, this);
            }
        };
        headingPanel.setSize(700,100);
        headingPanel.setVisible(true);


//       ----- CartPanel Components -------
        cartPanel = new JPanel(null);
        cartPanel.setBackground(new Color(255, 215, 173));
//        JScrollPane jspPanel = new JScrollPane(cartPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cartPanel.setBounds(0,80,700,600); //575

        nameLabel = new JLabel("Tarun Chaudhary");
        nameLabel.setBounds(20,10,150,25);
        nameLabel.setFont(new Font("Tahoma",Font.ITALIC,15));

        contactLabel = new JLabel("+91 8445402294");
        contactLabel.setBounds(20,30,150,25);
        contactLabel.setFont(new Font("Tahoma",Font.PLAIN,14));

        addLabel = new JLabel("A-41, Ganpati Vihar, Meerut, U.P, 250001");
        addLabel.setBounds(20,50,300,25);
        addLabel.setFont(new Font("Tahoma",Font.PLAIN,14));

//        -- Tiffin Cart Component --
        heading1Label = new JLabel("Tiffin");
        heading1Label.setBounds(20,100,100,30);
        heading1Label.setFont(new Font("Arial",Font.BOLD,22));

        tiffinTextArea = new JTextArea();
        tiffinTextArea.setEditable(false);
        tiffinTextArea.setFont(new Font("Monospaced",Font.PLAIN,14));
        tiffinTextArea.setBorder(new LineBorder(Color.orange));
        JScrollPane jsp2 = new JScrollPane(tiffinTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp2.setBounds(20,140,300,200);

        clearTiffinCartButton = new JButton("Clear");
        clearTiffinCartButton.setBounds(20,350,80,30);
        clearTiffinCartButton.setFont(new Font("Arial",Font.BOLD,14));
        clearTiffinCartButton.setBackground(Color.RED);
        clearTiffinCartButton.setForeground(Color.WHITE);
        clearTiffinCartButton.setBorderPainted(false);
        clearTiffinCartButton.setFocusPainted(false);
        clearTiffinCartButton.addActionListener(this);

//        -- PackedItem Cart Component --
        heading2Label = new JLabel("Packed Items");
        heading2Label.setBounds(350,100,150,30);
        heading2Label.setFont(new Font("Arial",Font.BOLD,22));

        packeditemTextArea = new JTextArea();
        packeditemTextArea.setEditable(false);
        packeditemTextArea.setFont(new Font("Monospaced",Font.PLAIN,14));
        packeditemTextArea.setBorder(new LineBorder(Color.BLUE));
        JScrollPane jsp3 = new JScrollPane(packeditemTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp3.setBounds(350,140,300,200);

        clearPackeditemCartButton = new JButton("Clear");
        clearPackeditemCartButton.setBounds(350,350,80,30);
        clearPackeditemCartButton.setFont(new Font("Arial",Font.BOLD,14));
        clearPackeditemCartButton.setBackground(Color.RED);
        clearPackeditemCartButton.setForeground(Color.WHITE);
        clearPackeditemCartButton.setBorderPainted(false);
        clearPackeditemCartButton.setFocusPainted(false);
        clearPackeditemCartButton.addActionListener(this);

//        -- Payment Component --
        heading3Label = new JLabel("Payment");
        heading3Label.setBounds(20,410,150,25);
        heading3Label.setFont(new Font("Arial",Font.BOLD,22));

        JLabel tPriceabel = new JLabel("Total Tiffin Amount :");
        tPriceabel.setBounds(40,450,200,20);
        tPriceabel.setHorizontalAlignment(JLabel.RIGHT);
        tPriceabel.setFont(new Font("Arial",Font.ITALIC,16));

        tiffinPriceLabel = new JLabel();
        tiffinPriceLabel.setBounds(260,450,100,20);
        tiffinPriceLabel.setFont(new Font("Arial",Font.PLAIN,14));

        JLabel piPriceLabel = new JLabel("Total PackedItem Amount :");
        piPriceLabel.setBounds(40,475,200,20);
        piPriceLabel.setHorizontalAlignment(JLabel.RIGHT);
        piPriceLabel.setFont(new Font("Arial",Font.ITALIC,16));

        packeditemPriceLabel = new JLabel("₹ 1115");
        packeditemPriceLabel.setBounds(260,475,100,20);
        packeditemPriceLabel.setFont(new Font("Arial",Font.PLAIN,14));

        JLabel toPriceLabel = new JLabel("Total Amount :");
        toPriceLabel.setBounds(40,500,200,20);
        toPriceLabel.setHorizontalAlignment(JLabel.RIGHT);
        toPriceLabel.setFont(new Font("Arial",Font.ITALIC,16));

        totalPriceLabel = new JLabel("₹ 2165");
        totalPriceLabel.setBounds(260,500,100,20);
        totalPriceLabel.setFont(new Font("Arial",Font.PLAIN,14));

        JLabel paymentOptLabel = new JLabel("Payment Option");
        paymentOptLabel.setBounds(350,450,120,20);
        paymentOptLabel.setFont(new Font("Arail",Font.PLAIN,16));

        paymentOptComboBox = new JComboBox<>();
        paymentOptComboBox.setBounds(480,450,100,20);
        paymentOptComboBox.addItem("Online");
        paymentOptComboBox.addItem("COD");
        paymentOptComboBox.addActionListener(this);

        payButton = new JButton("Pay");
        payButton.setBounds(350,500,100,25);
        payButton.setFont(new Font("Arial",Font.BOLD,16));
        payButton.setBackground(new Color(94, 152, 109));
        payButton.setForeground(Color.WHITE);
        payButton.setBorderPainted(false);
        payButton.setFocusPainted(false);

        orderButton = new JButton("ORDER");
        orderButton.setBounds(20,550,100,30);
        orderButton.setFont(new Font("Arial",Font.BOLD,16));
        orderButton.setBackground(Color.BLACK);
        orderButton.setForeground(Color.WHITE);
        orderButton.setBorderPainted(false);
        orderButton.setFocusPainted(false);

//  Retrieving order from tables to TextArea

        //  for Tiffins

        String  box1Str = "", box2Str= "", box3Str= "", box4Str= "";
        String box1PriceStr= "", box2PriceStr= "",box3PriceStr= "" ,box4PriceStr= "";
        int tiffinqtyStr = 0, boxqtyStr = 0, tiffinTotalPrice = 0;
        try {
            rs = db.s.executeQuery("select * from tiffinorder");
//            JOptionPane.showMessageDialog(null, "Data Reterived!!");

            while (rs.next()) {
                tiffinqtyStr = Integer.parseInt(rs.getString("tiffin_qty"));
            boxqtyStr = Integer.parseInt(rs.getString("box_qty"));
            box1Str = rs.getString("item1");
                box1PriceStr = rs.getString("item1_price");
            box2Str = rs.getString("item2");
                box2PriceStr = rs.getString("item2_price");
            box3Str = rs.getString("item3");
                box3PriceStr = rs.getString("item3_price");
            box4Str = rs.getString("item4");
                box4PriceStr = rs.getString("item4_price");

                tiffinTotalPrice  = Integer.parseInt(rs.getString("total_price"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        String tiffincartS1 = " Tiffin Qty-"+ tiffinqtyStr+", Box Qty-" + boxqtyStr + "\n" +
                "---------------------------------" + "\n" +
                " " + box1Str +" - ₹" + box1PriceStr + "\n" +
                " " + box2Str +" - ₹" + box2PriceStr + "\n" +
                " " + box3Str +" - ₹" + box3PriceStr + "\n";

        String tiffincartS2 =  " " + box4Str +" - ₹" + box4PriceStr + "\n" ;
        String tiffincartEnd =   "---------------------------------" ;

        String tiffinCartStr ="No Tiffin Added!!😢";
        if(boxqtyStr == 3) {
            tiffinCartStr = tiffincartS1 + tiffincartEnd;
        }
        if(boxqtyStr == 4) {
            tiffinCartStr = tiffincartS1 + tiffincartS2 + tiffincartEnd;
        }

        tiffinPriceLabel.setText("₹ " + tiffinTotalPrice);

        // for PackedItem
        int itemNum = 0;
        String icecreamTypeStr ="", colddrinkTypeStr ="", biscuitTypeStr ="", namkeenTypeStr ="";
        int icecreamPrice =0, colddrinkPrice =0, biscuitPrice =0, namkeenPrice =0, itemtotalPrice =0 ;
        String icecreamQty ="", colddrinkQty ="", biscuitQty ="", namkeenQty ="";


        try {
            rs = db.s.executeQuery("Select count(*) from packedorder");
            if(rs.next()){
                itemNum = Integer.parseInt(rs.getString("count(*)"));
            }

            String [] itemNameStr = {"Ice Cream", "Cold Drinks", "Biscuit", "Namkeen"};
            for(int i = 0; i<itemNum; i++) {
                rs = db.s.executeQuery("SELECT * FROM `packedorder` WHERE `item_Name` = '"+itemNameStr[i]+"'; ");
                if(rs.next()) {
                        if(i==0) {
                            icecreamTypeStr = rs.getString("item_type");
                            icecreamPrice = Integer.parseInt(rs.getString("total_price"));
                            icecreamQty = rs.getString("item_qty");

                            itemtotalPrice = icecreamPrice;
                        } else if (i==1) {
                            colddrinkTypeStr = rs.getString("item_type");
                            colddrinkPrice = Integer.parseInt(rs.getString("total_price"));
                            colddrinkQty = rs.getString("item_qty");

                            itemtotalPrice = icecreamPrice + colddrinkPrice;
                        } else if (i==2) {
                            biscuitTypeStr = rs.getString("item_type");
                            biscuitPrice = Integer.parseInt(rs.getString("total_price"));
                            biscuitQty = rs.getString("item_qty");

                            itemtotalPrice = icecreamPrice + colddrinkPrice + biscuitPrice;
                        } else {
                            namkeenTypeStr = rs.getString("item_type");
                            namkeenPrice = Integer.parseInt(rs.getString("total_price"));
                            namkeenQty= rs.getString("item_qty");

                            itemtotalPrice = icecreamPrice + colddrinkPrice + biscuitPrice + namkeenPrice;
                        }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String packedcartS1 = "Packed Item" + ", Item Qty-" + itemNum  + "\n" +
                              "---------------------------------" + "\n" +
                              " " + icecreamTypeStr +"/"+ icecreamQty + " - ₹" + icecreamPrice + "\n" ;
        String packedcartS2 = " " + colddrinkTypeStr + "/" + colddrinkQty + " - ₹" + colddrinkPrice + "\n" ;
        String packedcartS3 = " " + biscuitTypeStr + "/" + biscuitQty + " - ₹" + biscuitPrice + "\n" ;
        String packedcartS4 = " " + namkeenTypeStr + "/" + namkeenQty + " - ₹" + namkeenPrice + "\n" ;
        String packedcartEnd = "---------------------------------";

        String packeditemCartStr = "No Item Added!!😢";
        if(itemNum == 1) {
            packeditemCartStr = packedcartS1 + packedcartEnd;
        }
        if (itemNum == 2) {
            packeditemCartStr = packedcartS1 + packedcartS2 + packedcartEnd;
        }
        if( itemNum == 3) {
            packeditemCartStr = packedcartS1 + packedcartS2 + packedcartS3 + packedcartEnd;
        }
        if ( itemNum == 4) {
            packeditemCartStr = packedcartS1 + packedcartS2 + packedcartS3 + packedcartS4 + packedcartEnd;
        }

        packeditemPriceLabel.setText("₹ "+String.valueOf(itemtotalPrice));

        int totalOrderPrice = tiffinTotalPrice + itemtotalPrice;
        totalPriceLabel.setText("₹ "+totalOrderPrice);

        tiffinTextArea.setText(tiffinCartStr);
        packeditemTextArea.setText(packeditemCartStr);

//        -- Adding Component to Panel --
        cartPanel.add(nameLabel);
        cartPanel.add(contactLabel);
        cartPanel.add(addLabel);

        cartPanel.add(heading1Label);
        cartPanel.add(jsp2); // It is used to add the TextFeild of tiffin..
        cartPanel.add(clearTiffinCartButton);

        cartPanel.add(heading2Label);
        cartPanel.add(jsp3); // It is used to add the TextFeild of Packed Item..
        cartPanel.add(clearPackeditemCartButton);

        cartPanel.add(heading3Label);
            cartPanel.add(tPriceabel);
        cartPanel.add(tiffinPriceLabel);
            cartPanel.add(piPriceLabel);
        cartPanel.add(packeditemPriceLabel);
            cartPanel.add(toPriceLabel);
        cartPanel.add(totalPriceLabel);
        cartPanel.add(paymentOptLabel);
        cartPanel.add(paymentOptComboBox);
        cartPanel.add(payButton);
        cartPanel.add(orderButton);

//      --- Adding Component to Frame ---
        cartFrame.add(cartPanel);
        cartFrame.add(headingPanel);

        cartFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new CartFrame();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == paymentOptComboBox) {
            String  getItem = (String) paymentOptComboBox.getSelectedItem();
            assert getItem != null;
            if(getItem.equals("Online")) {
                payButton.setVisible(true);
            }
            if(getItem.equals("COD")) {
                payButton.setVisible(false);
            }
        }

        if(ae.getSource() == clearPackeditemCartButton) {

            try {
                int confirmOpt = JOptionPane.showConfirmDialog(null,"Want to CART CLEAR ?","Warning!!",JOptionPane.YES_NO_OPTION);
                if(confirmOpt == 0){
                    db.s.executeUpdate("DELETE FROM `packedorder`");
                    packeditemTextArea.setText("");
                    JOptionPane.showMessageDialog(null,"CART CLEARED SUCCESSFULLY");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ae.getSource() == clearTiffinCartButton) {

            try {
                int confirmOpt = JOptionPane.showConfirmDialog(null,"Want to CART CLEAR ?","Warning!!",JOptionPane.YES_NO_OPTION);
                        if(confirmOpt == 0){
                            db.s.executeUpdate("DELETE FROM `tiffinorder`");
                            tiffinTextArea.setText("");
                            JOptionPane.showMessageDialog(null,"CART CLEARED SUCCESSFULLY");
                        }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

