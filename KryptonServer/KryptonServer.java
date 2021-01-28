
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
                  ShowMessage obj = new ShowMessage();
                  obj.sm(in,out);
                }
                else if(whichOne.equals("thisisalogin!@#$"))
                {
                       
                    
                   Login obj = new Login();
                   obj.l(in,out);
                
               
            }
                else if(whichOne.equals("thisisaregistration!@#$"))
                {
                 Registration obj = new Registration();
                 obj.r(in,out);
                }
            else if(whichOne.equals("thisisamessagecompose!@#$"))
            {
                Compose obj = new Compose();
                        obj.c(in,out);
                    }
                    else if(whichOne.equals("thisisainbox!@#$"))
                    {
                 
                       Inbox obj = new Inbox();
                       obj.i(in,out);
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
