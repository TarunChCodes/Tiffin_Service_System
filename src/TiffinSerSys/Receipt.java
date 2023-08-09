package TiffinSerSys;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.awt.*;

public class Receipt implements ActionListener {

    JTextArea receiptTextArea;
    JButton printButton;

    String cust_id, receiptStr;
    Receipt (String cust_id, String custinfo, String tiffinCart, String packeditemCart, int tiffinCost, int packeditemCost, int totalOrderPrice, String payStatus) {
        this.cust_id = cust_id;

        JFrame receiptFrame = new JFrame("Receipt");
        receiptFrame.setBounds(280,0,650,725);
        receiptFrame.setLayout(null);
        receiptFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Change it later 
        receiptFrame.setBackground(Color.DARK_GRAY);

        JLabel heading = new JLabel("Order Receipt");
        heading.setBounds(230,10,150,30);
        heading.setFont(new Font("Arial",Font.PLAIN,24));

        receiptTextArea = new JTextArea();
        receiptTextArea.setEditable(true);
        receiptTextArea.setFont(new Font("Monospaced",Font.PLAIN,14));
        receiptTextArea.setBorder(new LineBorder(Color.BLACK));
        JScrollPane jsp = new JScrollPane(receiptTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(0,70,638,550);

        printButton = new JButton("Print");
        printButton.setBounds(255,630,100,30);
        printButton.setFont(new Font("Arial",Font.BOLD,16));
        printButton.setBackground(Color.BLACK);
        printButton.setForeground(Color.WHITE);
        printButton.setBorderPainted(false);
        printButton.setFocusPainted(false);
        printButton.addActionListener(this);

        // String to be shown on the String 

        receiptStr = "\t\t\tTIFFIN SERVICE SYSTEM\n" + //
                "-----------------------------------------------------------------------" +
                custinfo + "\n" +

                "\tTiffin Box\r\n" + //
                "---------------------------------" +
                "\n" + tiffinCart + "\n"  +
                "Total Tiffin = "+ tiffinCost +"\r\n" + //   
                "\r\n" + //

                "\tPacked Items\r\n" + //
                "---------------------------------" +
                "\n" + packeditemCart + "\n"  +
                "Total PackedItem = "+ packeditemCost +"\r\n" + //   
                "\r\n" +

                "\r\n" + //
                "Total Order Cost = "+ totalOrderPrice + "\r\n" + //
                "Payment Status = "+ payStatus +"\r\n" + //
                "\r\n" + //
                "\r\n" + //
                "--------------------------- Thank you ---------------------------------"
                ; 

        receiptTextArea.setText(receiptStr);
        receiptFrame.add(heading);
        receiptFrame.add(jsp);
        receiptFrame.add(printButton);
        receiptFrame.setVisible(true);
    }
    public static void main(String[] args) {
         new Receipt("","","","",0,0,0,"");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource() == printButton) {
        try {
            receiptTextArea.print();
        } catch (Exception e){
            e.printStackTrace();
        }
       }
    }
}
