package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.Color.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
// global varibale
    
    
    JButton login, Signup, Clear;
    JTextField CardTextField;
    JPasswordField PinTextField;

    Login() {
        setTitle("AUTOMATIC TELLER MACHINE");

        setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image img2 = img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel label = new JLabel(img3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel CardNo = new JLabel("Card No:");
        CardNo.setFont(new Font("Raleway", Font.BOLD, 28));
        CardNo.setBounds(120, 150, 150, 30);
        add(CardNo);

        CardTextField = new JTextField();
        CardTextField.setBounds(300, 150, 230, 30);
        CardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(CardTextField);

        JLabel Pin = new JLabel("PIN:");
        Pin.setFont(new Font("Raleway", Font.BOLD, 28));
        Pin.setBounds(120, 220, 230, 30);
        add(Pin);

        PinTextField = new JPasswordField();
        PinTextField.setBounds(300, 220, 230, 30);
        PinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(PinTextField);

        login = new JButton("SIGN IN ");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(white);
        login.addActionListener(this);
        add(login);
        
        
        // this is my project using java
        

        Clear = new JButton("CLEAR");
        Clear.setBounds(430, 300, 100, 30);
        Clear.setBackground(Color.BLACK);
        Clear.setForeground(white);
        Clear.addActionListener(this);
        add(Clear);

        Signup = new JButton("SIGN UP");
        Signup.setBounds(300, 350, 230, 30);
        Signup.setBackground(Color.BLACK);
        Signup.setForeground(white);
        Signup.addActionListener(this);
        add(Signup);

        getContentPane().setBackground(white);

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Clear) {
            CardTextField.setText("");
            PinTextField.setText("");
        } else if (ae.getSource() == login) {

            Conn conn = new Conn();
            String cardnumber = CardTextField.getText();
            String pinnumber = PinTextField.getText();

            String query = "select * from login where cardnumber = '" + cardnumber + "' and pinnumber = '" + pinnumber + "'";
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card number Or Pin ");
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == Signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
