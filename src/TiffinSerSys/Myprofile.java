package TiffinSerSys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Myprofile extends JFrame implements ActionListener {

    JPanel profilePanel, updatePanel;
    JLabel heading, nameLabel, idLabel, phnoLabel, emailLabel, addLabel;
    // profilePanel Components
    JLabel idText, nameText, phnoText, emailText, addText;
    JButton updateButton, backButton;
    // updatePanel Component
    JTextField nameTextField, phnoTextField, emailTextField, addTextField;
    JComboBox<String> emaildomainCombobox;
    JLabel nameWar, phnoWar, mailWar, addWar; //  Warning Label Used in the validation....
    JButton submitButton; 

    String id,usrtype;
    Database db = new Database();    

    Myprofile(String id, String usrtype){
        this.id = id;
        this.usrtype = usrtype;

        setTitle("My Profile");
        setBounds(290,0,1000,725);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Profile_Panel Comonents.... - JLabel use in the Frame are :- 1. Id , 2. Name, 3. phno, 4. Email, 5. Address

        profilePanel = new JPanel();
        profilePanel.setBounds(0,0,1000,725);
        profilePanel.setBackground(Color.orange);
        profilePanel.setLayout(null);
        profilePanel.setVisible(true);

        heading  = new JLabel("PROFILE");
        heading.setFont(new Font("Arial",Font.BOLD,25));
        heading.setBounds(400,30,200,30);

        idLabel = new JLabel("ID ");
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));
        idLabel.setBounds(50,100,100,30);

        nameLabel = new JLabel("Name ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBounds(50,150,100,30);
        
        phnoLabel = new JLabel("Contact ");
        phnoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        phnoLabel.setBounds(50,200,100,30);
       
        emailLabel = new JLabel("E-Mail ");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setBounds(50,250,100,30);

        addLabel = new JLabel("Address ");
        addLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addLabel.setBounds(50,300,100,30);
        profilePanel.add(addLabel);

        idText = new JLabel("ID TEXT");
        idText.setBounds(180,100,200,30);
        idText.setFont(new Font("Consolas", Font.PLAIN, 18));

        nameText = new JLabel("NAME TEXT");
        nameText.setFont(new Font("Consolas", Font.PLAIN, 18));
        nameText.setBounds(180,150,200,30);

        phnoText = new JLabel("CONTACT TEXT");
        phnoText.setFont(new Font("Consolas", Font.PLAIN, 18));
        phnoText.setBounds(180,200,200,30);

        emailText = new JLabel("EMAIL TEXT");
        emailText.setFont(new Font("Consolas", Font.PLAIN, 18));
        emailText.setBounds(180,250,300,30);

        addText = new JLabel("ADDRESS TEXT");
        addText.setFont(new Font("Consolas", Font.PLAIN, 16));
        addText.setBounds(180,300,450,30);

        updateButton = new JButton("Update Profile");
        updateButton.setFont(new Font("Sans-serif",Font.PLAIN,15));
        updateButton.setBounds(180,380,150,30);
        updateButton.setForeground(Color.white);
        updateButton.setBackground(Color.BLACK);
        updateButton.setBorderPainted(false);
        updateButton.setFocusPainted(false);
        updateButton.addActionListener(this);

        backButton =  new JButton("Back");
        backButton.setFont(new Font("Sans-serif",Font.PLAIN,15));
        backButton.setBounds(180,430,150,30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        profilePanel.add(heading);
        profilePanel.add(idLabel);
        profilePanel.add(nameLabel);
        profilePanel.add(phnoLabel);
        profilePanel.add(emailLabel);
        profilePanel.add(idText);
        profilePanel.add(nameText);
        profilePanel.add(phnoText);
        profilePanel.add(emailText);
        profilePanel.add(addText);
        profilePanel.add(updateButton);
        profilePanel.add(backButton);


        // Update_Panel Components....

        updatePanel = new JPanel();
        updatePanel.setBounds(0,0,1000,725);
        updatePanel.setLayout(null);
        updatePanel.setBackground(Color.lightGray);
        updatePanel.setVisible(false);

        nameTextField = new JTextField();
        nameTextField.setBounds(180,150,250,30);
        nameTextField.setFont(new Font("Consolas", Font.PLAIN, 16));
        nameTextField.setBorder(null);

        phnoTextField = new JTextField();
        phnoTextField.setBounds(180,200,250,30);
        phnoTextField.setFont(new Font("Consolas", Font.PLAIN, 16));
        phnoTextField.setBorder(null);
        
        emailTextField = new JTextField();
        emailTextField.setBounds(180,250,125,30);
        emailTextField.setFont(new Font("Consolas", Font.PLAIN, 16));
        emailTextField.setBorder(null);
        
        emaildomainCombobox = new JComboBox<>();
        emaildomainCombobox.addItem("@gmail.com");
        emaildomainCombobox.addItem("@yahoo.com");
        emaildomainCombobox.addItem("@cyberdude.com");
        emaildomainCombobox.addItem("@hackermail.com");
        emaildomainCombobox.addItem("@opensource.com");
        emaildomainCombobox.setBounds(310,250,120,30);
        
        addTextField = new JTextField();
        addTextField.setBounds(180,300,250,30);
        addTextField.setFont(new Font("Consolas", Font.PLAIN, 16));
        addTextField.setBorder(null);

        //        WARNING LABEL

        nameWar = new JLabel("");
        nameWar.setBounds(430,150,250,25);
        nameWar.setForeground(Color.red);
        nameWar.setFont(new Font("", Font.ITALIC, 14));

        phnoWar = new JLabel("");
        phnoWar.setBounds(430,200,250,25);
        phnoWar.setForeground(Color.red);
        phnoWar.setFont(new Font("", Font.ITALIC, 14));

        mailWar = new JLabel("");
        mailWar.setBounds(430,250,250,25);
        mailWar.setForeground(Color.red);
        mailWar.setFont(new Font("", Font.ITALIC, 14));
        
        addWar = new JLabel("");
        addWar.setBounds(430,300,250,25);
        addWar.setForeground(Color.red);
        addWar.setFont(new Font("", Font.ITALIC, 14));
    
        submitButton =  new JButton("Submit");
        submitButton.setFont(new Font("Sans-serif",Font.PLAIN,15));
        submitButton.setBounds(180,380,150,30);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);

        updatePanel.add(nameTextField);
        updatePanel.add(phnoTextField);
        updatePanel.add(emailTextField);
        updatePanel.add(emaildomainCombobox);
        updatePanel.add(addTextField);
        updatePanel.add(nameWar);
        updatePanel.add(phnoWar);
        updatePanel.add(mailWar);
        updatePanel.add(addWar);
        updatePanel.add(submitButton);

        
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
               // JOptionPane.showMessageDialog(null,"Data Retrieved Successfully");

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
    } // End of Constructor...

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
                profilePanel.add(heading);
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

            String mail = mailusrnameStr +  emaildomainCombobox.getSelectedItem();

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
    } // End of ActionPerformed... 

    public static void main(String[] args) {
        new Myprofile("","");
    }
}
