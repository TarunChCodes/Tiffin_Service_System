package TiffinSerSys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TiffinFrameCust implements ActionListener{

    JFrame MainFrame;
    JMenuBar menuBar;
    JMenu tiffin, packedItem, cartMenu, closeMenu;
    JMenuItem vegTiffin, nonvegTiffin, packedMenuItem, cartMenuItem, closeMenuItem, exitMenuItem;
    JPanel imgPanel;

    String id;

    TiffinFrameCust(String id) {
        this.id = id;

        MainFrame = new JFrame("Tiffin Category");
        MainFrame.setLayout(null);
        MainFrame.setBounds(290,0,1000,725);
        MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MainFrame.setBackground(new Color(237, 235, 246));

        menuBar = new JMenuBar();

        tiffin = new JMenu("Tiffin");
        tiffin.setFont(new Font("Sherif", Font.PLAIN, 18));
        tiffin.setMnemonic(KeyEvent.VK_T); // Alt + T for Tiffin
        menuBar.add(tiffin);

        vegTiffin = new JMenuItem("Veg Tiffins ");
        vegTiffin.setFont(new Font("Sherif", Font.ITALIC, 16));
        vegTiffin.setMnemonic(KeyEvent.VK_V); // v for vegTiffin
        tiffin.add(vegTiffin);
        vegTiffin.addActionListener(this);

        nonvegTiffin = new JMenuItem("Non-Veg Tiffins ");
        nonvegTiffin.setFont(new Font("Sherif", Font.ITALIC, 16));
        nonvegTiffin.setMnemonic(KeyEvent.VK_N); // n for nonvegTiffin
        tiffin.add(nonvegTiffin);
        nonvegTiffin.addActionListener(this);

        packedItem = new JMenu("Packed Item");
        packedItem.setFont(new Font("Sherif", Font.PLAIN, 18));
        packedItem.setMnemonic(KeyEvent.VK_P); // Alt + P for PackedItem
        menuBar.add(packedItem);

        packedMenuItem = new JMenuItem("Packed Items");
        packedMenuItem.setFont(new Font("Sherif", Font.ITALIC, 16));
        packedMenuItem.setMnemonic(KeyEvent.VK_P); // p for packedItem MenuItem
        packedItem.add(packedMenuItem);
        packedMenuItem.addActionListener(this);

        cartMenu = new JMenu("Cart");
        cartMenu.setFont(new Font("Sherif", Font.PLAIN, 18));
        cartMenu.setMnemonic(KeyEvent.VK_C); // Alt+c for Cart Menu
        menuBar.add(cartMenu);

        cartMenuItem = new JMenuItem("View Cart");
        cartMenuItem.setFont(new Font("Sherif", Font.ITALIC, 16));
        cartMenuItem.setMnemonic(KeyEvent.VK_C); // c for close MenuItem
        cartMenu.add(cartMenuItem);
        cartMenuItem.addActionListener(this);

        closeMenu = new JMenu("Close");
        closeMenu.setFont(new Font("Sherif", Font.PLAIN, 18));
        closeMenu.setMnemonic(KeyEvent.VK_X); // Alt + x for close Menu
        menuBar.add(closeMenu);

        closeMenuItem = new JMenuItem("Close Panels");
        closeMenuItem.setFont(new Font("Sherif", Font.ITALIC, 16));
        closeMenuItem.setMnemonic(KeyEvent.VK_X); // x for close MenuItem
        closeMenu.add(closeMenuItem);
        closeMenuItem.addActionListener(this);

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setFont(new Font("Sherif", Font.ITALIC, 16));
        exitMenuItem.setMnemonic(KeyEvent.VK_E); // e for Exit
        closeMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(this);

       imgPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/poster.png"));
                Image img2 = img1.getImage();
                g.drawImage(img2, 200, 0, 600, 700, this);
            }
        };

        MainFrame.setJMenuBar(menuBar);
        MainFrame.setContentPane(imgPanel);
        MainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TiffinFrameCust("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vegTiffin) {

            PanelClass p = new PanelClass("veg",id);
            p.nonvegtiffinPanel.setVisible(false);
            p.packeditemPanel.setVisible(false);
            p.vegtiffinPanel.setVisible(true);
            p.headingLabel.setText("VEG TIFFINS");
            MainFrame.setContentPane(p.vegtiffinPanel);

        } else if (ae.getSource() == nonvegTiffin) {

            PanelClass p = new PanelClass("nonveg",id);
            p.nonvegtiffinPanel.setVisible(true);
            p.packeditemPanel.setVisible(false);
            p.vegtiffinPanel.setVisible(false);
            p.headingLabel.setText("NON-VEG TIFFINS");
            MainFrame.setContentPane(p.nonvegtiffinPanel);

        } else if (ae.getSource() == packedMenuItem) {

            PanelClass p = new PanelClass("packed",id);
            p.nonvegtiffinPanel.setVisible(false);
            p.packeditemPanel.setVisible(true);
            p.vegtiffinPanel.setVisible(false);
            p.headingLabel.setText("PACKED ITEMS");
            MainFrame.setContentPane(p.packeditemPanel);


        } else if (ae.getSource() == closeMenuItem) {

            PanelClass p = new PanelClass("close",id);
            p.nonvegtiffinPanel.setVisible(false);
            p.packeditemPanel.setVisible(false);
            p.vegtiffinPanel.setVisible(false);
            MainFrame.setContentPane(imgPanel);

        } else if (ae.getSource() == exitMenuItem) {
            String[] options = {"Its Awesome", "It's Good", "Okay",  "Hate It"};
            String feedback = (String) JOptionPane.showInputDialog(null,"How is your experience ?","Feedback",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

            if (feedback == null){ // If user Choose to cancel the Feedback
                // Here is new JOptionPane To request the user to give the feedback
                int input = JOptionPane.showConfirmDialog(null,"Plz give us Feedback!!","Request",JOptionPane.YES_NO_OPTION);

//               yes = 0 and No = 1
                if(input == 1){ // If user still don't want to give the feedback then it exit from the system
                    System.out.println("FeedBack "+feedback);
//                    System.exit(1);
                    MainFrame.dispose();
                } else if( input == 0) { // If user change its mind to give the feedback
                    feedback = (String) JOptionPane.showInputDialog(null,"How is your experience ?","Feedback",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                    System.out.println("FeedBack "+feedback);
//                    System.exit(1);
                    MainFrame.dispose();
                }
            } else {
                System.out.println("FeedBack "+feedback);
                MainFrame.dispose();
            }
        } else if (ae.getSource() == cartMenuItem) {
            new CartFrame(id);
        }
    }
}

