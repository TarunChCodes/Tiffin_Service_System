package TiffinSerSys;

import java.awt.*;
import javax.swing.*;

public class TiffinCat extends JFrame {
    String usrtype, id;

    public TiffinCat(String usrtype, String id){
       this.usrtype = usrtype;
       this.id = id;

       JFrame TifcatFrame = new JFrame("Tiffin Cat");
       TifcatFrame.setLayout(null);
       TifcatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       TifcatFrame.setBounds(290,0,1000,725);
       TifcatFrame.setResizable(false);
       TifcatFrame.setBackground(Color.WHITE);


        TifcatFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TiffinCat("","");
    }
}
