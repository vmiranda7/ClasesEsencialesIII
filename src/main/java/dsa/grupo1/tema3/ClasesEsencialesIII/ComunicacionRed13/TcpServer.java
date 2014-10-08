package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed13;

/**
 * Ejercicio 13. Comunicaciones en red
 * Implementar la versión concurrente del ejercicio 1 para TCP.
**/

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class TcpServer {

    public static void main(String args[]) throws IOException {

        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(50001);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexion entrante: "+socket);
                ((ServerThread) new ServerThread(socket, idSession)).start();
                idSession++;
            }

        } catch (IOException ex) {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
