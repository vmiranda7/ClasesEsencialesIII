package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread extends Thread {
	
    private Socket socket;
    private DataOutputStream fromServer;
    private DataInputStream toServer;
    private int idSessio;

    public ServerThread(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            fromServer= new DataOutputStream(socket.getOutputStream());
            toServer = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String accion = "";
        try {
        	String patron = "dd/MM/yyyy HH:mm:ss";
			SimpleDateFormat formato = new SimpleDateFormat(patron);
			// formateo
			System.out.println(formato.format(new Date()));

			String inputLine = formato.format(new Date());
			// while ((inputLine = in.readLine()) != null) {
			
                fromServer.writeUTF(inputLine);
            	

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }



        desconnectar();
    }
}