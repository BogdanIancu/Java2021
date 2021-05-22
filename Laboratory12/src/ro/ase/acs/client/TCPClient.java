package ro.ase.acs.client;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import ro.ase.acs.models.Message;

public class TCPClient {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Message message = new Message();
		System.out.print("Username: ");
		String username = scanner.nextLine();
		message.setSender(username);
		
		try(Socket socket = new Socket("localhost", 8000)) {
			new Thread(() -> {
				try {
					while(true) {
						InputStream inputStream = socket.getInputStream();
						DataInputStream dataInputStream = 
								new DataInputStream(inputStream);
						String receivedMessage = dataInputStream.readUTF();
						System.out.println(receivedMessage);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}).start();
			
			while(true) {
				System.out.print("Message: ");
				String text = scanner.nextLine();
				message.setText(text);
				
				OutputStream outputStream = socket.getOutputStream();
				ObjectOutputStream objectOutputStream = 
						new ObjectOutputStream(outputStream);
				objectOutputStream.writeObject(message);
			}
			

		} catch(Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
