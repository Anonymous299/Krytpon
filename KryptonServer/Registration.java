
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
public class Registration
{
   void r(BufferedReader in, PrintWriter out)
   {
    try
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
   catch(IOException ex){}
}
}
