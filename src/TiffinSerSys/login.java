package TiffinSerSys;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener, KeyListener {

    JFrame frame = new JFrame();
    JPanel login_panel, forgetPass_panel;
    JTextField usernameTextField, usernameTextFieldf, answerTextField, newPasswordTextField, confirmPasswordTextField;
    JPasswordField passTextField;
    JButton loginButton, forgetButton, signupButton, updateButton, backButton;
    JComboBox<String> loginAsComboBox, securityQuesComboBox;
    JLabel newPasswordLabel, confirmPasswordLabel;
    Database db = new Database();

    // Warning JLabel
    JLabel newPassWar,conPassWar;

    login() {
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Login");


        // Login Panel
        login_panel = new JPanel() {

            public void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/Loginicon5.jpg"));
                Image i = img.getImage();
                g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        login_panel.setLayout(null);
        login_panel.setSize(frame.getWidth(), frame.getHeight());
        // login_panel.setBackground(Color.lightGray);

        JLabel usernameLabel = new JLabel("User Name ");
        usernameLabel.setBounds(200, 120, 100, 20);
        usernameLabel.setFont(new Font("Damask", Font.BOLD, 16));
        usernameLabel.setForeground(Color.BLACK);
        login_panel.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password ");
        passwordLabel.setBounds(200, 160, 100, 20);
        passwordLabel.setFont(new Font("Damask", Font.BOLD, 16));
        passwordLabel.setForeground(Color.BLACK);
        login_panel.add(passwordLabel);

        JLabel loginas = new JLabel("LoginAs ");
        loginas.setBounds(200, 200, 100, 20);
        loginas.setFont(new Font("Damask", Font.BOLD, 16));
        loginas.setForeground(Color.BLACK);
        login_panel.add(loginas);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(300, 120, 140, 25);
        usernameTextField.setFont(new Font("", Font.PLAIN, 12));
        login_panel.add(usernameTextField);
        usernameTextField.addKeyListener(this);

        passTextField = new JPasswordField();
        passTextField.setBounds(300, 160, 140, 25);
        passTextField.setFont(new Font("", Font.PLAIN, 18));
        passTextField.setEchoChar('*');
        login_panel.add(passTextField);
        passTextField.addKeyListener(this);

        loginAsComboBox = new JComboBox<>();
        loginAsComboBox.setBounds(300, 200, 140, 25);
        loginAsComboBox.addItem("Admin");
        loginAsComboBox.addItem("Customer");
        loginAsComboBox.addItem("Employee");
        login_panel.add(loginAsComboBox);
        loginAsComboBox.addKeyListener(this);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(220, 255, 90, 30);
        login_panel.add(signupButton);
        signupButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        signupButton.addActionListener(this);

        loginButton = new JButton("Login");
        loginButton.setBounds(330, 255, 90, 30);
        login_panel.add(loginButton);
        loginButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        loginButton.addActionListener(this);
        loginButton.addKeyListener(this);

        forgetButton = new JButton("Forget Password");
        forgetButton.setBounds(220, 300, 200, 30);
        login_panel.add(forgetButton);
        forgetButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        forgetButton.addActionListener(this);


        //-------------------- Forget Password Panel -----------------------

        forgetPass_panel = new JPanel();
        forgetPass_panel.setLayout(null);
        forgetPass_panel.setSize(600, 500);
        //forgetPass_panel.setBackground(Color.lightGray);

        JLabel heading2 = new JLabel("Forget Password");
        heading2.setFont(new Font("Cascadia Code SemiBold", Font.BOLD, 25));
        heading2.setBounds(190, 20, 250, 30);
        forgetPass_panel.add(heading2);

        JLabel usernamef = new JLabel("Username");
        usernamef.setBounds(50, 80, 100, 20);
        usernamef.setFont(new Font("Damask", Font.BOLD, 14));
        forgetPass_panel.add(usernamef);

        JLabel securityQuesLabel = new JLabel("Security Question ");
        securityQuesLabel.setBounds(50, 120, 130, 20);
        securityQuesLabel.setFont(new Font("Damask", Font.BOLD, 14));
        forgetPass_panel.add(securityQuesLabel);

        JLabel securityAnsLabel = new JLabel("Answer ");
        securityAnsLabel.setBounds(50, 160, 200, 20);
        securityAnsLabel.setFont(new Font("Damask", Font.BOLD, 14));
        forgetPass_panel.add(securityAnsLabel);

        usernameTextFieldf = new JTextField();
        usernameTextFieldf.setBounds(200, 85, 180, 25);
        usernameTextFieldf.setFont(new Font("", Font.PLAIN, 12));
        forgetPass_panel.add(usernameTextFieldf);
        usernameTextFieldf.addKeyListener(this);


        securityQuesComboBox = new JComboBox<>();
        securityQuesComboBox.addItem("What is your Date Of Birth?");
        securityQuesComboBox.addItem("What is the name of your pet?");
        securityQuesComboBox.addItem("Your Favourite Color?");
        securityQuesComboBox.setBounds(200, 120, 180, 25);
        forgetPass_panel.add(securityQuesComboBox);
        securityQuesComboBox.addKeyListener(this);

        answerTextField = new JTextField();
        answerTextField.setBounds(200, 165, 180, 25);
        answerTextField.setFont(new Font("", Font.PLAIN, 12));
        forgetPass_panel.add(answerTextField);
        answerTextField.addKeyListener(this);

        newPasswordLabel = new JLabel("New Password ");
        newPasswordLabel.setBounds(50, 200, 180, 20);
        newPasswordLabel.setFont(new Font("Damask", Font.BOLD, 14));
        forgetPass_panel.add(newPasswordLabel);

        confirmPasswordLabel = new JLabel("Confirm Password ");
        confirmPasswordLabel.setBounds(50, 240, 180, 20);
        confirmPasswordLabel.setFont(new Font("Damask", Font.BOLD, 14));
        forgetPass_panel.add(confirmPasswordLabel);

        newPasswordTextField = new JTextField();
        newPasswordTextField.setBounds(200, 205, 180, 25);
        forgetPass_panel.add(newPasswordTextField);
        newPasswordTextField.addKeyListener(this);

        confirmPasswordTextField = new JTextField();
        confirmPasswordTextField.setBounds(200, 245, 180, 25);
        forgetPass_panel.add(confirmPasswordTextField);
        confirmPasswordTextField.addKeyListener(this);

        newPassWar = new JLabel("");
        newPassWar.setFont(new Font("",Font.ITALIC,13));
        newPassWar.setForeground(Color.red);
        newPassWar.setBounds(390, 205, 150, 25);
        forgetPass_panel.add(newPassWar);

        conPassWar = new JLabel("");
        conPassWar.setFont(new Font("",Font.ITALIC,13));
        conPassWar.setForeground(Color.red);
        conPassWar.setBounds(390, 245, 150, 25);
        forgetPass_panel.add(conPassWar);

        updateButton = new JButton("Update");
        updateButton.setBounds(100, 300, 190, 30);
        updateButton.addActionListener(this);
        updateButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(Color.BLACK);
        updateButton.addKeyListener(this);
        forgetPass_panel.add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(100, 340, 190, 30);
        backButton.addActionListener(this);
        backButton.setFont(new Font("Sans-serif ", Font.PLAIN, 15));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
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