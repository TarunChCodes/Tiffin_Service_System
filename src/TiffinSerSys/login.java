package TiffinSerSys;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener, KeyListener {

    JFrame frame = new JFrame();
    JPanel login_panel, forgetPass_panel;

    // Login Jpanel components...
        JTextField usernameTextField;
        JPasswordField passTextField;
        JComboBox<String> loginAsComboBox;
        JButton loginButton, forgetButton, signupButton;
    // PackedItem Component
        JTextField  usernameTextFieldf, answerTextField, newPasswordTextField, confirmPasswordTextField;
        JComboBox<String> securityQuesComboBox;
        JLabel newPasswordLabel, confirmPasswordLabel;
        JLabel newPassWar,conPassWar; //used to show warning if password is not upto the requiremwnt OR not matching.
        JButton  updateButton, backButton;
        Database db = new Database();
        

    login() {
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Login");


        // Login Panel
        login_panel = new JPanel() {
            public void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/LoginBG.jpg"));
                Image i = img.getImage();
                g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        login_panel.setLayout(null);
        login_panel.setSize(frame.getWidth(), frame.getHeight());

        JLabel usernameLabel = new JLabel("User Name ");
        usernameLabel.setBounds(200, 150, 120, 20);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setForeground(Color.BLACK);

        JLabel passwordLabel = new JLabel("Password ");
        passwordLabel.setBounds(200, 200, 100, 20);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.BLACK);

        JLabel loginas = new JLabel("LoginAs ");
        loginas.setBounds(200, 250, 100, 20);
        loginas.setFont(new Font("Arial", Font.BOLD, 18));
        loginas.setForeground(Color.BLACK);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(340, 150, 180, 30);
        usernameTextField.setFont(new Font("Consolas", Font.PLAIN, 16));
        usernameTextField.setBorder(null);
        usernameTextField.addKeyListener(this);

        passTextField = new JPasswordField();
        passTextField.setBounds(340, 200, 180, 30);
        passTextField.setFont(new Font("Consolas", Font.PLAIN, 18));
        passTextField.setEchoChar('*');
        passTextField.setBorder(null);
        passTextField.addKeyListener(this);

        loginAsComboBox = new JComboBox<>();
        loginAsComboBox.setBounds(340, 250, 180, 30);
        loginAsComboBox.addItem("Admin");
        loginAsComboBox.addItem("Customer");
        loginAsComboBox.addItem("Employee");
        loginAsComboBox.setBorder(null);
        loginAsComboBox.addKeyListener(this);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(230, 320, 90, 30);
        signupButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        signupButton.addActionListener(this);

        loginButton = new JButton("Login");
        loginButton.setBounds(340, 320, 90, 30);
        loginButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        loginButton.addActionListener(this);
        loginButton.addKeyListener(this);

        forgetButton = new JButton("Forget Password");
        forgetButton.setBounds(230, 370, 200, 30);
        forgetButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        forgetButton.addActionListener(this);

        login_panel.add(usernameLabel);
        login_panel.add(passwordLabel);
        login_panel.add(loginas);
        login_panel.add(usernameTextField); 
        login_panel.add(passTextField);
        login_panel.add(loginAsComboBox);
        login_panel.add(signupButton);
        login_panel.add(loginButton);
        login_panel.add(forgetButton);


        //-------------------- Forget Password Panel -----------------------

        forgetPass_panel = new JPanel();
        forgetPass_panel.setLayout(null);
        forgetPass_panel.setSize(600, 500);
        
        JLabel heading2 = new JLabel("Forget Password");
        heading2.setFont(new Font("Arial", Font.BOLD, 25));
        heading2.setHorizontalAlignment(JLabel.LEFT);
        heading2.setBounds(280, 20, 220, 30);
        
        JLabel usernameLabelf = new JLabel("Username");
        usernameLabelf.setBounds(50, 100, 150, 30);
        usernameLabelf.setHorizontalAlignment(JLabel.LEFT);
        usernameLabelf.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel securityQuesLabel = new JLabel("Security Question ");
        securityQuesLabel.setBounds(50, 150, 150, 30);
        securityQuesLabel.setHorizontalAlignment(JLabel.LEFT);
        securityQuesLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel securityAnsLabel = new JLabel("Answer ");
        securityAnsLabel.setBounds(50, 200, 150, 30);
        securityAnsLabel.setHorizontalAlignment(JLabel.LEFT);
        securityAnsLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        newPasswordLabel = new JLabel("New Password ");
        newPasswordLabel.setBounds(50, 250, 150, 30);
        newPasswordLabel.setHorizontalAlignment(JLabel.LEFT);
        newPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        confirmPasswordLabel = new JLabel("Confirm Password ");
        confirmPasswordLabel.setBounds(50, 300, 180, 30);
        confirmPasswordLabel.setHorizontalAlignment(JLabel.LEFT);
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        usernameTextFieldf = new JTextField();
        usernameTextFieldf.setBounds(250, 100, 200, 30);
        usernameTextFieldf.setFont(new Font("Consolas", Font.PLAIN, 14));
        usernameTextFieldf.setBorder(null);
        usernameTextFieldf.addKeyListener(this);

        securityQuesComboBox = new JComboBox<>();
        securityQuesComboBox.addItem("What is your Date Of Birth?");
        securityQuesComboBox.addItem("What is the name of your pet?");
        securityQuesComboBox.addItem("Your Favourite Color?");
        securityQuesComboBox.setBounds(250, 150, 200, 30);
        securityQuesComboBox.addKeyListener(this);

        answerTextField = new JTextField();
        answerTextField.setBounds(250, 200, 200, 30);
        answerTextField.setFont(new Font("Consolas", Font.PLAIN, 14));
        answerTextField.setBorder(null);
        answerTextField.addKeyListener(this);

        newPasswordTextField = new JTextField();
        newPasswordTextField.setBounds(250, 250, 200, 30);
        newPasswordTextField.setFont(new Font("Consolas", Font.PLAIN, 14));
        newPasswordTextField.setBorder(null);
        newPasswordTextField.addKeyListener(this);

        confirmPasswordTextField = new JTextField();
        confirmPasswordTextField.setBounds(250, 300, 200, 30);
        confirmPasswordTextField.setFont(new Font("Consolas", Font.PLAIN, 14));
        confirmPasswordTextField.setBorder(null);
        confirmPasswordTextField.addKeyListener(this);

        newPassWar = new JLabel("");
        newPassWar.setFont(new Font("",Font.ITALIC,13));
        newPassWar.setForeground(Color.red);
        newPassWar.setBounds(460, 250, 150, 30);
        
        conPassWar = new JLabel("");
        conPassWar.setFont(new Font("",Font.ITALIC,13)); 
        conPassWar.setForeground(Color.red);
        conPassWar.setBounds(460, 300, 150, 30);

        updateButton = new JButton("Update");
        updateButton.setBounds(100, 380, 200, 30);
        updateButton.addActionListener(this);
        updateButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(Color.BLACK);
        updateButton.addKeyListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(100, 430, 200, 30);
        backButton.addActionListener(this);
        backButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);

        forgetPass_panel.add(heading2);
        forgetPass_panel.add(usernameLabelf);
        forgetPass_panel.add(securityQuesLabel);
        forgetPass_panel.add(securityAnsLabel);
        forgetPass_panel.add(newPasswordLabel);
        forgetPass_panel.add(confirmPasswordLabel);
        forgetPass_panel.add(usernameTextFieldf);
        forgetPass_panel.add(securityQuesComboBox);
        forgetPass_panel.add(answerTextField);
        forgetPass_panel.add(newPasswordTextField);
        forgetPass_panel.add(confirmPasswordTextField);
        forgetPass_panel.add(newPassWar);
        forgetPass_panel.add(conPassWar);
        forgetPass_panel.add(updateButton);
        forgetPass_panel.add(backButton);

        frame.add(login_panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new login();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {

            String usrname = usernameTextField.getText();
            String pass = String.valueOf(passTextField.getPassword());
            String usrtype = (String) loginAsComboBox.getSelectedItem();
            String id = "";

            // -- check for empty Fields --
            if (usrname.equals("") || pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter Your Details!!");
            }
            // -- Code for Authentication --
            else {
                try {
                    ResultSet rs = db.s.executeQuery("select * from userdet where usertype = '" + usrtype + "' and username = '" + usrname + "' and pass = '" + pass + "'; ");
                    if (rs.next()) {

                        if (usrtype.equals("Admin") || usrtype.equals("Employee")) {
                            id = rs.getString("empid");
                            new Homepage(id, usrtype);
                            frame.setVisible(false);
                        } else if (usrtype.equals("Customer")) {
                            id = rs.getString("custid");
                            new Homepage(id, usrtype);
                            frame.setVisible(false);
                        }
                        JOptionPane.showMessageDialog(null, "Welcome " + usrtype + " " + id);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Details");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }       
            }

        } else if (ae.getSource() == forgetButton) {
            frame.setTitle("Forget Password ");
            login_panel.setVisible(false);
            frame.add(forgetPass_panel);
            forgetPass_panel.setVisible(true);
            usernameTextFieldf.requestFocus();

        } else if (ae.getSource() == updateButton) {
            // -- Forget Password --
            String usrnmf = usernameTextFieldf.getText();
            String squef = (String) securityQuesComboBox.getSelectedItem();
            String ansf = answerTextField.getText();
            String npass = newPasswordTextField.getText();
            String cpass = confirmPasswordTextField.getText();


            if (usrnmf.equals("") || ansf.equals("") || npass.equals("") || cpass.equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter Your Details!!");
            } else {

                boolean flag = true;
                boolean flag2 = true;

                Validation v = new Validation();

                flag = v.passValidate(npass);
                if(flag == false) {
                    newPassWar.setText("Invalid Format!!");
                    flag2 = false;
                } else {
                    newPassWar.setText("");
                }

                flag = v.confirmPassValidate(cpass,npass);
                if(flag == false) {
                    conPassWar.setText("Invalid Format!!");
                    flag2 = false;
                } else {
                    conPassWar.setText("");
                }

                if (flag2 == true) {
                    try {
                        if (npass.equals(cpass)) {
                            ResultSet rs = db.s.executeQuery("select * from userdet where username = '" + usrnmf + "' and sques = '" + squef + "' and ans = '" + ansf + "' ;");
                            if (rs.next()) {
                                try {
                                    db.s.executeUpdate("update userdet set pass = '" + npass + "' where username ='" + usrnmf + "'; ");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(null, "Password Updates Successfully (●'◡'●)");
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid Details (¬_¬ )");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Enter Same Password!!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
 
                    newPasswordTextField.setText("");
                    confirmPasswordTextField.setText("");
                    newPasswordTextField.requestFocus();
                }

            }
        } else if (ae.getSource() == backButton) {
            forgetPass_panel.setVisible(false);
            login_panel.setVisible(true);
        } else if (ae.getSource() == signupButton) { // For signup page/Frame
            new signup().setVisible(true);
            frame.setVisible(false);


        }

    }

    //
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if(ke.getSource() == usernameTextField || ke.getSource() == passTextField || ke.getSource() == loginAsComboBox || ke.getSource() == loginButton ) {

            if(ke.getKeyCode()==KeyEvent.VK_ENTER) {
//                Code to Authenticate the User fot Login Process

                String usrname = usernameTextField.getText();
                String pass = String.valueOf(passTextField.getPassword());
                String usrtype = (String) loginAsComboBox.getSelectedItem();
                String id = "";

                // -- check for empty Fields --
                if (usrname.equals("") || pass.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter Your Details!!");
                }
                // -- Code for Authentication --
                else {
                    try {
                        ResultSet rs = db.s.executeQuery("select * from userdet where usertype = '" + usrtype + "' and username = '" + usrname + "' and pass = '" + pass + "'; ");
                        if (rs.next()) {

                            if (usrtype.equals("Admin") || usrtype.equals("Employee")) {
                                id = rs.getString("empid");
                                new Homepage(id, usrtype);
                                frame.setVisible(false);
                            } else if (usrtype.equals("Customer")) {
                                id = rs.getString("custid");
                                new Homepage(id, usrtype);
                                frame.setVisible(false);
                            }
                            JOptionPane.showMessageDialog(null, "Welcome " + usrtype + " " + id);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Details");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        } // End of Login Button Logic

        else if(ke.getSource() == usernameTextFieldf || ke.getSource() == securityQuesComboBox || ke.getSource() == answerTextField || ke.getSource() == newPasswordTextField || ke.getSource() == confirmPasswordTextField || ke.getSource() == updateButton ) {

            if(ke.getKeyCode()== KeyEvent.VK_ENTER) {
                // -- Forget Password --
                String usrnmf = usernameTextFieldf.getText();
                String squef = (String) securityQuesComboBox.getSelectedItem();
                String ansf = answerTextField.getText();
                String npass = newPasswordTextField.getText();
                String cpass = confirmPasswordTextField.getText();


                if (usrnmf.equals("") || ansf.equals("") || npass.equals("") || cpass.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter Your Details!!");
                } else {

                    boolean flag = true;
                    boolean flag2 = true;

                    Validation v = new Validation();

                    flag = v.passValidate(npass);
                    if(flag == false) {
                        newPassWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else {
                        newPassWar.setText("");
                    }

                    flag = v.confirmPassValidate(cpass,npass);
                    if(flag == false) {
                        conPassWar.setText("Invalid Format!!");
                        flag2 = false;
                    } else {
                        conPassWar.setText("");
                    }

                    if (flag2 == true) {
                        try {
                            if (npass.equals(cpass)) {
                                ResultSet rs = db.s.executeQuery("select * from userdet where username = '" + usrnmf + "' and sques = '" + squef + "' and ans = '" + ansf + "' ;");
                                if (rs.next()) {
                                    try {
                                        db.s.executeUpdate("update userdet set pass = '" + npass + "' where username ='" + usrnmf + "'; ");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    JOptionPane.showMessageDialog(null, "Password Updates Successfully (●'◡'●)");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid Details (¬_¬ )");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Please Enter Same Password!!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        newPasswordTextField.setText("");
                        confirmPasswordTextField.setText("");
                        newPasswordTextField.requestFocus();
                    }

                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}