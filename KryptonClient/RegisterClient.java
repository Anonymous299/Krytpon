import javax.swing.*;
import java.math.BigInteger;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.awt.image.BufferedImage;
import java.net.Socket;
import javax.imageio.ImageIO;

public class RegisterClient extends JFrame implements MouseListener{
    private JFrame frame;
    private Image icon;
    private JPanel panel;
    boolean galti=true;
    JTextField tf1;
   
    JPasswordField p1, p2,p3; 
    boolean UseralreadyThere = false, PassalreadyThere = false;
    private BufferedReader in;
    private PrintWriter out;
    boolean h,l,s;
 
   
    public boolean special = false;
    
    public BufferedImage requestImage (int n)
    {
        BufferedImage image = null;
        try
        {
            if(n==0)
           
            image = ImageIO.read(new File ("RegistrationPrototype3.jpg"));
           if(n==1)
         
           image = ImageIO.read(new File ("RegistrationHomeClickPrototype1.jpg"));
           if(n==2)
        
           image = ImageIO.read(new File ("RegistrationLoginClickPrototype1.jpg"));
           if(n==3)
           image = ImageIO.read(new File("RegistrationSubmitClickPrototype1.jpg"));
        }
        catch (IOException e){};
        return image;
        
    }
    
    public RegisterClient()  {
          final BufferedImage image = requestImage(0);
        final BufferedImage image2 = requestImage(1);
        final BufferedImage image3 = requestImage(2);
        final BufferedImage image4 = requestImage(3);
        frame = new JFrame();
        panel = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
               
                if(h == true)
                {
                g.drawImage(image2,0,0,1366,768,this);
                h = false;
            }
                else if(l == true)
                {
                  g.drawImage(image3,0,0,1366,768,this);
                  l = false;
                }
                else if(s == true)
                {
                  g.drawImage(image4,0,0,1366,768,this);
                  s = false;
                }
                else
                g.drawImage(image,0,0,1366,768,this);
            
        
       
            }
        };
        
        Font f1 = new Font("SansSerif", Font.BOLD, 20);
        
        
        tf1= new JTextField();
        tf1.setBounds(480,150,515,50);
        
        tf1.setFont(f1);
  
    
        
         p1 = new JPasswordField();
        p1.setBounds(480,275,515,50);
        
        
        p2 = new JPasswordField();
        p2.setBounds(480,410,515,50);
        
        
         p3 = new JPasswordField();
        p3.setBounds(480,535,515,50);
        
        

    

        
      
        frame.setPreferredSize(new Dimension(1366, 768));

       
        frame.setDefaultCloseOperation
        (JFrame.EXIT_ON_CLOSE);

        
         
        frame.add(tf1);
       
        frame.add(p1);
        frame.add(p2);
        frame.add(p3);
        
        frame.add(panel);
        frame.pack();
       
        
         
       //frame.add(label);
      frame.setLayout(null);
       frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addMouseListener(this);

    }
   
     public void mouseClicked(MouseEvent me) {  
       int x = me.getX();
       int y =  me.getY();
         if(x>10 && x < 122 && y>60 && y<90)
       {
        try
        {
        Display d = new Display();
    }
    catch(IOException exc){}
        frame.dispose();
        }
        else if(x>1215 && x<1340 && y>60 && y<90)
        {
            LoginClient lc = new LoginClient();
            frame.dispose();
        }
        else if(x>547 && x<797 && y>68 && y<718)
        {
             String s1 = tf1.getText();

            char[] s3 = p1.getPassword();
            char[] s4 = p2.getPassword(); 
            char[] s5 = p3.getPassword();
            String s8 = new String(s3);
            String s9 = new String(s4);
            String s10 = new String(s5);
            
           MainControl mc = new MainControl();
            mc.password=s8;
           
            special = false;
            if(s8.equals(s9))
            {
              
                if(s1.equals("") || s1.equals(null) || s8.equals("") || s8.equals(null) || s10.equals("") || s10.equals(null))
                {
                  JOptionPane.showMessageDialog(null,  "None of the fields can be empty.");
                  special = true;
                }
                for(int i = 0; i<s1.length();i++)
               {
                  char s = s1.charAt(i);
                  char ts = '\0';
                  if(i<s8.length())
                   ts = s8.charAt(i);
                   if(s=='~' || s=='#' || s=='%' || s=='&' || s=='*' || s=='{' || s=='}' || s=='[' || s==']' || s=='(' || s==')' || s=='\\' || s==':' || s=='<' || s=='>' || s=='?' || s=='/' || s=='"' || s=='\'')
                   {
                      JOptionPane.showMessageDialog(null, s+ " character cannot be a part of username");
                      special=true;
                      
                    }
                    else if(ts==' ' ||s==' ')
                    {
                       JOptionPane.showMessageDialog(null, "username or password cannot contain space");
                      special=true;
                    }
                }
                
                if(s8.length()>12)
                {
                  JOptionPane.showMessageDialog(null,"Password cannot be more than 12 letters long");
                  special = true;
                }
                else if(s10.length()>16)
                {
                  JOptionPane.showMessageDialog(null,"Encryption key cannot be more than 16 letters long");
                  special = true;
                }
                else if(s1.length()>12)
                {
                  JOptionPane.showMessageDialog(null,"Username cannot be more than 12 letters long");
                  special = true;
                }
                if(!special)
                {
                    try
            {
            connectToServer("192.168.43.65");
        }
        catch(IOException exc){
        
        JOptionPane.showMessageDialog(null,"You are not connected to the internet");
        }
                RSA rsa = new RSA();
                rsa.returnIt();
                BigInteger N = rsa.returnN();
                BigInteger e = rsa.returnE();
                BigInteger d = rsa.returnD();
               
               Blowfish b = new Blowfish();
               String s8_encrypt="";
               try
               {
                String p="";
                   for(int i = 0; i<s1.length();i++)
                {
                  if((s1.length()*i)%2==0)
                  p+=Character.toString(s1.charAt(i));
                  else
                   p+=Character.toString(s1.charAt(s1.length()-i));
                }
                   s8_encrypt = b.encrypt(p,s8);
            }
            catch(Exception exc){}
               
            out.println(s1);
                out.println(s8_encrypt);
                out.println(N);
                out.println(e);
                
                   String response;
                
                try
                {
                    response= in.readLine();
                    if(response.equals("u"))
                    {
                        JOptionPane.showMessageDialog(null, "Username already taken");
                    }
                    else if(response.equals("p"))
                    
                    {
                        JOptionPane.showMessageDialog(null, "Password already taken");
                    }
                    else if(response.equals("n"))
                    {
                    Blowfish bl = new Blowfish();
                    try
                    {
                   
                        String dcrypt = bl.encrypt(s8, d.toString());
                    out.println(dcrypt);
                    out.println("stopitrightnow!@#$");
                    String keycrypt = bl.encrypt(s8,s10);
                    
                    out.println(keycrypt);
                    
                }
                catch(Exception rxx){}
                   
                        JOptionPane.showMessageDialog(null, "Your account has been created!" );
                        Compose comp = new Compose(s1);
                      galti=false;
                        frame.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Some unknown error has occured.Try a different username/password");
                    }
                }
                catch (IOException ex) {
                    response = "Error: " + ex;
                }
          
            }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Password Does Not Match");
            }
          if(galti)
          {
            RegisterClient rc = new RegisterClient();
          frame.dispose();
        }
        }
    }  
    public void mouseEntered(MouseEvent e) {  
        
    }  
    public void mouseExited(MouseEvent e) {  
        
    }  
    public void mousePressed(MouseEvent e) {  
        int x = e.getX();  
       int y = e.getY();
        if(x>10 && x < 122 && y>60 && y<90)
        {
    h=true;
    panel.repaint();
    }
       else if(x>1215 && x<1340 && y>60 && y<90)
      {
       l=true;
       panel.repaint();
    }
    else if(x>547 && x<797 && y>68 && y<718)
    {
      s = true;
      panel.repaint();
    }
    }  
    public void mouseReleased(MouseEvent e) {  
       panel.repaint();
    } 
   
    public void connectToServer(String serverAddress) throws IOException {

        
        Socket socket = new Socket(serverAddress, 9898);
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("thisisaregistration!@#$");
      
    }
  
}
