package ro.ase.acs.classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 2L;
	private String name;
	private float price;
	private int quantity;
	
	public Product() {
		name = "";
		price = 0;
		quantity = 0;
	}

	public Product(String name, float price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}
	
	public void serialize() throws IOException {
		FileOutputStream file = new FileOutputStream("product.dat");
		ObjectOutputStream oos = new ObjectOutputStream(file);
		oos.writeObject(this);
		oos.close();
	}
	
	public void deserialize() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("product.dat");
		ObjectInputStream ois = new ObjectInputStream(file);
		Product p = (Product)ois.readObject();
		this.name = p.name;
		this.price = p.price;
		this.quantity = p.quantity;
		ois.close();
	}
}
