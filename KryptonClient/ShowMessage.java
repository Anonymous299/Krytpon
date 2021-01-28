
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.math.BigInteger;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class ShowMessage extends JFrame  implements ActionListener, MouseListener
{
    JButton btn1;
    JTextArea tf1;
    private JPanel panel;
    private JFrame frame; 
    private JScrollPane sp1;
    private Image icon;
    BufferedReader in;
    PrintWriter out;
    String  message="", password="", name, theirN;

    boolean c=false,l=false;

    
    public BufferedImage requestImage (int n)
    {
        BufferedImage image = null;
        try
        {
            if(n==0)

                image = ImageIO.read(new File ("MessagePrototype1.jpg"));
            if(n==1)

                image = ImageIO.read(new File ("MessageInboxClickPrototype1.jpg"));
            if(n==2)

                image = ImageIO.read(new File ("MessageLoginClickPrototype1.jpg"));

        }
        catch (IOException e){};
        return image;

    }

   
    public ShowMessage(String message, String password, String username, String theirName)
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
        this.message = message;
        this.password = password;
        name = username;
        theirN=theirName;
        Font f1 = new Font("SansSerif", Font.BOLD, 20);


        tf1 = new JTextArea();
        tf1.setBounds(250,290,700,350);
        tf1.setFont(f1);

        sp1 = new JScrollPane(tf1);
        sp1.setBounds(250,290,700,350);
        tf1.setEditable(false);

        tf1.append(message);
        btn1 = new JButton("Decrypt");

        btn1.setBounds(550, 650, 100, 30);
        btn1.addActionListener(this);
        frame.setPreferredSize(new Dimension(1366, 768));

       
        frame.setDefaultCloseOperation
        (JFrame.EXIT_ON_CLOSE);

        frame.add(sp1);
        frame.add(btn1);

        frame.add(panel);
        frame.pack();

      
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addMouseListener(this);

    }

    public void actionPerformed(ActionEvent eve) {

        if(eve.getSource()==btn1)
        {
            Blowfish blowfish = new Blowfish();
            try
            {
                connectToServer("192.168.43.65");
            }
            catch(IOException exc){

                JOptionPane.showMessageDialog(null,"You are not connected to the internet");
            }
            BigInteger D=null;
            BigInteger N=null;
            String theirP= "";
            try
            {
                out.println(theirN);
               theirP = in.readLine();
               
                out.println(name);
                String dcrypted = "";
                while(true)
                {
                    String input = in.readLine();
                    if(input.equals("stopitrightnow!@#$"))
                        break;
                    dcrypted += input;
                    dcrypted += "\n";
                }
                String nS = in.readLine();
                MainControl mc = new MainControl();
                String dS = blowfish.decrypt(dcrypted, mc.password); 
                D = new BigInteger(dS);
                N = new BigInteger(nS); 

            }
            catch(Exception ex){}

            RSA rsa = new RSA();
            try
            {
                rsa.setIt2(D,N);
                String[] x = password.split(" ");
                byte[] encrypted = new byte[x.length];
                for(int i=0;i<x.length;i++)
                    encrypted[i] = Byte.parseByte(x[i]);
                byte[] decrypted = rsa.decrypt(encrypted);
                String oPassword = new String(decrypted);

                String ooPassword = "";
                try
                {
                    String p="";
                    for(int i = 0; i<theirN.length();i++)
                    {
                        if((theirN.length()*i)%2==0)
                            p+=Character.toString(theirN.charAt(i));
                        else
                            p+=Character.toString(theirN.charAt(theirN.length()-i));
                    }
                    tf1.setText(oPassword + "  "+ p);
                    ooPassword = blowfish.decrypt(theirP, p);

                }
                catch(Exception exc){}
                String oKey = blowfish.decrypt(oPassword, ooPassword);
                String oMessage = blowfish.decrypt(message, oKey);
                tf1.setText(oMessage);
            }
            catch(Exception exc){}
        }
    }

    public void mouseClicked(MouseEvent e) {  
        int x = e.getX();  
        int y = e.getY();
        if(x>50 && x < 150 && y>55 && y<80)
        {
            try
            {
                Inbox i = new Inbox(name);
            }
            catch(Exception exc){}
            frame.dispose();
        }
        else if(x>1170 && x<1270 && y>55 && y<80)
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
        if(x>50 && x < 150 && y>55 && y<80)
        {
            c=true;

            panel.repaint();

        }
        else if(x>1170 && x<1270 && y>55 && y<80)
        {
            l=true;
            panel.repaint();
        }

    }  

    public void mouseReleased(MouseEvent e) {  
        panel.repaint();
    } 

    String[] returnDN(String username)
    {
        BufferedReader br = null;
        FileReader fr = null;
        String filename = username + ".txt";
        String[] dn=new String[2];
        try {

            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(filename));

            for(int i=0;i<2;i++) {
                sCurrentLine = br.readLine();
                dn[i]=sCurrentLine;
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return dn;
    }

    public void connectToServer(String serverAddress) throws IOException {

        Socket socket = new Socket(serverAddress, 9898);
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("thisisashowmessage!@#$");

    }
}

 