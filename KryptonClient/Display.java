
import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.*;
import sun.audio.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.awt.image.BufferedImage;
class Display extends JFrame implements MouseListener{
    //CLASS FOR CLIENT, NOT SERVER!!!!!!!!!!
    private JFrame frame;
    private Image icon;
    private JLabel label;
    private JButton login,register;
    private JPanel panel, buttonPanel;
    private Container c;
    private boolean lMP=false, rMP = false;
    static double width, height;
    public BigInteger p, q;
     
    
    
    
    
    /*public static void main(String args[]) throws IOException
    {
        new Display();
    }*/

    public BufferedImage requestImage (int n)
    {
        BufferedImage image = null;
        try
        {
            if(n==0)
           //image = ImageIO.read(new File ("KryptonStartScreen.jpg"));
            image = ImageIO.read(new File ("KryptonStartScreen2.jpg"));
           if(n==1)
           //image = ImageIO.read(new File ("lclick.jpg"));
           image = ImageIO.read(new File ("LoginClick.jpg"));
           if(n==2)
           //image = ImageIO.read(new File ("rclick.jpg"));
           image = ImageIO.read(new File ("RegisterClick.jpg"));
        }
        catch (IOException e){};
        return image;
        
    }

    public Display() throws IOException
    {
        this.p=p;
        this.q=q;
        final BufferedImage image = requestImage(0);
        final BufferedImage image2 = requestImage(1);
        final BufferedImage image3 = requestImage(2);
        frame = new JFrame();
        panel = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
               
                if(lMP == true)
                {
                g.drawImage(image2,0,0,1366,768,this);
                lMP = false;
            }
                else if(rMP == true)
                {
                  g.drawImage(image3,0,0,1366,768,this);
                  rMP = false;
                }
                else
                g.drawImage(image,0,0,1366,768,this);
            
        
       
            }
        };
        
        
        
        
        
        

       frame.setPreferredSize(new Dimension(1366, 768));

       
        frame.setDefaultCloseOperation
        (JFrame.EXIT_ON_CLOSE);

         frame.add(panel);
        frame.pack();
       
        
       //frame.add(label);
       //frame.setLocationRelativeTo(null);
      
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addMouseListener(this);
    }

   /* public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register)
        {
            
            RegisterClient client = new RegisterClient();
            
            frame.setVisible(false);
        }
        else if(e.getSource() == login)
        {
          LoginClient client = new LoginClient();
         
            frame.setVisible(false);
        }
    }*/
    public void mouseClicked(MouseEvent e) {  
       int x = e.getX();  
       int y = e.getY();
       if(x>475 && x < 875 && y>400 && y<520)
       {
          LoginClient lc = new LoginClient();
          frame.dispose();
        }
        else if(x>355 && x<1005 && y>575 && y<675)
        {
        RegisterClient rc = new RegisterClient();
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
        if(x>475 && x < 875 && y>400 && y<520)
        {
    lMP=true;
    panel.repaint();
    }
       else if(x>355 && x<1005 && y>575 && y<675)
      {
       rMP=true;
       panel.repaint();
    }
    }  
    public void mouseReleased(MouseEvent e) {  
       panel.repaint();
    } 

   /* private void playMusic()throws IOException
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("Mysterious.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);

        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }

    }*/
}
