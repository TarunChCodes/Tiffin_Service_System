package TiffinSerSys;

import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class EmpInfo extends JFrame implements ActionListener {
//  In this Module we provide the following Functionality to the Viewer
//    1. It can search any employee by just its ID
//    2. It can add new Emp
//    3. Can remove any Emp from Sys
    JLabel idLabel;
    JComboBox<String> idChoice;
    JButton searchButton,addButton,removeButton;
    JTable empdetTable;
    Database db = new Database();
    ResultSet rs;
    String usrtype ="Employee";
    EmpInfo() {
        setTitle("Employee Information");
        setBounds(290,0,1000,725);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel heading = new JLabel("Employee Information");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(400, 10, 250, 30);
        add(heading);

        idLabel = new JLabel("Employee ID");
        idLabel.setBounds(80, 100, 180, 30);
        idLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(idLabel);

        idChoice = new JComboBox<String>();
        idChoice.setBounds(200, 100, 100, 25);
        add(idChoice);
        try{

            rs = db.s.executeQuery("select empid from userdet where usertype = '"+usrtype+"' ORDER BY empid ASC ");
            while (rs.next()){
                idChoice.addItem(rs.getString("empid"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setBounds(350, 100, 100, 25);
        add(searchButton);
        searchButton.addActionListener(this);

        removeButton = new JButton("Remove");
        removeButton.setBackground(Color.BLACK);
        removeButton.setForeground(Color.WHITE);
        removeButton.setBorderPainted(false);
        removeButton.setFocusPainted(false);
        removeButton.setBounds(460, 100, 100, 25);
        add(removeButton);
        removeButton.addActionListener(this);

        addButton = new JButton("Add");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setBounds(570, 100, 100, 25);
        add(addButton);
        addButton.addActionListener(this);

        empdetTable = new JTable();

        try {
            rs = db.s.executeQuery("Select `usertype`, `empid`, `username`, `name`, `phno`, `email`, `address` FROM `userdet` WHERE usertype = '"+usrtype+"' ");
            empdetTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(empdetTable);
        scrollPane.setBounds(0,200,995,725);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new EmpInfo();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== addButton){
            new signup();
        } else if (ae.getSource()==removeButton) {
            try {
                db.s.executeUpdate("Delete from userdet where empid = '"+(String) idChoice.getSelectedItem()+"' ");
                JOptionPane.showMessageDialog(null,"Employee Removed Successfully! （￣︶￣）↗");

//                Here The table show all the remaining Employee.
                rs = db.s.executeQuery("Select `usertype`, `empid`, `username`, `name`, `phno`, `email`, `address` FROM `userdet` WHERE usertype = '"+usrtype+"' ");
                empdetTable.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e){
                e.printStackTrace();
            }

            idChoice.removeAllItems();
            try{
//                Here we Update the JComboBox with remaining ID in the D-Base.
                        rs = db.s.executeQuery("select empid from userdet where usertype = '"+usrtype+"' ");
                while (rs.next()){
                    idChoice.addItem(rs.getString("empid"));
                }

            } catch (Exception e){
                e.printStackTrace();
            }


        } else if (ae.getSource() == searchButton) {
            String id = (String) idChoice.getSelectedItem();
//            System.out.println(id);

            try{
                rs = db.s.executeQuery("Select `usertype`, `empid`, `username`, `name`, `phno`, `email`, `address` FROM `userdet` WHERE empid = '"+id+"' ");
//                if(rs.next()){
//                    System.out.println("Data Found ");
//                } else {
//                    System.out.println("Data Not Found");
//                }
                empdetTable.setModel(DbUtils.resultSetToTableModel(rs));


            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
