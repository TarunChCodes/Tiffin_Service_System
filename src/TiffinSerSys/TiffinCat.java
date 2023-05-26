package TiffinSerSys;

import java.awt.*;
import javax.swing.*;

public class TiffinCat extends JFrame {
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
        TifcatFrame.setLayout(new FlowLayout());

        menubar = new JMenuBar();
        TifcatFrame.setJMenuBar(menubar);

        tiffinMenu = new JMenu("Tiffins");
        tiffinMenu.setFont(new Font("sherif",Font.PLAIN,16));
        menubar.add(tiffinMenu);

        vegTiffin = new JMenuItem("Veg Tiffin");
        vegTiffin.setFont(new Font("sherif",Font.PLAIN,14));
        tiffinMenu.add(vegTiffin);

        nonvegTiffin = new JMenuItem("Non-Veg Tiffin");
        nonvegTiffin.setFont(new Font("sherif",Font.PLAIN,14));
        tiffinMenu.add(nonvegTiffin);

        packitemMenu = new JMenu("Packed Items");
        packitemMenu.setFont(new Font("sherif",Font.PLAIN,16));
        menubar.add(packitemMenu);


        TifcatFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TiffinCat("","");
    }
}
