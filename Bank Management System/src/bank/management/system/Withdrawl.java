package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Withdrawl extends JFrame implements ActionListener {
    
    JTextField amount;
    JButton withdraw,back;
    String pinnumber;
    Withdrawl (String pinnumber) {
        this.pinnumber = pinnumber;
        
        
        
        setLayout(null);
        
        
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800,800,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 800);
        add(image);
        
        
        JLabel text = new JLabel ("Enter the amount you want to Withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(150, 250, 350, 20);
        image.add(text);
        
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(147, 300, 300, 25);
        image.add(amount);
               
        
        
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(315, 425, 140, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);
        
        back = new JButton("Back");
        back.setBounds(315, 460, 140, 30);
        back.addActionListener(this);
        image.add(back);
      
        
        setSize(800,800);
        setLocation(250,0);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "please enter the amount you to Withdraw:");
                }
            else {
                
                try{
                Conn conn = new Conn();
                String query = "insert into bank Values('"+pinnumber+"','"+date+"','withdrawl','"+number+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs "+number+ "Withdraw Successfully! ");
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                } catch(Exception e){
                    System.out.println(e);
                }
            }
        }else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
   public static void main(String [] args){
       new Withdrawl("");
   }  
}
