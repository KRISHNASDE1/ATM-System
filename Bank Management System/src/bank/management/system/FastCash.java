package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton pay1,pay2,pay3,pay4,pay5,pay6,back;
    
    String pinnumber;
    
    FastCash (String pinnumber)  {
        this.pinnumber = pinnumber;
        setLayout(null);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel (i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        
        JLabel text = new JLabel ("SELECT WITHDRAWL AMOUNT");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        
        image.add(text);
        
        
        pay1 = new JButton("Rs 100");
        pay1.setBounds(170, 415, 150, 30);
        pay1.addActionListener(this);
        image.add(pay1);
        
        
        pay2 = new JButton("Rs 500");
        pay2.setBounds(355, 415, 150, 30);
        pay2.addActionListener(this);
        image.add(pay2);
       
        
        pay3 = new JButton("Rs 1000");
        pay3.setBounds(170, 450, 150, 30);
        pay3.addActionListener(this);
        image.add(pay3);
       
        pay4 = new JButton("Rs 2000");
        pay4.setBounds(355, 450, 150, 30);
        pay4.addActionListener(this);
        image.add(pay4);
       
        
        pay5 = new JButton("Rs 5000");
        pay5.setBounds(170, 485, 150, 30);
        pay5.addActionListener(this);
        image.add(pay5);
      
        
        pay6 = new JButton("Rs 10000");
        pay6.setBounds(355, 485, 150, 30);
        pay6.addActionListener(this);
        image.add(pay6);
      
        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
      
        
        
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
       
        setVisible(true);
        
        
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        
        }  
        else {
            String amount = ((JButton)ae.getSource()).getText().substring(3);  //Rs 500
            Conn c = new Conn();
            try{
                ResultSet rs  = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                int balance = 0;
                while(rs.next()){
                    if(rs.getString("type").equals("Deposit")){
                        balance += Integer.parseInt( rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                
                
                if(ae.getSource() != back && balance < Integer.parseInt(amount)){
                 JOptionPane.showMessageDialog(null, "Insuffient Balance");
                 return;
                }
                Date  date = new Date();
                String query = "insert into bank Values('"+pinnumber+"','"+date+"','Withdrawl','"+amount+"')";
                c.s.executeUpdate(query);    
                JOptionPane.showMessageDialog(null, "Rs " + amount + "Debitted SuccessFully!");
                
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                
            } catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    
    public static void main(String [] args){
        new FastCash("");
    }
}

