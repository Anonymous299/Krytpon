
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
public class Inbox
{
   void i(BufferedReader in,PrintWriter out)
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
                       ArrayList<String> dL = new ArrayList<String>();
                        while(rs.next())
                        {
                          out.println(rs.getString(1));
                               
                            }
                            out.println("abbashogayabhaipada!@#$");
                            String response = in.readLine();
                            if(response.equals("d"))
                            {
                               while(true)
                               {
                                String input = in.readLine();
                                if(input.equals("stopitrightnow!@#$"))
                                break;
                                dL.add(input);
                                }
                                for(int i=0;i<dL.size();i++)
                                System.out.println(dL.get(i));
                                
                                
                             
                                for(int j=0;j<dL.size();j++)
                                {
                                  ResultSet rs2 = stmt.executeQuery(rS);
                                  int i=0;
                                  while(rs2.next())
                                  {
                                    if(i==Integer.parseInt(dL.get(j)))
                                  {
                                    String query = "delete from " + username + " where message = ?";
                                    PreparedStatement pstmt1 = con.prepareStatement(query);
                                    pstmt1.setString(1, rs2.getString(2));
                                    pstmt1.execute();
                                      break;
                                    }
                                    
                                    i++;
                                    }
                                }
                            }
                            else 
                            {
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
                        }
                    catch(Exception ex){}
                    }
                }
                