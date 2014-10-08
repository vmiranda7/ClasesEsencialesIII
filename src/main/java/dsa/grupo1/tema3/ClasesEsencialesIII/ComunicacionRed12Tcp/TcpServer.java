package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed12Tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Ejercicio 12. Comunicaciones en red
 * Implementar el cliente y el servidor del protocolo “¿Qué hora es?” utilizando tanto
 * TCP como UDP, ambos en modo no-concurrente. El protocolo funciona de la
 * siguiente manera: el cliente abre una conexión contra el servidor y este le responde
 * con la fecha y hora actual según su reloj en formato “[dia del mes]/[mes]/[año]
 * [hora]:[minutos]:[segundos]” todos en formato numérico (por ejemplo, 21/09/2013
 * 17:07:34). Para la realización de este ejercicio utilizar la clase
 * java.text.SimpleDateFormat
 */

public class TcpServer {
	public static void main(String[] args) throws IOException {

		/*
		 * if (args.length != 1) {
		 * System.err.println("Usage: java EchoServer <port number>");
		 * System.exit(1); }
		 */

		int portNumber = 8000;

		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			// BufferedReader in = new BufferedReader(
			// new InputStreamReader(clientSocket.getInputStream()));
			String patron = "dd/MM/yyyy HH:mm:ss";
			SimpleDateFormat formato = new SimpleDateFormat(patron);
			// formateo
			System.out.println(formato.format(new Date()));

			String inputLine = formato.format(new Date());
			// while ((inputLine = in.readLine()) != null) {
			out.println(inputLine);
			// }
			serverSocket.close();
		}

		catch (IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}
