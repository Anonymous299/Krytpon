
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.math.BigInteger;
public class ShowMessage extends JFrame  implements ActionListener
{
     JButton btn1;
    JTextArea tf1;
   
   
    String  message="", password="", name;
    static double width, height;
    public ShowMessage(String message, String password, String username)
    {
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         width = screenSize.getWidth();
         height = screenSize.getHeight();
        setVisible(true);
        getContentPane().setBackground(Color.black);
        setSize((int)width, (int)height);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Show Message");
      this.message = message;
      this.password = password;
       name = username;
        
       tf1=new JTextArea();  
         
       
       
        btn1 = new JButton("Decrypt");
       

        btn1.addActionListener(this);
       
      
        tf1.setBounds(500,330, 400,200); 
        JScrollPane scroll = new JScrollPane(tf1);
        scroll.setBounds(500,330, 400,200);
        tf1.setEditable(false);
        tf1.append(message);
        btn1.setBounds(550, 650, 100, 30);
        

        
        add(scroll);
        
        
        
       
        add(btn1);
        
    }
    public void actionPerformed(ActionEvent eve) {
        
           if(eve.getSource()==btn1)
           {
             
               String[] dn = returnDN(name);
            BigInteger D,N;
            
            D=new BigInteger(dn[0]);
            N=new BigInteger(dn[1]);
            
            Blowfish blowfish = new Blowfish();
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
            
            String oMessage = blowfish.decrypt(message, oPassword);
            tf1.setText(oMessage);
        }
        catch(Exception exc){}
        }
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
}
