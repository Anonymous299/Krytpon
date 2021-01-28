import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.math.BigInteger;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Compose extends JFrame  implements MouseListener
{
    private JFrame frame; 
    private Image icon;
    private JPanel panel;
    
    JTextArea tf1;
    JTextField tf2;
    private BufferedReader in;
    private PrintWriter out;
    
    String myName ="";
    
      private JScrollPane sp1;
     boolean i=false,s=false,l=false;
    
   
   
     public BufferedImage requestImage (int n)
    {
        BufferedImage image = null;
        try
        {
            if(n==0)
           
            image = ImageIO.read(new File ("ComposePrototype1.jpg"));
           if(n==1)
           
           image = ImageIO.read(new File ("ComposeInboxClick.jpg"));
           if(n==2)
           
           image = ImageIO.read(new File ("ComposeLoginClickPrototype1.jpg"));
           if(n==3)
           image = ImageIO.read(new File ("ComposeSendClickPrototype1.jpg"));
        }
        catch (IOException e){};
        return image;
        
    }
  
   
    public Compose(String username)
    {
          final BufferedImage image = requestImage(0);
        final BufferedImage image2 = requestImage(1);
        final BufferedImage image3 = requestImage(2);
        final BufferedImage image4 = requestImage(3);
        myName = username;
        frame = new JFrame();
        panel = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                
                if(i== true)
                {
                g.drawImage(image2,0,0,1366,768,this);
                i = false;
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
       
        tf2 = new JTextField();
       tf2.setBounds(350,200,200,50);
       tf2.setFont(f1);

       tf1 = new JTextArea();
       tf1.setBounds(250,290,800,350);
       tf1.setFont(f1);
       tf1.setText("Type your message here.......");
       sp1 = new JScrollPane(tf1);
       sp1.setBounds(250,290,800,350);
       
       
      
        frame.setPreferredSize(new Dimension(1366, 768));

       
        frame.setDefaultCloseOperation
        (JFrame.EXIT_ON_CLOSE);

       
         frame.add(sp1);
         frame.add(tf2);
         frame.add(panel);
        frame.pack();
        
       
      
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addMouseListener(this);
    }
   
    
     public void mouseClicked(MouseEvent eve) {  
       int x = eve.getX();  
       int y = eve.getY();
        if(x>40 && x < 152 && y>60 && y<90)
        {
        try
        {
        Inbox inbox = new Inbox(myName);
    }
    catch(Exception exc){}
        frame.dispose();
    }
       else if(x>1200 && x<1312 && y>70 && y<100)
      {
       LoginClient lc = new LoginClient();
       frame.dispose();
    }
    else if(x>550 && x<722 && y>680 && y<730)
    {
                try
            {
            connectToServer("192.168.43.65");
        }
        catch(IOException exc){
        
        JOptionPane.showMessageDialog(null,"You are not connected to the internet");
        }
            String s1 = tf1.getText();
            String key="";
            try
            {
              
              out.println(myName);
              
              key = in.readLine();
            
            }
            catch(Exception ex){}
            String s2 = tf2.getText();
            Blowfish blowfish = new Blowfish();
            RSA rsa = new RSA();
            BigInteger N=BigInteger.ONE;
            BigInteger e=BigInteger.ONE;
            String pdecrypt ="";
            try
            {
            try
            {
             MainControl mc = new MainControl();
                pdecrypt = blowfish.decrypt(key,mc.password);
                
            }
            catch(Exception exc){}
              
            out.println(blowfish.encrypt(pdecrypt,s1));
              
           
        
            out.println("stopitrightnow!@#$");
           out.println(s2);
          
          N = new BigInteger(in.readLine());
           e = new BigInteger(in.readLine());
           
           }
        catch(Exception ex){}
        
            rsa.setIt(e,N);
            
           String response;
           try
           {
            response = in.readLine();
            if(response.equals("s"))
            {
               byte[] encrypted = rsa.encrypt(key.getBytes());
             out.println(rsa.bytesToString(encrypted));
            
                JOptionPane.showMessageDialog(null, "Message sent successfully!");
            }
            else if(response.equals("n"))
            {
                JOptionPane.showMessageDialog(null, "Wrong username");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Some error has occured. Try restarting the program.");
            }
            }
            catch(IOException ex)
            {
            }
    Compose c = new Compose(myName);
    frame.dispose();
        }
    }  
    public void mouseEntered(MouseEvent e) {  
        
    }  
    public void mouseExited(MouseEvent e) {  
        
    }  
    public void mousePressed(MouseEvent e) {  
        int x = e.getX();  
       int y = e.getY();
        if(x>40 && x < 152 && y>60 && y<90)
        {
        i=true;
        
        panel.repaint();
        
    }
       else if(x>1200 && x<1312 && y>70 && y<100)
      {
       l=true;
       panel.repaint();
    }
    else if(x>550 && x<722 && y>680 && y<730)
    {
        s=true;
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
       
         out.println("thisisamessagecompose!@#$");
       
    }
  
}
