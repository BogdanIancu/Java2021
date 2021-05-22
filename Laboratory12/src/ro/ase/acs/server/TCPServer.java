package ro.ase.acs.server;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import ro.ase.acs.models.Message;

public class TCPServer extends Thread {
	private static List<Socket> clients = new Vector<>();
	private Socket currentClient;
	
	public TCPServer(Socket currentClient) {
		this.currentClient = currentClient;
	}
	
	private Message receive() throws Exception {
		InputStream inputStream = currentClient.getInputStream();
		ObjectInputStream objectInputStream = 
				new ObjectInputStream(inputStream);
		Message message = (Message)objectInputStream.readObject();
		return message;
	}
	
	private void send(Message message) throws Exception {
		for(Socket s : clients) {
			if(!s.equals(currentClient)) {
				OutputStream outputStream = s.getOutputStream();
				DataOutputStream dataOutputStream =
						new DataOutputStream(outputStream);
				dataOutputStream.writeUTF(message.toString());
			}
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Message m = receive();
				send(m);
			}
		} catch(Exception e) {
			e.printStackTrace();
			clients.remove(currentClient);
		}
	}

	public static void main(String[] args) {
		int port = 8000;
		
		try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server started on port " + port);
			
			while(true) {
				Socket clientSocket = serverSocket.accept();
				clients.add(clientSocket);
				TCPServer serverInstance = new TCPServer(clientSocket);
				serverInstance.start();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
