package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed14;

/**
 * Ejercicio 14. Comunicaciones en red
 * Se modifica el protocolo “¿Qué hora es?” de tal modo que el cliente envía al
 * servidor un “0” para indicar que quiere la respuesta en el formato propuesto en el
 * ejercicio1 y un “1” si quiere la respuesta en el siguiente formato texto: [día de la
 * semana], [día del mes] de [mes], [hora]: [minutos]:[segundos]. Por ejemplo:
 * “Sábado, 21 de septiembre de 2013, 17:07:34 ”. Implementarlo para la versión del
 * servicio que queráis, TCP-no concurrente, TCP-concurrente o UDP.
**/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TcpServer {
	public static void main(String[] args) throws IOException {

		/*
		 * if (args.length != 1) {
		 * System.err.println("Usage: java EchoServer <port number>");
		 * System.exit(1); }
		 */

		int portNumber = 5000;

		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			String inputLine = in.readLine();

			System.out.println("server: " + inputLine);
			int entero = Integer.parseInt(inputLine);
			System.out.println("server: " + entero);
			if (entero == 0) {
				System.out.println("ha elegido la opcion 0\n");
				String patron = "dd/MM/yyyy HH:mm:ss";
				SimpleDateFormat formato = new SimpleDateFormat(patron);
				// formateo
				System.out.println(formato.format(new Date()));

				inputLine = formato.format(new Date());
				out.println(inputLine);
			} else if (entero == 1) {
				System.out.println("ha elegido la opcion 1\n");
				String patron = "HH:mm:ss";
				SimpleDateFormat formato = new SimpleDateFormat(patron);
				// formateo
				System.out.println(formato.format(new Date()));

				Calendar calendar = Calendar.getInstance();
				Date fecha1 = new Date();

				String dia = "";

				if (calendar.get(Calendar.DAY_OF_WEEK) == 0)
					dia = "Domingo";
				else if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
					dia = "Lunes";
				else if (calendar.get(Calendar.DAY_OF_WEEK) == 2)
					dia = "Martes";
				else if (calendar.get(Calendar.DAY_OF_WEEK) == 3)
					dia = "Miercoles";
				else if (calendar.get(Calendar.DAY_OF_WEEK) == 4)
					dia = "Jueves";
				else if (calendar.get(Calendar.DAY_OF_WEEK) == 5)
					dia = "Viernes";
				else if (calendar.get(Calendar.DAY_OF_WEEK) == 6)
					dia = "Sábado";

				inputLine = dia + ", "
						+ Integer.toString(calendar.get(Calendar.DATE))
						+ " de "
						+ Integer.toString(calendar.get(Calendar.MONTH))
						+ " de "
						+ Integer.toString(calendar.get(Calendar.YEAR)) + ", "
						+ formato.format(new Date());
				out.println(inputLine);
			}

			// while ((inputLine = in.readLine()) != null) {

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
