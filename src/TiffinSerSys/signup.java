package TiffinSerSys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class  signup extends JFrame implements ActionListener , KeyListener{

    JLabel nameLabel, userTypeLabel, empIdLabel, customerIdLabel, userNameLabel, passwordLabel, confirmPasswordLabel, squesLabel, ansLabel, phoneLabel, emailLabel, addressLabel, houseStreetNoLabel, colonyLabel, cityLabel, stateLabel, pincodeLabel;
    JTextField nameText, idText, userNameText, passText, confirmPassText, ansField, phoneText, emailText, houseStreetNoText, colonyText, cityText, stateText, pincodeText;
    JComboBox<String> userTypeChoise,squesComboBox, domainComboBox;
    JButton submitButton,backButton;
    Database db = new Database();

//     Warning Labels
        JLabel nameWar, usernmWar, passWar, confirmpassWar, phoneWar, emailWar, houseWar, colonyWar, cityWar, stateWar, pincodeWar;

    public signup() {
        setSize(700, 740);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Once completed change it into the DISPOSE_ON_CLOSE

        JLabel heading = new JLabel("Sign Up Form");
        heading.setFont(new Font("Cascadia Code SemiBold", Font.BOLD, 25));
        heading.setBounds(220, 20, 200, 30);
        add(heading);


        // --- Labels ---

        nameLabel = new JLabel("Name ");
        nameLabel.setBounds(50, 80, 100, 25);
        nameLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(nameLabel);

        userTypeLabel = new JLabel("User Type ");
        userTypeLabel.setBounds(50, 120, 100, 25);
        userTypeLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(userTypeLabel);

        empIdLabel = new JLabel("Employee ID ");
        empIdLabel.setBounds(50, 160, 100, 25);
        empIdLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(empIdLabel);

        customerIdLabel = new JLabel("Customer ID");
        customerIdLabel.setBounds(50, 160, 100, 25);
        customerIdLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(customerIdLabel);
        customerIdLabel.setVisible(false);

        userNameLabel = new JLabel("UserName ");
        userNameLabel.setBounds(50, 200, 100, 25);
        userNameLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(userNameLabel);

        passwordLabel = new JLabel("Password ");
        passwordLabel.setBounds(50, 240, 100, 25);
        passwordLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(passwordLabel);

        confirmPasswordLabel = new JLabel("Confirm Password ");
        confirmPasswordLabel.setBounds(50, 280, 150, 25);
        confirmPasswordLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(confirmPasswordLabel);

        squesLabel = new JLabel("Security Question ");
        squesLabel.setBounds(50, 320, 150, 25);
        squesLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(squesLabel);

        ansLabel = new JLabel("Answer ");
        ansLabel.setBounds(50, 360, 150, 25);
        ansLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(ansLabel);

        phoneLabel = new JLabel("Phone Number ");
        phoneLabel.setBounds(50, 400, 150, 25);
        phoneLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(phoneLabel);

        emailLabel = new JLabel("E-Mail ");
        emailLabel.setBounds(50, 440, 150, 25);
        emailLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(emailLabel);

        addressLabel = new JLabel("Address ");
        addressLabel.setBounds(50, 480, 150, 25);
        addressLabel.setFont(new Font("Damask", Font.BOLD, 14));
        add(addressLabel);

        houseStreetNoLabel = new JLabel("House & Street No :");
        houseStreetNoLabel.setBounds(180, 480, 120, 20);
        houseStreetNoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(houseStreetNoLabel);

        colonyLabel = new JLabel("Colony/Area :");
        colonyLabel.setBounds(180, 500, 150, 25);
        colonyLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(colonyLabel);

        cityLabel = new JLabel("City :");
        cityLabel.setBounds(180, 520, 150, 25);
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(cityLabel);

        stateLabel = new JLabel("State :");
        stateLabel.setBounds(180, 540, 150, 25);
        stateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(stateLabel);

        pincodeLabel = new JLabel("Pincode :");
        pincodeLabel.setBounds(180, 560, 150, 25);
        pincodeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(pincodeLabel);


        // --- TextFields And 3 ComboBox ---

        nameText = new JTextField();
        nameText.setBounds(180, 85,150, 25);
        add(nameText);
        nameText.addKeyListener(this);

        userTypeChoise = new JComboBox<>();
        userTypeChoise.setBounds(180, 120, 150, 25);
        userTypeChoise.addItem("--");
        userTypeChoise.addItem("Admin");
        userTypeChoise.addItem("Employee");
        userTypeChoise.addItem("Customer");
        add(userTypeChoise);
        userTypeChoise.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                String userString = (String) userTypeChoise.getSelectedItem();

                if (userString.equals("Customer")) {
                    empIdLabel.setVisible(false);
                    customerIdLabel.setVisible(true);

                    // Here I have to fetch the Customer last ID and then Increment the Id by 1 and Display it in the IDText Feild.

                    String custid = "";

                    try {
                        ResultSet rs = db.s.executeQuery("select custid from userdet where usertype = 'Customer' ORDER BY custid ASC");
                        while( rs.next() ){
                            custid = "";
                            custid = rs.getString("custid");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(custid.length()>0) {
                        int last_digit = custid.charAt(custid.length()-1) -'0';
                        last_digit++;
                        custid = custid.substring(0,custid.length()-1);
                        custid = custid + last_digit;

                        idText.setText(custid);
                    } else {
                        idText.setText("C001");
                    }
                    
                }
                if (userString.equals("Admin")) {
                    empIdLabel.setVisible(true);
                    customerIdLabel.setVisible(false);

                    // Here I have to fetch the Admin last ID and then Increment the Id by 1 and Display it in the IDText Feild.

                    String admid = "";

                    try {
                        ResultSet rs = db.s.executeQuery("select empid from userdet where usertype = 'Admin' ORDER BY empid ASC");
                        while( rs.next() ){
                            admid = "";
                            admid = rs.getString("empid");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(admid.length()>0) {
                        int last_digit = admid.charAt(admid.length()-1) -'0';
                        last_digit++;
                        admid = admid.substring(0,admid.length()-1);
                        admid = admid + last_digit;

                        idText.setText(admid);
                    } else {
                        idText.setText("AE001");
                    }

                }
                if (userString.equals("Employee")) {
                    empIdLabel.setVisible(true);
                    customerIdLabel.setVisible(false);

                    // Here I have to fetch the Employee last ID and then Increment the Id by 1 and Display it in the IDText Feild.

                    String empid = "";

                    try {

                        ResultSet rs = db.s.executeQuery("select empid from userdet where usertype = 'Employee' ORDER BY empid ASC");

                        while( rs.next() ){
                            empid = "";
                            empid = rs.getString("empid");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(empid.length()>0) {
                        int last_digit = empid.charAt(empid.length()-1) -'0';
                        last_digit++;
                        empid = empid.substring(0,empid.length()-1);
                        empid = empid + last_digit;

                        idText.setText(empid);
                    } else {
                        idText.setText("E001");
                    }
                }
            }
        });

        idText = new JTextField();
        idText.setBounds(180, 165, 150, 25);
        add(idText);
        idText.setEditable(false);
        idText.addKeyListener(this);

        userNameText = new JTextField();
        userNameText.setBounds(180, 205, 150, 25);
        add(userNameText);
        userNameText.addKeyListener(this);

        passText = new JTextField();
        passText.setBounds(180, 245, 150, 25);
        add(passText);
        passText.addKeyListener(this);

        confirmPassText = new JTextField();
        confirmPassText.setBounds(180, 285, 150, 25);
        add(confirmPassText);
        confirmPassText.addKeyListener(this);

        squesComboBox = new JComboBox<>();
        squesComboBox.setBounds(180, 320, 200, 25);
        squesComboBox.addItem("What is your Date Of Birth?");
        squesComboBox.addItem("What is the name of your pet?");
        squesComboBox.addItem("By which name your Friends Call you?");
        squesComboBox.addItem("Your Favourite Color?");
        add(squesComboBox);

        ansField = new JTextField();
        ansField.setBounds(180, 365, 150, 25);
        add(ansField);
        ansField.addKeyListener(this);

        phoneText = new JTextField();
        phoneText.setBounds(180, 405, 150, 25);
        add(phoneText);
        phoneText.addKeyListener(this);

        emailText = new JTextField();
        emailText.setBounds(180, 445, 150, 25);
        add(emailText);
        emailText.addKeyListener(this);

        domainComboBox = new JComboBox<>();
        domainComboBox.setBounds(340, 445, 100, 25);
        domainComboBox.addItem("@gmail.com");
        domainComboBox.addItem("@yahoo.com");
        domainComboBox.addItem("@hackermail.com");
        domainComboBox.addItem("@cyberdude.com");
        add(domainComboBox);

        houseStreetNoText = new JTextField();
        houseStreetNoText.setBounds(300, 479, 150, 20);
        add(houseStreetNoText);
        houseStreetNoText.addKeyListener(this);

        colonyText = new JTextField();
        colonyText.setBounds(300, 501, 150, 20);
        add(colonyText);
        colonyText.addKeyListener(this);

        cityText = new JTextField();
        cityText.setBounds(300, 523, 150, 20);
        add(cityText);
        cityText.addKeyListener(this);

        stateText = new JTextField();
        stateText.setBounds(300, 545, 150, 20);
        add(stateText);
        stateText.addKeyListener(this);

        pincodeText = new JTextField();
        pincodeText.setBounds(300, 567, 150, 20);
        add(pincodeText);
        pincodeText.addKeyListener(this);


        // -- 2 JButton --
        submitButton = new JButton("Sign Up");
        submitButton.setFont(new Font("Sans-serif ",Font.PLAIN,15));
        submitButton.setBounds(80,600,200,30);
        submitButton.setForeground(Color.white);
        submitButton.setBackground(Color.BLACK);
        add(submitButton);
        submitButton.addActionListener(this);
        submitButton.addKeyListener(this);

        JLabel msgLabel = new JLabel("Already Have Account?");
        msgLabel.setBounds(90,640,150,20);
        msgLabel.setFont(new Font("Arial",Font.PLAIN,13));
        add(msgLabel);
        backButton = new JButton("Login");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(Color.blue);
        backButton.setFont(new Font("Sans-serif ",Font.PLAIN,15));
        backButton.setBounds(200,638,100,20);
        add(backButton);
        backButton.addActionListener(this);

//        --- Warning Labels ---

        nameWar = new JLabel("");
        nameWar.setBounds(350, 85,150, 25);
        nameWar.setForeground(Color.red);
        nameWar.setFont(new Font("",Font.ITALIC,13));
        add(nameWar);

        usernmWar = new JLabel("");
        usernmWar.setBounds(350, 205,150, 25);
        usernmWar.setForeground(Color.red);
        usernmWar.setFont(new Font("",Font.ITALIC,13));
        add(usernmWar);

        passWar = new JLabel("");
        passWar.setBounds(350, 245, 150, 25);
        passWar.setForeground(Color.red);
        passWar.setFont(new Font("",Font.ITALIC,13));
        add(passWar);

        confirmpassWar = new JLabel("");
        confirmpassWar.setBounds(350, 285, 150, 25);
        confirmpassWar.setForeground(Color.red);
        confirmpassWar.setFont(new Font("",Font.ITALIC,13));
        add(confirmpassWar);

        phoneWar = new JLabel("");
        phoneWar.setBounds(350, 405,150, 25);
        phoneWar.setForeground(Color.red);
        phoneWar.setFont(new Font("",Font.ITALIC,13));
        add(phoneWar);

        emailWar = new JLabel("");
        emailWar.setBounds(450, 445, 150, 25);
        emailWar.setForeground(Color.red);
        emailWar.setFont(new Font("",Font.ITALIC,13));
        add(emailWar);

        houseWar = new JLabel("");
        houseWar.setBounds(450, 479, 150, 20);
        houseWar.setForeground(Color.red);
        houseWar.setFont(new Font("",Font.ITALIC,13));
        add(houseWar);

        colonyWar = new JLabel("");
        colonyWar.setBounds(450, 501, 150, 20);
        colonyWar.setForeground(Color.red);
        colonyWar.setFont(new Font("",Font.ITALIC,13));
        add(colonyWar);

        cityWar = new JLabel("");
        cityWar.setBounds(450, 523, 150, 20);
        cityWar.setForeground(Color.red);
        cityWar.setFont(new Font("",Font.ITALIC,13));
        add(cityWar);

        stateWar = new JLabel("");
        stateWar.setBounds(450, 545, 150, 20);
        stateWar.setForeground(Color.red);
        stateWar.setFont(new Font("",Font.ITALIC,13));
        add(stateWar);

        pincodeWar = new JLabel("");
        pincodeWar.setBounds(450, 567, 150, 20);
        pincodeWar.setForeground(Color.red);
        pincodeWar.setFont(new Font("",Font.ITALIC,13));
        add(pincodeWar);

        setVisible(true);

    } // End Of Constructor....

    public static void main(String[] args) {
        new signup();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource()==backButton){
            new login();
            setVisible(false);

        } else if(ae.getSource()==submitButton) {

//            Here We are Fetching the UserInput from the TextFields..

            String name,usrtype,id,usrnm,pass,cpass,sque,ans,phno,email_username,email_domain,address,email;
            name = nameText.getText();
            usrtype = (String) userTypeChoise.getSelectedItem();
            id = idText.getText();
            usrnm = userNameText.getText();
            pass = passText.getText();
            cpass = confirmPassText.getText();
            sque = (String) squesComboBox.getSelectedItem();
            ans = ansField.getText();
            phno = phoneText.getText(); //            int ph = Integer.parseInt(phno);
            email_username = emailText.getText();
            email_domain = (String) domainComboBox.getSelectedItem();
            email = email_username+email_domain;
            address = houseStreetNoText.getText() +", "+ colonyText.getText() +", "+ cityText.getText()+", "+stateText.getText()+", "+pincodeText.getText();



            boolean flag = true; // It means data is valid and data is ready to send to database.
            boolean flag2 = true; // It means send it.


            if(name.equals("") || id.equals("") || usrnm.equals("") || pass.equals("") || cpass.equals("") || ans.equals("") || phno.equals("") || email.equals("") || colonyText.getText().equals("") || cityText.getText().equals("") || stateText.getText().equals("") || pincodeText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter Details ≡(▔﹏▔)≡");
                flag2 = false;
            } else {

                Validation v = new Validation();

                flag = v.nameValidate(name);
                if(flag == false){
                    nameWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    nameWar.setText("");
                }

                flag = v.usrnameValidate(usrnm);
                if(flag == false){
                    usernmWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    usernmWar.setText("");
                }

                flag = v.passValidate(pass);
                if(flag == false){
                    passWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    passWar.setText("");
                }

                flag = v.confirmPassValidate(cpass, pass);
                if(flag == false){
                    confirmpassWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    confirmpassWar.setText("");
                }

                flag = v.contactValidate(phno);
                if(flag == false){
                    phoneWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    phoneWar.setText("");
                }

                flag = v.emailValidate(email_username);
                if(flag == false){
                    emailWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    emailWar.setText("");
                }

                flag = v.houseValidate(houseStreetNoText.getText());
                if(flag == false){
                    houseWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    houseWar.setText("");
                }

                flag = v.colonyValidate(colonyText.getText());
                if(flag == false){
                    colonyWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    colonyWar.setText("");
                }

                flag = v.cityValidate(cityText.getText());
                if(flag == false){
                    cityWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    cityWar.setText("");
                }

                flag = v.stateValidate(stateText.getText());
                if(flag == false){
                    stateWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    stateWar.setText("");
                }

                flag = v.picodeValidate(pincodeText.getText());
                if(flag == false){
                    pincodeWar.setText("Invalid Format!!");
                    flag2 = false;
                } else  if(flag == true) {
                    pincodeWar.setText("");
                }

            }

            if(flag2 == true){

                try{

                    if (usrtype.equals("Customer")){

                        db.s.executeUpdate(" INSERT INTO `userdet` (`usertype`, `empid`, `custid`, `username`, `pass`, `name`, `phno`, `email`, `address`, `sques`, `ans`) VALUES ('"+usrtype+"', NULL, '"+id+"', '"+usrnm+"', '"+pass+"', '"+name+"', '"+phno+"', '"+email+"', '"+address+"', '"+sque+"', '"+ans+"'); ");
                        JOptionPane.showMessageDialog(null,"Welcome To The Family of TSS😊");

                        nameText.setText("");
                        userTypeChoise.setSelectedIndex(0);
                        idText.setText("");
                        userNameText.setText("");
                        passText.setText("");
                        confirmPassText.setText("");
                        squesComboBox.setSelectedIndex(0);
                        ansField.setText("");
                        phoneText.setText("");
                        emailText.setText("");
                        houseStreetNoText.setText("");
                        colonyText.setText("");
                        cityText.setText("");
                        stateText.setText("");
                        pincodeText.setText("");


                    } else {
                        db.s.executeUpdate(" INSERT INTO `userdet` (`usertype`, `empid`, `custid`, `username`, `pass`, `name`, `phno`, `email`, `address`, `sques`, `ans`) VALUES ('"+usrtype+"', '"+id+"', NULL, '"+usrnm+"', '"+pass+"', '"+name+"', '"+phno+"', '"+email+"', '"+address+"', '"+sque+"', '"+ans+"'); ");
                        JOptionPane.showMessageDialog(null,"Welcome To The Family of TSS😊");

                        nameText.setText("");
                        userTypeChoise.setSelectedIndex(0);
                        idText.setText("");
                        userNameText.setText("");
                        passText.setText("");
                        confirmPassText.setText("");
                        squesComboBox.setSelectedIndex(0);
                        ansField.setText("");
                        phoneText.setText("");
                        emailText.setText("");
                        houseStreetNoText.setText("");
                        colonyText.setText("");
                        cityText.setText("");
                        stateText.setText("");
                        pincodeText.setText("");

                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

        }

    } // End of Action Performed.


    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if(ke.getSource() == nameText || ke.getSource() ==userNameText || ke.getSource() == passText || ke.getSource() == confirmPassText || ke.getSource() == phoneText|| ke.getSource() == emailText || ke.getSource() == ansField || ke.getSource() == houseStreetNoText || ke.getSource() == colonyText || ke.getSource() == cityText || ke.getSource() == stateText ||ke.getSource() == pincodeText) {

            if(ke.getKeyCode()==KeyEvent.VK_ENTER){

//            when you press enter button code will execute

//            Here We are Fetching the UserInput from the TextFields..

                String name,usrtype,id,usrnm,pass,cpass,sque,ans,phno,email_username,email_domain,address,email;
                name = nameText.getText();
                usrtype = (String) userTypeChoise.getSelectedItem();
                id = idText.getText();
                usrnm = userNameText.getText();
                pass = passText.getText();
                cpass = confirmPassText.getText();
                sque = (String) squesComboBox.getSelectedItem();
                ans = ansField.getText();
                phno = phoneText.getText(); //            int ph = Integer.parseInt(phno);
                email_username = emailText.getText();
                email_domain = (String) domainComboBox.getSelectedItem();
                email = email_username+email_domain;
                address = houseStreetNoText.getText() +", "+ colonyText.getText() +", "+ cityText.getText()+", "+stateText.getText()+", "+pincodeText.getText();

                boolean flag = true; // It means data is valid and data is ready to send to database.
                boolean flag2 = true; // It means send it.

                if(name.equals("") || id.equals("") || usrnm.equals("") || pass.equals("") || cpass.equals("") || ans.equals("") || phno.equals("") || email.equals("") || colonyText.getText().equals("") || cityText.getText().equals("") || stateText.getText().equals("") || pincodeText.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please Enter Details ≡(▔﹏▔)≡");
                    flag2 = false;
                } else {
                    Validation v = new Validation();

                    flag = v.nameValidate(name);
                    if(flag == false){
                        nameWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        nameWar.setText("");
                    }

                    flag = v.usrnameValidate(usrnm);
                    if(flag == false){
                        usernmWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        usernmWar.setText("");
                    }

                    flag = v.passValidate(pass);
                    if(flag == false){
                        passWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        passWar.setText("");
                    }

                    flag = v.confirmPassValidate(cpass, pass);
                    if(flag == false){
                        confirmpassWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        confirmpassWar.setText("");
                    }

                    flag = v.contactValidate(phno);
                    if(flag == false){
                        phoneWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        phoneWar.setText("");
                    }

                    flag = v.emailValidate(email_username);
                    if(flag == false){
                        emailWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        emailWar.setText("");
                    }

                    flag = v.houseValidate(houseStreetNoText.getText());
                    if(flag == false){
                        houseWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        houseWar.setText("");
                    }

                    flag = v.colonyValidate(colonyText.getText());
                    if(flag == false){
                        colonyWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        colonyWar.setText("");
                    }

                    flag = v.cityValidate(cityText.getText());
                    if(flag == false){
                        cityWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        cityWar.setText("");
                    }

                    flag = v.stateValidate(stateText.getText());
                    if(flag == false){
                        stateWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        stateWar.setText("");
                    }

                    flag = v.picodeValidate(pincodeText.getText());
                    if(flag == false){
                        pincodeWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else  if(flag == true) {
                        pincodeWar.setText("");
                    }

                }

                if(flag2 == true){

                    try{

                        if (usrtype.equals("Customer")){

                            db.s.executeUpdate(" INSERT INTO `userdet` (`usertype`, `empid`, `custid`, `username`, `pass`, `name`, `phno`, `email`, `address`, `sques`, `ans`) VALUES ('"+usrtype+"', NULL, '"+id+"', '"+usrnm+"', '"+pass+"', '"+name+"', '"+phno+"', '"+email+"', '"+address+"', '"+sque+"', '"+ans+"'); ");
                            JOptionPane.showMessageDialog(null,"Welcome To The Family of TSS😊");

                            nameText.setText("");
                            userTypeChoise.setSelectedIndex(0);
                            idText.setText("");
                            userNameText.setText("");
                            passText.setText("");
                            confirmPassText.setText("");
                            squesComboBox.setSelectedIndex(0);
                            ansField.setText("");
                            phoneText.setText("");
                            emailText.setText("");
                            houseStreetNoText.setText("");
                            colonyText.setText("");
                            cityText.setText("");
                            stateText.setText("");
                            pincodeText.setText("");


                        } else {
                            db.s.executeUpdate(" INSERT INTO `userdet` (`usertype`, `empid`, `custid`, `username`, `pass`, `name`, `phno`, `email`, `address`, `sques`, `ans`) VALUES ('"+usrtype+"', '"+id+"', NULL, '"+usrnm+"', '"+pass+"', '"+name+"', '"+phno+"', '"+email+"', '"+address+"', '"+sque+"', '"+ans+"'); ");
                            JOptionPane.showMessageDialog(null,"Welcome To The Family of TSS😊");

                            nameText.setText("");
                            userTypeChoise.setSelectedIndex(0);
                            idText.setText("");
                            userNameText.setText("");
                            passText.setText("");
                            confirmPassText.setText("");
                            squesComboBox.setSelectedIndex(0);
                            ansField.setText("");
                            phoneText.setText("");
                            emailText.setText("");
                            houseStreetNoText.setText("");
                            colonyText.setText("");
                            cityText.setText("");
                            stateText.setText("");
                            pincodeText.setText("");

                        }

                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }

        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {}

} // Ends Of Class SignUp