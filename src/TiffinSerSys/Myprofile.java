package TiffinSerSys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Myprofile extends JFrame implements ActionListener {

    JPanel profilePanel, updatePanel;
    JLabel heading,nameLabel,idLabel,phnoLabel,emailLabel,addLabel;
    JLabel nameText,idText,phnoText, emailText,addText;
    JTextField nameTextField,phnoTextField,emailTextField,addTextField;
    JComboBox<String> domainCombobox;
    JButton updateButton,backButton; // These button is on the profile_Panel
    JButton submitButton; // This Button is on the Update_panel
    String id,usrtype;
    Database db = new Database();

//    Warning Label Used in the validation....

    JLabel nameWar, phnoWar, mailWar, addWar;

    Myprofile(String empid, String usrtype){
        this.id = empid;
        this.usrtype = usrtype;
        setTitle("My Profile");
        setBounds(290,0,1000,725);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // - JLabel use in the Frame are :- 1. Id , 2. Name, 3. phno, 4. Email, 5. Address
        profilePanel = new JPanel();
        profilePanel.setBounds(0,0,1000,725);
        profilePanel.setBackground(Color.orange);
        profilePanel.setLayout(null);
        profilePanel.setVisible(true);

        heading  = new JLabel("PROFILE");
        heading.setFont(new Font("Arial",Font.BOLD,25));
        profilePanel.add(heading);
        heading.setBounds(250,30,200,30);

        idLabel = new JLabel("ID ");
        idLabel.setFont(new Font("Damask",Font.BOLD,16));
        idLabel.setBounds(50,80,100,30);
        profilePanel.add(idLabel);

        idText = new JLabel("ID TEXT");
        idText.setBounds(150,80,200,30);
        idText.setFont(new Font("Arial",Font.ITALIC,14));
        profilePanel.add(idText);

        nameLabel = new JLabel("Name ");
        nameLabel.setFont(new Font("Damask",Font.BOLD,16));
        nameLabel.setBounds(50,120,100,30);
        profilePanel.add(nameLabel);

        nameText = new JLabel("NAME TEXT");
        nameText.setFont(new Font("Arial",Font.ITALIC,14));
        nameText.setBounds(150,120,200,30);
        profilePanel.add(nameText);

        phnoLabel = new JLabel("Contact ");
        phnoLabel.setFont(new Font("Damask",Font.BOLD,16));
        phnoLabel.setBounds(50,160,100,30);
        profilePanel.add(phnoLabel);

        phnoText = new JLabel("CONTACT");
        phnoText.setFont(new Font("Arial",Font.ITALIC,14));
        phnoText.setBounds(150,160,200,30);
        profilePanel.add(phnoText);

        emailLabel = new JLabel("E-Mail ");
        emailLabel.setFont(new Font("Damask",Font.BOLD,16));
        emailLabel.setBounds(50,200,100,30);
        profilePanel.add(emailLabel);

        emailText = new JLabel("EMAIL TEXT");
        emailText.setFont(new Font("Arial",Font.ITALIC,14));
        emailText.setBounds(150,200,220,30);
        profilePanel.add(emailText);

        addLabel = new JLabel("Address ");
        addLabel.setFont(new Font("Damask",Font.BOLD,16));
        addLabel.setBounds(50,240,100,30);
        profilePanel.add(addLabel);

        addText = new JLabel("ADDRESS TEXT");
        addText.setFont(new Font("Arial",Font.ITALIC,14));
        addText.setBounds(150,240,400,30);
        profilePanel.add(addText);

        updateButton = new JButton("Update Profile");
        updateButton.setFont(new Font("Sans-serif",Font.PLAIN,15));
        updateButton.setBounds(80,300,150,30);
        updateButton.setForeground(Color.white);
        updateButton.setBackground(Color.BLACK);
        updateButton.setBorderPainted(false);
        updateButton.setFocusPainted(false);
        profilePanel.add(updateButton);
        updateButton.addActionListener(this);

        backButton =  new JButton("Back");
        backButton.setFont(new Font("Sans-serif",Font.PLAIN,15));
        backButton.setBounds(80,350,150,30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        profilePanel.add(backButton);
        backButton.addActionListener(this);

        // -- Update_Panel --
        updatePanel = new JPanel();
        updatePanel.setBounds(0,0,1000,725);
        updatePanel.setLayout(null);
        updatePanel.setBackground(Color.lightGray);
        updatePanel.setVisible(false);


        nameTextField = new JTextField();
        nameTextField.setBounds(150,120,250,25);
        updatePanel.add(nameTextField);

        phnoTextField = new JTextField();
        phnoTextField.setBounds(150,160,250,25);
        updatePanel.add(phnoTextField);

        emailTextField = new JTextField();
        emailTextField.setBounds(150,200,125,25);
        updatePanel.add(emailTextField);

        domainCombobox = new JComboBox<>();
        domainCombobox.addItem("@gmail.com");
        domainCombobox.addItem("@yahoo.com");
        domainCombobox.addItem("@cyberdude.com");
        domainCombobox.addItem("@hackermail.com");
        domainCombobox.addItem("@opensource.com");
        domainCombobox.setBounds(280,200,120,25);

        updatePanel.add(domainCombobox);

        addTextField = new JTextField();
        addTextField.setBounds(150,240,250,25);
        updatePanel.add(addTextField);



        submitButton =  new JButton("Submit");
        submitButton.setFont(new Font("Sans-serif",Font.PLAIN,15));
        submitButton.setBounds(80,300,150,30);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        updatePanel.add(submitButton);
        submitButton.addActionListener(this);

        add(profilePanel);
        add(updatePanel);
        setVisible(true);

        try{
            ResultSet rs;
            if(usrtype.equals("Admin") || usrtype.equals("Employee")){
               rs = db.s.executeQuery("select * from userdet where empid= '"+id+"' ");
            } else {
               rs = db.s.executeQuery("select * from userdet where custid= '"+id+"' ");
            }

            if(rs.next()){
                idText.setText(id);
                nameText.setText(rs.getString("name"));
                phnoText.setText(rs.getString("phno"));
                emailText.setText(rs.getString("email"));
                addText.setText(rs.getString("address"));
                JOptionPane.showMessageDialog(null,"Data Retrieved Successfully");

                nameTextField.setText(rs.getString("name"));
                phnoTextField.setText(rs.getString("phno"));
//                Code to Remove the domain name or to extract the usrname from mail and set to emailTextField
                String e_mail = rs.getString("email");
                String usrName = "";
                for(int i=0 ; i<e_mail.length(); i++) {
                    if(e_mail.charAt(i)!='@') {
                        usrName += e_mail.charAt(i);
                    } else {
                        break;
                    }
                }
                emailTextField.setText(usrName);
                addTextField.setText(rs.getString("address"));
            }


        } catch (Exception e){
            e.printStackTrace();
        }

//        WARNING LABEL

        nameWar = new JLabel("");
        nameWar.setBounds(410,120,250,25);
        nameWar.setForeground(Color.red);
        nameWar.setFont(new Font("", Font.ITALIC, 14));
        updatePanel.add(nameWar);

        phnoWar = new JLabel("");
        phnoWar.setBounds(410,160,250,25);
        phnoWar.setForeground(Color.red);
        phnoWar.setFont(new Font("", Font.ITALIC, 14));
        updatePanel.add(phnoWar);

        mailWar = new JLabel("");
        mailWar.setBounds(410,200,250,25);
        mailWar.setForeground(Color.red);
        mailWar.setFont(new Font("", Font.ITALIC, 14));
        updatePanel.add(mailWar);

        addWar = new JLabel("");
        addWar.setBounds(410,240,250,25);
        addWar.setForeground(Color.red);
        addWar.setFont(new Font("", Font.ITALIC, 14));
        updatePanel.add(addWar);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == updateButton) {
            profilePanel.setVisible(false);
            updatePanel.add(heading);
            heading.setText("Update Profile");
            updatePanel.add(nameLabel);
            updatePanel.add(phnoLabel);
            updatePanel.add(emailLabel);
            updatePanel.add(addLabel);
            updatePanel.add(backButton);
            updatePanel.setVisible(true);

            nameTextField.requestFocus();

        } else if (ae.getSource()==backButton) {
            if(profilePanel.isVisible()){
                setVisible(false);
            } else if (updatePanel.isVisible()) {
                profilePanel.setVisible(true);
                updatePanel.setVisible(false);
                profilePanel.add(nameLabel);
                profilePanel.add(phnoLabel);
                profilePanel.add(emailLabel);
                profilePanel.add(addLabel);
                profilePanel.add(backButton);
                heading.setText("Profile");

                try{
                    ResultSet rs;
                    if(usrtype.equals("Admin") || usrtype.equals("Employee")){
                        rs = db.s.executeQuery("select * from userdet where empid= '"+id+"' ");
                    } else {
                        rs = db.s.executeQuery("select * from userdet where custid= '"+id+"' ");
                    }

                    if(rs.next()){
                        idText.setText(id);
                        nameText.setText(rs.getString("name"));
                        phnoText.setText(rs.getString("phno"));
                        emailText.setText(rs.getString("email"));
                        addText.setText(rs.getString("address"));
//                        JOptionPane.showMessageDialog(null,"Data Retrieved Successfully");

                        nameTextField.setText(rs.getString("name"));
                        phnoTextField.setText(rs.getString("phno"));
//                        Code to Remove the domain name or to extract the usrname from mail and set to emailTextField
                        String e_mail = rs.getString("email");
                        String usrName = "";
                        for(int i=0 ; i<e_mail.length(); i++) {
                            if(e_mail.charAt(i)!='@') {
                                usrName += e_mail.charAt(i);
                            } else {
                                break;
                            }
                        }
                        emailTextField.setText(usrName);
                        addTextField.setText(rs.getString("address"));
                    }


                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        } else if (ae.getSource()==submitButton) {

            String nameStr = nameTextField.getText();
            String phnoStr = phnoTextField.getText();
            String mailusrnameStr = emailTextField.getText();
            String addStr  = addTextField.getText();

            String mail = mailusrnameStr + domainCombobox.getSelectedItem();

            boolean flag = true; // It mean All validation are true and Data is ready to send.
            boolean flag2 = true;

//             Here two things happen First is Validation of the TextFields of UpdateProfile Panel and second Sending the Data to the Database (If format id valid)!!

            if( !(nameStr.equals("") || phnoStr.equals("") || mailusrnameStr.equals("") || addStr.equals(""))) {
                Validation v = new Validation();

                flag = v.nameValidate(nameStr);
                if( flag == false) {
                    nameWar.setText("Invalid Formate!!");
                    flag2=false;
                } else {
                    nameWar.setText("");
                }

                flag = v.contactValidate(phnoStr);
                if( flag == false) {
                    phnoWar.setText("Invalid Formate!!");
                    flag2=false;
                } else {
                    phnoWar.setText("");
                }

                flag = v.emailValidate(mailusrnameStr);
                if( flag == false) {
                    mailWar.setText("Invalid Formate!!");
                    flag2=false;
                } else {
                    mailWar.setText("");
                }

                flag = v.addressValidate(addStr);
                if( flag == false) {
                    addWar.setText("Invalid Formate!!");
                    flag2=false;
                } else {
                    addWar.setText("");
                }

            } else {
                JOptionPane.showMessageDialog(null,"Plz Enter The Fields !!");
                flag2 = false;
            }

            if(flag2 == true) {
                try {
                    if(usrtype.equals("Admin") || usrtype.equals("Employee")){
                        db.s.executeUpdate("update userdet set name = '"+nameStr+"', phno = '"+phnoStr+"', email = '"+mail+"', address = '"+addStr+"'  where empid = '"+id+"' ");
                        JOptionPane.showMessageDialog(null,"Profile Updated Successfully (●'◡'●)");
                    } else {
                        db.s.executeUpdate("update userdet set name = '"+nameStr+"', phno = '"+phnoStr+"', email = '"+mail+"', address = '"+addStr+"'  where custid = '"+id+"' ");
                        JOptionPane.showMessageDialog(null,"Profile Updated Successfully (●'◡'●)");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

        }
    }


    public static void main(String[] args) {
        new Myprofile("","");
    }
}
