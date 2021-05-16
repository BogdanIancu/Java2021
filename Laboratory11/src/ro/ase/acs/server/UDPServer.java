package ro.ase.acs.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UDPServer extends Thread {
	private static Scanner scanner = new Scanner(System.in);
	private DatagramSocket socket;
	
	public UDPServer(DatagramSocket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				byte[] buffer = new byte[256];
				DatagramPacket packetToReceive =
						new DatagramPacket(buffer, buffer.length);
				socket.receive(packetToReceive);
				
				String receivedMessage = 
						new String(packetToReceive.getData());
				System.out.println(receivedMessage);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int port = 8000;
		
		try(DatagramSocket socket = new DatagramSocket(port)) {
			System.out.println("Server started on port " + port);
			
			byte[] buffer = new byte[256];
			DatagramPacket packetToReceive =
					new DatagramPacket(buffer, buffer.length);
			socket.receive(packetToReceive);
			
			String receivedMessage = 
					new String(packetToReceive.getData());
			System.out.println(receivedMessage);
			
			UDPServer server = new UDPServer(socket);
			server.start();
			
			while(true) {
				System.out.print("Message: ");
				String messageToSend = scanner.nextLine();
				byte[] bytes = messageToSend.getBytes();
				
				DatagramPacket packetToSend =
						new DatagramPacket(bytes, bytes.length,
								packetToReceive.getAddress(),
								packetToReceive.getPort());
				socket.send(packetToSend);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
