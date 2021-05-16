package ro.ase.acs.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try(DatagramSocket socket = new DatagramSocket()) {
			new Thread(() -> {
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
			}).start();
			
			while(true) {
				System.out.print("Message: ");
				String messageToSend = scanner.nextLine();
				byte[] bytes = messageToSend.getBytes();
				DatagramPacket packetToSend =
						new DatagramPacket(bytes, bytes.length,
								InetAddress.getByName("localhost"),
								8000);
				socket.send(packetToSend);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
