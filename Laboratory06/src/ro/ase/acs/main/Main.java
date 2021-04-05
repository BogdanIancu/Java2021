package ro.ase.acs.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

import ro.ase.acs.classes.Product;

public class Main {

	public static void main(String[] args) {
		Product product = new Product("Water", 3.5f, 5);
		
		try {
			FileOutputStream file =
					new FileOutputStream("product.txt");
			OutputStreamWriter writer = 
					new OutputStreamWriter(file);
			BufferedWriter buffer = 
					new BufferedWriter(writer);
			buffer.write(product.getName());
			buffer.write(System.lineSeparator());
			buffer.write(String.valueOf(product.getPrice()));
			buffer.write(System.lineSeparator());
			buffer.write(String.valueOf(product.getQuantity()));
			buffer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader bufferedReader = null;
		try {
			FileInputStream file = 
					new FileInputStream("product.txt");
			InputStreamReader reader = 
					new InputStreamReader(file);
			bufferedReader = new BufferedReader(reader);
			Product p = new Product();
			p.setName(bufferedReader.readLine());
			String line = bufferedReader.readLine();
			p.setPrice(Float.parseFloat(line));
			line = bufferedReader.readLine();
			p.setQuantity(Integer.parseInt(line));
			System.out.println(p);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		File file = new File("product.txt");
		try(Scanner scanner = new Scanner(file)) {
			Product p = new Product();
			p.setName(scanner.next());
			p.setPrice(Float.parseFloat(scanner.next()));
			p.setQuantity(scanner.nextInt());
			System.out.println(p);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try(FileOutputStream fos = new FileOutputStream("product.bin");
				DataOutputStream dataOs = new DataOutputStream(fos)) {
			dataOs.writeUTF(product.getName());
			dataOs.writeFloat(product.getPrice());
			dataOs.writeInt(product.getQuantity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(FileInputStream fis = new FileInputStream("product.bin");
				DataInputStream dataIs = new DataInputStream(fis)) {
			Product p = new Product();
			p.setName(dataIs.readUTF());
			p.setPrice(dataIs.readFloat());
			p.setQuantity(dataIs.readInt());
			System.out.println(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			product.serialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Product p = new Product();
		try {
			p.deserialize();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(p);
		
		try (RandomAccessFile raf = 
				new RandomAccessFile("product.bin", "r")){
			raf.seek(raf.length() - Integer.BYTES);
			int value = raf.readInt();
			System.out.println(value);
			System.out.println(raf.getFilePointer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
