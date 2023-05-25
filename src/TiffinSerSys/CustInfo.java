package TiffinSerSys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class CustInfo extends JFrame implements ActionListener {

    JLabel idLabel;
    JComboBox<String> idChoice;
    JButton search;
    JTable custdet;
    Database db = new Database();
    ResultSet rs;
    String usrtype = "Customer";

    CustInfo() {
        setTitle("Customer Information");
        setBounds(290,0,1000,725);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);


        JLabel heading = new JLabel("Customer Information");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(400, 10, 250, 30);
        add(heading);

        idLabel = new JLabel("Customer ID");
        idLabel.setBounds(80, 100, 180, 30);
        idLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(idLabel);

        idChoice = new JComboBox<String>();
        idChoice.setBounds(200, 100, 100, 25);
        add(idChoice);
        try{

            rs = db.s.executeQuery("select custid from userdet where usertype = '"+usrtype+"' ");
            while (rs.next()){
                idChoice.addItem(rs.getString("custid"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }



        search = new JButton("Search");
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.setBorderPainted(false);
        search.setFocusPainted(false);
        search.setBounds(350, 100, 100, 25);
        add(search);
        search.addActionListener(this);


        custdet = new JTable();

        try {
            rs = db.s.executeQuery("Select `usertype`, `custid`, `username`, `name`, `phno`, `email`, `address` FROM `userdet` WHERE usertype = '"+usrtype+"' ");
            custdet.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(custdet);
        scrollPane.setBounds(0,200,995,725);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            String id = (String) idChoice.getSelectedItem();
            System.out.println(id);

            try{
                rs = db.s.executeQuery("Select `usertype`, `custid`, `username`, `name`, `phno`, `email`, `address` FROM `userdet` WHERE custid = '"+id+"' ");
                custdet.setModel(DbUtils.resultSetToTableModel(rs));
//                if(rs.next()){
//                    JOptionPane.showMessageDialog(null,"Data Found! (●'◡'●)");
//                } else {
//                    JOptionPane.showMessageDialog(null,"Data Not Found! X﹏X");
//                }

            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new CustInfo();
    }

}

