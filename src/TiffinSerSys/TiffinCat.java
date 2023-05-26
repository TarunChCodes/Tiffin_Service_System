package TiffinSerSys;

import java.awt.*;
import javax.swing.*;

public class TiffinCat extends JFrame {
    String usrtype, id;
    JFrame TifcatFrame;

    JMenuBar menubar;
    JMenu tiffinMenu, packitemMenu;

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
        tiffinMenu.setFont(new Font("San - sherif",Font.PLAIN,16));
        menubar.add(tiffinMenu);

        packitemMenu = new JMenu("Packed Items");
        packitemMenu.setFont(new Font("san - sherif",Font.PLAIN,16));
        menubar.add(packitemMenu);


        TifcatFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TiffinCat("","");
    }
}
