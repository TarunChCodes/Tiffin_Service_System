package TiffinSerSys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Homepage extends JFrame implements ActionListener {

    JPanel main_Jpanel, menu_Jpanel;
    JButton toggleButton;

    //    Button For the Menus on Menu Panel on Left Side of Main Frame
    JButton myProfile, tiffinCat, cart, order, custInfo, empInfo, logout;

    String id, usrtype;

    Homepage(String id, String usrtype) {
        this.id = id;
        this.usrtype = usrtype;

        setTitle("HomePage");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//       ---------------------- Main Panel Content ---------------------
        main_Jpanel = new JPanel() {
            public void paintComponent(Graphics g) {
                ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/backImg.jpg"));
                Image img2 = img1.getImage();
                g.drawImage(img2, 0, 0, main_Jpanel.getWidth(), 700, this);
            }
        };
        main_Jpanel.setLayout(null);
        main_Jpanel.setVisible(true);
        main_Jpanel.setBounds(0, 0, 1300, 700);

        toggleButton = new JButton("Menu");
        toggleButton.setBounds(10, 5, 150, 30);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setBorderPainted(false);
        toggleButton.setFocusPainted(false);
        toggleButton.setFont(new Font("Sans-serif ", Font.BOLD, 18));
        main_Jpanel.add(toggleButton);
        toggleButton.addActionListener(this);
        
//       ----------------------- Menu_Panel Content -------------------------------
        menu_Jpanel = new JPanel();
        menu_Jpanel.setLayout(null);
        menu_Jpanel.setVisible(false);
        menu_Jpanel.setBounds(0,0,300, 700);
        menu_Jpanel.setBackground(Color.BLACK);

        myProfile = new JButton("My Profile");
        myProfile.setFont(new Font("Arial", Font.BOLD, 20));
        myProfile.setForeground(Color.white);
        myProfile.setBounds(20, 50, 150, 30);
        myProfile.setContentAreaFilled(false);
        myProfile.setBorderPainted(false);
        myProfile.setHorizontalAlignment(JButton.LEFT);
        myProfile.addActionListener(this);

        tiffinCat = new JButton("Tiffins");
        tiffinCat.setFont(new Font("Arial", Font.BOLD, 20));
        tiffinCat.setForeground(Color.white);
        tiffinCat.setBounds(20, 100, 150, 30);
        tiffinCat.setContentAreaFilled(false);
        tiffinCat.setBorderPainted(false);
        tiffinCat.setHorizontalAlignment(JButton.LEFT);
        tiffinCat.addActionListener(this);

        order = new JButton("Orders");
        order.setFont(new Font("Arial", Font.BOLD, 20));
        order.setForeground(Color.white);
        order.setBounds(20, 150, 125, 30);
        order.setContentAreaFilled(false);
        order.setBorderPainted(false);
        order.setHorizontalAlignment(JButton.LEFT);
        order.addActionListener(this);

        cart = new JButton("Cart");
        cart.setFont(new Font("Arial", Font.BOLD, 20));
        cart.setForeground(Color.white);
        cart.setBounds(20, 200, 150, 30);
        cart.setContentAreaFilled(false);
        cart.setBorderPainted(false);
        cart.setHorizontalAlignment(JButton.LEFT);
        cart.addActionListener(this);

        custInfo = new JButton("Customer Information");
        custInfo.setFont(new Font("Arial", Font.BOLD, 20));
        custInfo.setForeground(Color.white);
        custInfo.setBounds(20, 200, 260, 30);
        custInfo.setContentAreaFilled(false);
        custInfo.setBorderPainted(false);
        custInfo.setHorizontalAlignment(JButton.LEFT);
        custInfo.addActionListener(this);

        empInfo = new JButton("Employee Information");
        empInfo.setFont(new Font("Arial", Font.BOLD, 20));
        empInfo.setForeground(Color.white);
        empInfo.setBounds(20, 250, 260, 30);
        empInfo.setContentAreaFilled(false);
        empInfo.setBorderPainted(false);
        empInfo.setHorizontalAlignment(JButton.LEFT);
        empInfo.addActionListener(this);

        logout = new JButton("Log Out");
        logout.setFont(new Font("Arial", Font.BOLD, 18));
        logout.setForeground(Color.white);
        logout.setBounds(10, 600, 260, 30);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.addActionListener(this);

        menu_Jpanel.add(myProfile);
        menu_Jpanel.add(logout);
        if(usrtype.equals("Admin")) {
            menu_Jpanel.add(tiffinCat);
             menu_Jpanel.add(order);
            menu_Jpanel.add(custInfo);
            menu_Jpanel.add(empInfo);
        } else if(usrtype.equals("Customer")) {
            menu_Jpanel.add(tiffinCat);
             menu_Jpanel.add(order);
            menu_Jpanel.add(cart);
        } else if (usrtype.equals("Employee")) {
            order.setBounds(20, 100, 150, 30);
            menu_Jpanel.add(order);
        }
 
        add(menu_Jpanel);
        add(main_Jpanel);
        setVisible(true);
    } // End of Constructor

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("Full Screen")) {
            menu_Jpanel.setVisible(false);
            main_Jpanel.setBounds(0, 0, 1300, 700);
            toggleButton.setText("Menu");
        } else if (ae.getActionCommand().equals("Menu")) {
            menu_Jpanel.setVisible(true);
            main_Jpanel.setBounds(300, 0, 990, 700);
            toggleButton.setText("Full Screen");
        } else if (ae.getSource() == myProfile) {
            new Myprofile(id, usrtype);
        } else if(ae.getSource() == tiffinCat) {
            if(usrtype.equals("Customer")) {
                new TiffinFrameCust(id);
            } else if(usrtype.equals("Admin")) {
                JOptionPane.showMessageDialog(null, "Working On it!!");
            } else if(usrtype.equals("Employee")) {
                JOptionPane.showMessageDialog(null, "Working On it!!");
            }
        } else if (ae.getSource() == order) {
            if(usrtype.equals("Customer")) {
                JOptionPane.showMessageDialog(null, "Working On it!!");
            } else if(usrtype.equals("Admin")) {
                JOptionPane.showMessageDialog(null, "Working On it!!");
            } else if(usrtype.equals("Employee")) {
                JOptionPane.showMessageDialog(null, "Working On it!!");
            }
        } else if (ae.getSource() == cart) {
            new CartFrame(id);
        } else if (ae.getSource() == custInfo) {
            new CustInfo();
        } else if (ae.getSource() == empInfo) {
            new EmpInfo();
        } else if (ae.getSource() == logout) {
            setVisible(false);
            new login();
        }
    }

    public static void main(String[] args) {
        new Homepage("", "");
    }
}
