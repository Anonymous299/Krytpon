import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.math.BigInteger;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Inbox extends JFrame implements ActionListener, MouseListener
{
    private JFrame frame;
    private Image icon;
    private JPanel panel;
    ArrayList<JButton> button = new ArrayList<JButton>();
    JPanel container;
    JScrollPane scroller;
   
    BufferedReader in;
    PrintWriter out;
    String username ="";
    boolean c=false,l=false;
    
     public BufferedImage requestImage (int n)
    {
        BufferedImage image = null;
        try
        {
            if(n==0)
           //image = ImageIO.read(new File ("KryptonStartScreen.jpg"));
            image = ImageIO.read(new File ("InboxPrototype1.jpg"));
           if(n==1)
           //image = ImageIO.read(new File ("lclick.jpg"));
           image = ImageIO.read(new File ("InboxComposeClickPrototype1.jpg"));
           if(n==2)
           //image = ImageIO.read(new File ("rclick.jpg"));
           image = ImageIO.read(new File ("InboxLoginClickPrototype1.jpg"));
          
        }
        catch (IOException e){};
        return image;
        
    }
    
    public Inbox(String un) throws Exception
    {
        final BufferedImage image = requestImage(0);
        final BufferedImage image2 = requestImage(1);
        final BufferedImage image3 = requestImage(2);
       
        frame = new JFrame();
        panel = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                
                if(c== true)
                {
                g.drawImage(image2,0,0,1366,768,this);
                c = false;
            }
                else if(l == true)
                {
                  g.drawImage(image3,0,0,1366,768,this);
                  l = false;
                }
                
                else
                g.drawImage(image,0,0,1366,768,this);
            
        
       
            }
        };
              try
            {
            connectToServer("192.168.43.65");
        }
        catch(IOException exc){
        
        JOptionPane.showMessageDialog(null,"You are not connected to the internet");
        }
        username = un;
        JTextPane tp = new JTextPane();
        tp.setEditable(false);
        
       
        tp.setBounds(250,290,800,350);
        
        JScrollPane jsp = new JScrollPane(tp);

        StyledDocument doc = tp.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        int i=0;
       try
       {
        out.println(username);
           while(true)//true
        {
             String name = in.readLine();
             if(name.equals("abbashogayabhaipada!@#$"))
             break;
            tp.setCaretPosition(tp.getDocument().getLength());
            JButton b = new JButton("Message from " + name);
            b.setPreferredSize(new Dimension(400,30));
            //b.setBorderPainted(false);
            b.setFocusPainted(false);
            b.setContentAreaFilled(false);
            button.add(b);
            tp.insertComponent(button.get(button.size()-1));
            doc.insertString(doc.getLength(), "\n\n", attr );
            i++;
        }
        jsp.setBounds(250,290,800,350);
        
        getContentPane().add(jsp);
        for(int j=0;j<button.size();j++)
        {
          JButton b= button.get(j);
          b.addActionListener(this);
        }
    }
    catch(IOException e){}
    
    frame.setPreferredSize(new Dimension(1366, 768));

       
        frame.setDefaultCloseOperation
        (JFrame.EXIT_ON_CLOSE);

       
         frame.add(jsp);
        
         frame.add(panel);
        frame.pack();
        
      
      
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addMouseListener(this);
    }
    

    public void actionPerformed(ActionEvent eve) {
        int num;
        for(num=0;num<button.size();num++)
        {
          if(eve.getSource() == button.get(num))
         break;
        }
        out.println(num);
        String message="";
        String password="";
        String theirName="";
        try
        {
         theirName = in.readLine();
            while(true)
       {
           String input = in.readLine();
           if(input.equals("stopitrightnow!@#$"))
           break;
           message += input;
           message += "\n";
        }
            password = in.readLine();
            
    }
    catch(IOException e){}
        ShowMessage sm = new ShowMessage(message, password, username, theirName);
        frame.dispose();
    }

     public void mouseClicked(MouseEvent e) {  
      int x = e.getX();  
       int y = e.getY();
        if(x>50 && x < 225 && y>55 && y<70)
        {
        
        Compose c = new Compose(username);
        frame.dispose();
    }
       else if(x>1215 && x<1327 && y>55 && y<85)
      {
       LoginClient lc = new LoginClient();
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
        if(x>50 && x < 225 && y>55 && y<70)
        {
        c=true;
        
        panel.repaint();
        
    }
       else if(x>1215 && x<1327 && y>55 && y<85)
      {
       l=true;
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

        out.println("thisisainbox!@#$");
       
    }
}
