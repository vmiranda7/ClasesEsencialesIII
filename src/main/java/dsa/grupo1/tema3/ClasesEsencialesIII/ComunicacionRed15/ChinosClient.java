package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChinosClient {
	public static void main(String args[]) throws Exception {
		Socket socket = new Socket("localhost", 4567);
		System.out.println("introduzca su nombre");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		String name = br.readLine();
		int coins;
		int bet;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

		writer.println("PLAY " + name);
		boolean finished = false;
		while (!finished) {
			String response = reader.readLine();
			if (response.startsWith("WAIT"))
				System.out.println(response);
			else if (response.startsWith("VERSUS"))
				System.out.println(response);
			else if (response.startsWith("YOUR BET")) {
				System.out.println("introduzca el numero de monedas apostadas");
				coins = Integer.parseInt(br.readLine());
				System.out.println("introduzca el numero de monedas totales");
				bet = Integer.parseInt(br.readLine());
				writer.println("MY BET " + coins + " " + bet);
			} else if (response.startsWith("BET OF")) {

				System.out.println(response);
			} else if (response.startsWith("WINNER")
					|| response.startsWith("NONE")) {

				System.out.println(response);
				finished = !finished;
			}

		}

	}
}
