package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange  extends JFrame implements ActionListener {
    
    JPasswordField pin,repin;
    JButton change,back;
    String pinnumber;
            
    PinChange(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 800);
        add(image);
        
        
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font ("System",Font.BOLD,16));
        text.setBounds(230, 250, 370, 35);
        image.add(text);
        
      
        
          JLabel pintext = new JLabel("NEW PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font ("System",Font.BOLD,16));
        pintext.setBounds(140, 280, 180, 25);
        image.add(pintext);
        
        
        
        pin = new JPasswordField();
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        pin.setBounds(300, 285, 160, 20);
        image.add(pin);
        
      
          JLabel repintext = new JLabel("RE-ENTER NEW PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font ("System",Font.BOLD,16));
        repintext.setBounds(140, 310, 160, 25);
        image.add(repintext);
      
        repin = new JPasswordField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(300, 315, 160, 20);
        image.add(repin);
       
        change = new JButton("CHANGE");
        change.setBounds(300, 425, 150, 30);
        change.addActionListener(this);
        image.add(change);
        
        back = new JButton("BACK");
        back.setBounds(300, 460, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        
        
        setSize(800,800);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == change){
            
        
        
        try{
            
            String npin = pin.getText();
            String rpin = repin.getText();
            
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Enter Pin Does not match");
                return;
            }
            
            if(npin.equals("")){
                 JOptionPane.showMessageDialog(null, "Please Enter New Pin");
                return;
            }
            if(rpin.equals("")){
                 JOptionPane.showMessageDialog(null, "Please Re-Enter New Pin");
                return;
            }
            
            Conn conn = new Conn();
            String query1 = "update bank set pin = '"+rpin+"'where pin ='"+pinnumber+"'";
            String query2 = "update login set pinnumber = '"+rpin+"'where pinnumber ='"+pinnumber+"'";
            String query3 = "update signupthree set pinnumber = '"+rpin+"'where pinnumber ='"+pinnumber+"'";
            
            conn.s.executeUpdate(query1);
            conn.s.executeUpdate(query2);
            conn.s.executeUpdate(query3);
            
              JOptionPane.showMessageDialog(null, "Pin Changed SuccessFully!");
               
              setVisible(false);
              new Transactions(rpin).setVisible(true);
              
            
        }catch(Exception e){
            System.out.println(e);
        }
       } else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
    public static void main(String [] args){
        new PinChange("").setVisible(true);
    }   
}
