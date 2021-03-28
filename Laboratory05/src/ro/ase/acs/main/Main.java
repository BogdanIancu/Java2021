package ro.ase.acs.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Scanner;

import ro.ase.acs.authentication.User;

public class Main {
	
	static void add(int x, int y) {
		x = x + y;
	}
	
	static void add(Int x, Int y) {
		int value = x.value;
		x = new Int();
		x.value = value;
		x.value = x.value + y.value;
	}
	
	static void add(Integer x, Integer y) {
		x = x + y;
	}
	
	public static void main(String[] args) {
		int a = 5;
		int b = 3;
		add(a, b);
		
		System.out.println(String.format("a = %d b = %d", a, b));
		
		Int c = new Int();
		c.value = 5;
		
		Int d = new Int();
		d.value = 3;
		
		add(c, d);
		
		System.out.println(String.format(
				"c = %d d = %d", c.value, d.value));
		
		Integer r = 5;
		Integer t = 3;
		add(r, t);
		System.out.println(String.format("r = %d t = %d", r, t));
		
		User user = new User();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Username: ");
		user.setUsername(scanner.nextLine());
		System.out.print("Password: ");
		user.setPassword(scanner.nextLine());
		try {
			user.login();
		} catch (InvalidAlgorithmParameterException e1) {
			e1.printStackTrace();
		}
		
		scanner.close();
		
		b = 0;
		try {
			int z = a / b;
			System.out.println(z);
		} catch(ArithmeticException e) {
			System.out.println(e.getMessage());
		}
		
		FileInputStream file = null;
		try {
			file = new FileInputStream("test.txt");
			file.read();
			file.close();
			//...
		} catch (FileNotFoundException | ArithmeticException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Some message");
	}
}
