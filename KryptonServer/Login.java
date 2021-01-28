
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
public class Login
{
    void l(BufferedReader in,PrintWriter out)
    {
     try
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
                }
                catch(IOException exc){}
                }
}
