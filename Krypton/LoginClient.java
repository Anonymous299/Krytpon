
import javax.swing.*;
import java.math.BigInteger;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.net.Socket;


public class LoginClient extends JFrame implements ActionListener{
    JLabel l1, l2,l3;
    JTextField tf1;
    JButton btn1, btn2;
    JPasswordField p1; 
    
    private BufferedReader in;
    private PrintWriter out;
    static double width, height;
    //MainControl mc = new MainControl();
    
    public LoginClient()  {
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         width = screenSize.getWidth();
         height = screenSize.getHeight();
        
        setVisible(true);
        getContentPane().setBackground(Color.black);
        setSize((int)width, (int)height);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Login Form");

        l1 = new JLabel("Registration:");
        l1.setForeground(Color.green);
        l1.setFont(new Font("Comic Sans MS", Font.BOLD, 70));

        l2 = new JLabel("Username:");
        l2.setForeground(Color.white);
        l2.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
        l3 = new JLabel("Create Password:");
        l3.setForeground(Color.white);
        l3.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
        
        tf1 = new JTextField();

        p1 = new JPasswordField();
       
        btn1 = new JButton("Login");
        btn2 = new JButton("Clear");

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        l1.setBounds(520, 70, 450, 95);
        l2.setBounds(380, 270, 200, 30);

        l3.setBounds(300, 350, 250, 30);
       
        tf1.setBounds(600, 270, 200, 30);

        p1.setBounds(600, 350, 200, 30);
       
        btn1.setBounds(550, 650, 100, 30);
        btn2.setBounds(670, 650, 100, 30);

        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        
        add(p1);
       
        add(btn1);
        add(btn2);

    }
    // Add Listeners
    // dataField.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent eve) {
        if(eve.getSource() == btn1)
        {
            String s1 = tf1.getText();

            char[] s3 = p1.getPassword();
            
            String s8 = new String(s3);
       
          try
          {
            connectToServer("171.50.210.42");
         
        }
            catch(Exception exc){
             try
            {
              connectToServer("192.168.1.90");
            }
            catch(Exception exc2){
            JOptionPane.showMessageDialog(btn1, "You are not connected to the internet!");
            }
            }
    
            /*try
            {
              connectToServer();
            }
        catch(IOException exc){}*/
               
               
                out.println(s1);
                out.println(s8);
                
               
              
              
                String response;

                try
                {
                    response= in.readLine();
                    if(response.equals("c"))
                    {
                      JOptionPane.showMessageDialog(btn1, "Login successful!");
                      Compose comp = new Compose(s1);
                   
                     setVisible(false);
                    }
                    else if(response.equals("w"))
                    {
                      JOptionPane.showMessageDialog(btn1, "Username or password is incorrect");
                    }
                    else
                    {
                      JOptionPane.showMessageDialog(btn1, "Some unknown error has occured.Please reopen the program.");
                    }
                }
                catch (IOException ex) {
                    response = "Error: " + ex;
                }
            
            
            
           
        }
        else
        {
            tf1.setText("");
            p1.setText("");
           
        }
    }
    //});

   
    public void connectToServer(String serverAddress) throws IOException {

        // Get the server address from a dialog box.
        //String serverAddress = "171.50.210.42";
        //String serverAddress = "192.168.1.90";
        // Make connection and initialize streams
        
        Socket socket = new Socket(serverAddress, 9898);
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
         out.println("thisisalogin!@#$");
        // Consume the initial welcoming messages from the server
        /* for (int i = 0; i < 3; i++) {
        System.out.println(in.readLine() + "\n");
        }*/
    }
    
    /*public static void main(String[] args) throws Exception {
    RegisterClient client = new RegisterClient();
    client.connectToServer();

    }*/
}