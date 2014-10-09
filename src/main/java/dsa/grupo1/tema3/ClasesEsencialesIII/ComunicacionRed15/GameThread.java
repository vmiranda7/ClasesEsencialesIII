package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed15;
import java.io.IOException;
import java.net.Socket;

public class GameThread implements Runnable {
	private Thread thread;
	private Player[] players;
	private int connected = 0;
	private int monedas;

	public GameThread() {
		super();
		players = new Player[2];
	}

	public void addPlayer(Socket socket) {
		try {

			players[connected] = new Player(socket);
			if (connected == 0)
				players[connected].sendWait();
			else {
				thread = new Thread(this);
				thread.start();

			}

			connected++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		players[0].sendVersus(players[1].getName());
		players[1].sendVersus(players[0].getName());

		try {
			players[1].sendWaitBet();
			players[0].sendYourBet();

			players[0].sendBetOf(players[0].getName(), players[0].getBet());
			players[1].sendBetOf(players[0].getName(), players[0].getBet());
			players[0].sendWaitBet();
			players[1].sendYourBet();

			players[1].sendBetOf(players[1].getName(), players[1].getBet());
			players[0].sendBetOf(players[1].getName(), players[1].getBet());
			monedas = players[0].getCoins() + players[1].getCoins();

			if (players[0].getBet() == monedas) {
				players[0].win();
				players[1].lose(players[0].getName());
			}
			if (players[1].getBet() == monedas) {
				players[1].win();
				players[0].lose(players[1].getName());
			}
			if (players[0].getBet() == monedas) {
				players[0].draw();
				players[1].draw();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}