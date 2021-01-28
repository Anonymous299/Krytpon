import javax.imageio.ImageIO;
import javax.swing.*;
import java.math.BigInteger;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.net.Socket;








public class LoginClient extends JFrame implements MouseListener{
    private JFrame frame;
    private Image icon;
    boolean galti =true;
    JTextField tf1;
    
    JPasswordField p1; 
    private JPanel panel;
    private BufferedReader in;
    private PrintWriter out;
   
    boolean h,l,s;
    
    
    
    
     public BufferedImage requestImage (int n)
    {
        BufferedImage image = null;
        try
        {
            if(n==0)
           
            image = ImageIO.read(new File ("LoginPrototype1.jpg"));
           if(n==1)
           
           image = ImageIO.read(new File ("LoginHomeClickPrototype1.jpg"));
           if(n==2)
          
           image = ImageIO.read(new File ("LoginRegisterClickPrototype1.jpg"));
           if(n==3)
           image = ImageIO.read(new File("LoginSubmitClickPrototype1.jpg"));
        }
        catch (IOException e){};
        return image;
        
    }
    
    
     
    
    
    public LoginClient()  {
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
        
        
        
  
    
        
        tf1 = new JTextField();
        tf1.setBounds(450,255,515,50);
        
        tf1.setFont(f1);
        
       
        p1 = new JPasswordField();
        p1.setBounds(450,455,515,50);
        
        

    

        
      
        frame.setPreferredSize(new Dimension(1366, 768));

       
        frame.setDefaultCloseOperation
        (JFrame.EXIT_ON_CLOSE);

        
         
        
        frame.add(tf1);
        
        frame.add(p1);
        
        frame.add(panel);
        frame.pack();
       
        
         
       
      frame.setLayout(null);
       frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addMouseListener(this);
    }
   
    public void mouseClicked(MouseEvent e) {  
       int x = e.getX();
       int y = e.getY();
        if(x>10 && x < 122 && y>60 && y<90)
       {
        try
        {
           Display d = new Display();
        }
        catch(IOException exc){}
           frame.dispose();
        }
        else if(x>1140 && x<1335 && y>60 && y<90)
        {
            RegisterClient rc = new RegisterClient();
            frame.dispose();
        }
        else if(x>547 && x<767 && y>668 && y<718)
        {
             String s1 = tf1.getText();

            char[] s3 = p1.getPassword();
            
            String s8 = new String(s3);
            MainControl mc = new MainControl();
            mc.password=s8;
                 try
            {
            connectToServer("192.168.43.65");
        }
        catch(IOException exc){
        
        JOptionPane.showMessageDialog(null,"You are not connected to the internet");
        }
          
           
               
             
            Blowfish b = new Blowfish();
            String p_encrypted ="";
            
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
                p_encrypted = b.encrypt(p,s8);
            }
            catch(Exception exc){}
                
                
                    out.println(s1);
                out.println(p_encrypted);
                
               
              
              
                String response;

                try
                {
                    response= in.readLine();
                    BigInteger d=null;
                    String key="";
                    if(response.equals("c"))
                    {
                      
                      JOptionPane.showMessageDialog(null, "Login successful!");
                      Compose comp = new Compose(s1);
                   galti = false;
                    frame.dispose();
                    }
                    else if(response.equals("w"))
                    {
                      JOptionPane.showMessageDialog(null, "Username or password is incorrect");
                    }
                    else
                    {
                      JOptionPane.showMessageDialog(null, "Some unknown error has occured.Please reopen the program.");
                    }
                }
                catch (IOException ex) {
                    response = "Error: " + ex;
                }
         if(galti)
         {
                LoginClient lc = new LoginClient();   
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
       else if(x>1140 && x<1335 && y>60 && y<90)
      {
       l=true;
       panel.repaint();
    }
    else if(x>547 && x<767 && y>668 && y<718)
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
         out.println("thisisalogin!@#$");
       
    }
    
   
}
