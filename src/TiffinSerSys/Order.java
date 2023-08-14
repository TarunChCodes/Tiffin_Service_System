package TiffinSerSys;

import javax.swing.*;
import java.awt.*;
import net.proteanit.sql.DbUtils;
import java.sql.ResultSet;

public class Order {

    JTable orderTable;
    Database db = new Database();
    ResultSet rs;
    
    Order(String id, String usrType) {
        JFrame orderFrame = new JFrame("Order"); 

        orderFrame.setBounds(290,0,1000,725);
        orderFrame.setResizable(false);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setLayout(null);
        orderFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel heading = new JLabel("Order");
        heading.setFont(new Font("Arial", Font.PLAIN, 26));
        heading.setBounds(400, 10, 250, 30);
        
        orderTable = new JTable();

        // CASE1- Cutomer 
        System.out.println(usrType);
        if(usrType.equals("Customer")) {
            try {
                rs = db.s.executeQuery("Select * FROM `order` WHERE cust_id = '"+id+"' ");
                orderTable.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        // CASE2 - Admin/Employee
        else if (usrType.equals("Admin") || usrType.equals("Employee")) {
            try {
                rs = db.s.executeQuery("Select * from tss.order");
                orderTable.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else {
            JOptionPane.showMessageDialog(null,"Invalid Order class Open");
            System.exit(0);
        }
        
        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(0,200,995,725);

        // Adding component 
        orderFrame.add(heading);
        orderFrame.add(scrollPane);
        orderFrame.setVisible(true);
    }

    public static void main(String[] args) {
    new Order("","");
    }
}