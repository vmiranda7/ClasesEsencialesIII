package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed13;


import java.io.*;
import java.net.*;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        
       /* if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }*/

        String hostName = "localhost";
        int portNumber = 50001;
        Socket timeSocket;
        try {
            timeSocket = new Socket(hostName, portNumber);
            //PrintWriter out = new PrintWriter(timeSocket.getOutputStream(), true);
           BufferedReader in = new BufferedReader(
                new InputStreamReader(timeSocket.getInputStream()));
           /* BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));*/
            String fromServer;
          //  String fromUser;

            fromServer = in.readLine();
                System.out.println(fromServer);
                
                timeSocket.close();  
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
        
    }
    
}