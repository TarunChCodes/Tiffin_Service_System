package TiffinSerSys;

import java.awt.*;
import javax.swing.*;

public class TiffinCat extends JFrame {
    String usrtype, id;

    JMenuBar menuBar;
    JMenu tiffinMenu, pacekedMenu;

    public TiffinCat(String usrtype, String id){
       this.usrtype = usrtype;
       this.id = id;

       JFrame TifcatFrame = new JFrame("Tiffin Cat");
       TifcatFrame.setLayout(new FlowLayout());
       TifcatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       TifcatFrame.setBounds(290,0,1000,725);
       TifcatFrame.setResizable(false);
       TifcatFrame.setBackground(Color.WHITE);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("sherif",Font.PLAIN,16));
        menubar.add(menu);

//        tiffinMenu = new JMenu("Tiffin");
//        tiffinMenu.setFont(new Font("sherif",Font.PLAIN,16));
//        menuBar.add(tiffinMenu);


        TifcatFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TiffinCat("","");
    }
}
