
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.math.BigInteger;
import java.util.*;
public class Compose
{
   void c(BufferedReader in, PrintWriter out)
   {
       try
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
                  
                         ResultSet rs2 = stmt.executeQuery("select * from reg");
                        BigInteger N=BigInteger.ONE;
                        BigInteger e=BigInteger.ONE;
                        while(rs2.next())
                        {
                          if(rs2.getString(1).equals(username))
                          {
                             
                                  isThere = true;
                                  N = new BigInteger(rs2.getString(3));
                                   e = new BigInteger(rs2.getString(4));
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
   catch(Exception exx){}
}
}
