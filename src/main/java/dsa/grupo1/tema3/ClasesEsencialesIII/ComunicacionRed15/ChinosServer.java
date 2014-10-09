package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed15;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChinosServer {

	public static void main(String args[]) {
		int numplayers = 0;
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(4567);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server started");
		GameThread game = new GameThread();

		while (true) {
			try {
				Socket socket = ss.accept();
				System.out.println("client accepted");
				game.addPlayer(socket);
				numplayers++;
				if (numplayers % 2 == 0) {
					game = new GameThread();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
