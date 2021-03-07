package ro.ase.acs.main;

import ro.ase.acs.classes.BookStore;

public class Main {

	public static void main(String[] args) {
		BookStore b1 = new BookStore();
		BookStore b2 = new BookStore("Carturesti", 7);
		
		b1 = (BookStore)b2.clone();
		b1.setName("Elefant");
		System.out.println(b2.getName());
		
		float[] values = new float[] {50.5f, 20.8f, 75};
		b2.setPrices(values);
		values[0] = 80;
		
		float[] reference = b2.getPrices();
		reference[0] = 0;
		
		String s = "something";
		s = new String("something else");
		System.out.println(s);
	}

}
