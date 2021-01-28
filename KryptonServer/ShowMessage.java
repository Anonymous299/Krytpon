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
public class ShowMessage
{
    void sm(BufferedReader in, PrintWriter out)
    {
     try
                  {
                       Class.forName("com.mysql.jdbc.Driver");  
                        Connection con=DriverManager.getConnection(  
                                "jdbc:mysql://localhost:3306/registration","root","Black12345&");  
                                Statement stmt=con.createStatement();  
                                   ResultSet rs=stmt.executeQuery("select * from reg");
                      String dName = in.readLine();
                      String theirPass="";
                      while(rs.next())
                        {
                          if(rs.getString(1).equals(dName))
                          {
                            theirPass = rs.getString(2);
                            }
                        }
                        out.println(theirPass);
                      String uName = in.readLine();
                      
                     

                        
                        ResultSet rs2=stmt.executeQuery("select * from reg");
                       String dcrypted="", nS="";
                        while(rs2.next())
                        {
                          if(rs2.getString(1).equals(uName))
                          {
                            dcrypted = rs2.getString(5);
                            nS = rs2.getString(3);
                            }
                        }
                        out.println(dcrypted);
                        
                        out.println("stopitrightnow!@#$");
                        out.println(nS);
                       
                    }
                    catch(Exception ex){}
    }
}
