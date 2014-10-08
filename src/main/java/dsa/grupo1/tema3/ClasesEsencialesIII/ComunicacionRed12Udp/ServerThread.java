package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed12Udp;


import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerThread extends Thread {

	protected DatagramSocket socket = null;
	protected BufferedReader in = null;
	protected boolean moreQuotes = true;

	public ServerThread() throws IOException {
		this("ServerThread");
	}

	public ServerThread(String name) throws IOException {
		super(name);
		socket = new DatagramSocket(4445);

		/*
		 * try { in = new BufferedReader(new FileReader("one-liners.txt")); }
		 * catch (FileNotFoundException e) {
		 * System.err.println("Could not open quote file. Serving time instead."
		 * ); }
		 */
	}

	public void run() {

		while (moreQuotes) {
			try {
				byte[] buf = new byte[256];

				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);

				// figure out response
				String dString = null;
						
					dString = getDate();

				buf = dString.getBytes();

				// send the response to the client at "address" and "port"
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
				moreQuotes = false;
			}
		}
		socket.close();
	}

	protected String getDate() {
		String returnValue = null;
		String patron = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat formato = new SimpleDateFormat(patron);
		// formateo
		System.out.println(formato.format(new Date()));
		
		returnValue = formato.format(new Date());
		return returnValue;
	}
}
