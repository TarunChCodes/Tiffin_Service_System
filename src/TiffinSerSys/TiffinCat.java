package TiffinSerSys;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class TiffinCat extends JFrame implements ActionListener {
    String usrtype, id;
    JFrame TifcatFrame;

    JMenuBar menubar;
    JMenu tiffinMenu, packitemMenu;
    JMenuItem vegTiffin, nonvegTiffin;

    public TiffinCat(String usrtype, String id){
        this.usrtype = usrtype;
        this.id = id;

        TifcatFrame = new JFrame("Tiffin Cat");
        TifcatFrame.setBounds(290,0,1000,725);
        TifcatFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        TifcatFrame.setLayout(null);

        menubar = new JMenuBar();
        TifcatFrame.setJMenuBar(menubar);

//        ---------- Menu ------------
        tiffinMenu = new JMenu("Tiffins");
        tiffinMenu.setFont(new Font("sherif",Font.PLAIN,16));
        menubar.add(tiffinMenu);

        packitemMenu = new JMenu("Packed Items");
        packitemMenu.setFont(new Font("sherif",Font.PLAIN,16));
        menubar.add(packitemMenu);

//        ------------- MenuItems ------------
        vegTiffin = new JMenuItem("Veg Tiffin");
        vegTiffin.setFont(new Font("sherif",Font.PLAIN,14));
        tiffinMenu.add(vegTiffin);

        nonvegTiffin = new JMenuItem("Non-Veg Tiffin");
        nonvegTiffin.setFont(new Font("sherif",Font.PLAIN,14));
        tiffinMenu.add(nonvegTiffin);

        JPanel vegPanel = new JPanel();
        vegPanel.setSize(1000,725);
        vegPanel.setBackground(Color.orange);
        TifcatFrame.add(vegPanel);
        vegPanel.setVisible(false);

        JPanel nonvegPanel = new JPanel();
        nonvegPanel.setSize(1000,725);
        nonvegPanel.setBackground(Color.GREEN);
        TifcatFrame.add(nonvegPanel);
        nonvegPanel.setVisible(false);

        vegTiffin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vegPanel.setVisible(true);
                nonvegPanel.setVisible(false);

            }
        });
        nonvegTiffin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nonvegPanel.setVisible(true);
                vegPanel.setVisible(false);
            }
        });




        TifcatFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TiffinCat("","");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
