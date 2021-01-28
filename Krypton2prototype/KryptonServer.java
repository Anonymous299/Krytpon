
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.math.BigInteger;
public class KryptonServer {

    public static void main(String[] args) throws Exception {
        //System.out.println("The capitalization server is running.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                new Communicator(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Communicator extends Thread {
        private Socket socket;
        private int clientNumber;

        public Communicator(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
        }

        public void run() {
            try {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String whichOne = in.readLine();
                if(whichOne.equals("thisisashowmessage!@#$"))
                {
                   try
                  {
               
                      String uName = in.readLine();
                      
                      Class.forName("com.mysql.jdbc.Driver");  
                        Connection con=DriverManager.getConnection(  
                                "jdbc:mysql://localhost:3306/registration","root","Black12345&");  

                        Statement stmt=con.createStatement();  
                        ResultSet rs=stmt.executeQuery("select * from reg");
                       String dcrypted="", nS="";
                        while(rs.next())
                        {
                          if(rs.getString(1).equals(uName))
                          {
                            dcrypted = rs.getString(5);
                            nS = rs.getString(3);
                            }
                        }
                        out.println(dcrypted);
                        
                        out.println("stopitrightnow!@#$");
                        out.println(nS);
                       
                    }
                    catch(Exception ex){}
                 
                }
                else if(whichOne.equals("thisisalogin!@#$"))
                {
                       
                    
                    String username = in.readLine();
                  String password = in.readLine();
                  boolean isThere = false;
                  try
                  {
               
                     
                      Class.forName("com.mysql.jdbc.Driver");  
                        Connection con=DriverManager.getConnection(  
                                "jdbc:mysql://localhost:3306/registration","root","Black12345&");  

                        Statement stmt=con.createStatement();  
                        ResultSet rs=stmt.executeQuery("select * from reg");
                       
                        while(rs.next())
                        {
                          if(rs.getString(1).equals(username))
                          {
                              if(rs.getString(2).equals(password))
                              {
                                  isThere = true;
                                  break;
                                }
                                
                            }
                        }
                        if(isThere)
                        {
                        con.close();
                            out.println("c");
                     
                    }
                        else
                        out.println("w");
                    }
                    catch(Exception ex){}
                    finally
                {
                 
                   
                
                }
               
            }
                else if(whichOne.equals("thisisaregistration!@#$"))
                {
                 int q=0;
                    
                    while (true) {
                    boolean UseralreadyThere, PassalreadyThere;
                    String username = in.readLine();
                   
                    String password = in.readLine();
                    BigInteger N = new BigInteger(in.readLine()); 
                    String e = in.readLine();
                    int x = 0;
                    try
                    {
                        PreparedStatement pstmt;
                        Class.forName("com.mysql.jdbc.Driver");  
                        Connection con=DriverManager.getConnection(  
                                "jdbc:mysql://localhost:3306/registration","root","Black12345&");  

                        Statement stmt=con.createStatement();  
                        ResultSet rs=stmt.executeQuery("select * from reg");  
                        UseralreadyThere = false;
                        PassalreadyThere = false;
                        while(rs.next())
                        {
                            if(rs.getString(1).equalsIgnoreCase(username))
                            {
                                UseralreadyThere = true;
                                break;
                            }
                            if(rs.getString(2).equals(password))
                            {
                                PassalreadyThere = true;
                                break;
                            }
                        }

                        x++;
                        if(UseralreadyThere)
                        {
                            out.println("u");
                        }
                        else if(PassalreadyThere){
                            out.println("p");
                        }
                        else if (x > 0) 
                        {
                            //
                             out.println("n");
                             String dcrypt = "";
                              while(true)
               {
               String input = in.readLine();
               if(input.equals("stopitrightnow!@#$"))
               break;
                dcrypt += input;
               dcrypt += "\n";
             
            }
                             String key = in.readLine();
                            
                            String insertQuery = "INSERT INTO reg (username, password, N, e, d, k)"
                                +"VALUES"
                                +"(?, ?, ?, ?, ?, ?)";
                            
                                pstmt = con.prepareStatement(insertQuery);
                            pstmt.setString(1, username);
                            pstmt.setString(2, password);
                            pstmt.setString(3, N.toString());
                            pstmt.setString(4, e.toString());
                            pstmt.setString(5, dcrypt);
                            pstmt.setString(6, key);
                            int rowCount = pstmt.executeUpdate();
                         String createTable = "CREATE TABLE "+username+"("
				+ "name VARCHAR(100)  NULL, "
				+ "message TEXT NULL,"
				+ "password TEXT NULL)";
				
                   stmt.executeUpdate(createTable);
                  
                   
                            
                            con.close();
                            q=1;
                            break;
                        }
                    }
                    catch (Exception ex) 
                    {
                        System.out.println(ex);
                    }
                  
                
                
               
                
                }
                }
            else if(whichOne.equals("thisisamessagecompose!@#$"))
            {
                Class.forName("com.mysql.jdbc.Driver");  
                        Connection con=DriverManager.getConnection(  
                                "jdbc:mysql://localhost:3306/registration","root","Black12345&");  

                        Statement stmt=con.createStatement();  
                        ResultSet rs=stmt.executeQuery("select * from reg");
                PreparedStatement pstmt;
                String key = "";
               String message ="";
               String uName = in.readLine();
               while(rs.next())
               {
                  if(rs.getString(1).equals(uName))
                  {
                  key = rs.getString(6);
                break;
                }
                }
                out.println(key);
               
               while(true)
               {
               String input = in.readLine();
               if(input.equals("stopitrightnow!@#$"))
               break;
                message += input;
               message += "\n";
             
            }
             
               String username = in.readLine();
               
              boolean isThere = false;
              
              try
              {
                  
                
                        BigInteger N=BigInteger.ONE;
                        BigInteger e=BigInteger.ONE;
                        while(rs.next())
                        {
                          if(rs.getString(1).equals(username))
                          {
                             
                                  isThere = true;
                                  N = new BigInteger(rs.getString(3));
                                   e = new BigInteger(rs.getString(4));
                                  break;
                                }
                                
                            }
                            if(isThere)
                            {
                                out.println(N);
                                out.println(e);
                                out.println("s");
                               
                            String encrypted = in.readLine();
                                //String password =in.readLine();
                              String insertQuery = "INSERT INTO " + username + " (name, message, password)"
                                +"VALUES"
                                +"(?, ?, ?)";
                               
                            pstmt = con.prepareStatement(insertQuery);
                            pstmt.setString(1, uName);
                            pstmt.setString(2, message);
                             pstmt.setString(3, encrypted);
                             
                            int rowCount = pstmt.executeUpdate();
                            con.close();
                            
                            }
                            else
                            {
                              out.println("n");
                            }
                        }
                        catch(Exception ex){}
                        
                    }
                    else if(whichOne.equals("thisisainbox!@#$"))
                    {
                 
                        try
                    {
                      Class.forName("com.mysql.jdbc.Driver");  
                        Connection con=DriverManager.getConnection(  
                                "jdbc:mysql://localhost:3306/registration","root","Black12345&");  

                        Statement stmt=con.createStatement();
                        String username = in.readLine();
                        String rS = "select * from " + username;
                        ResultSet rs=stmt.executeQuery(rS);
                       
                        while(rs.next())
                        {
                          out.println(rs.getString(1));
                               
                            }
                            out.println("abbashogayabhaipada!@#$");
                            int end = Integer.parseInt(in.readLine());
                            
                            ResultSet rs2=stmt.executeQuery(rS);
                            int i = 0;
                            while(rs2.next())
                            {
                                if(i==end)
                                {
                                  out.println(rs2.getString(1));
                                    out.println(rs2.getString(2));
                                  out.println("stopitrightnow!@#$");
                                  out.println(rs2.getString(3));
                                  break;
                                }
                                i++;
                            }
                    }
                    catch(Exception ex){}
                    }
                }
                 catch(Exception ex){}
                 finally
                 {
                    try
                    {
                      socket.close();
                    }
                    catch (IOException ioe){}
                    }
                    
               
            }
            private void log(String message) {
            System.out.println(message);
        }
        }

        
    }
