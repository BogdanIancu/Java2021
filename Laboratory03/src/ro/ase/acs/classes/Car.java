package ro.ase.acs.classes;

import ro.ase.acs.interfaces.Taxable;

public final class Car extends Vehicle implements Taxable {
	private static final int MAX_TAX = 5000;
	//private final String something = "abcd";
	
	private String model;
	private int capacity;
	
	public Car() {
		//super();
		this.model = "";
		this.capacity = 0;
	}
	
	public Car(String producer, String model, 
			float speed, int capacity) {
		super(producer, speed);
		this.model = model;
		this.capacity = capacity;
	}
	
	@Override
	public float computeTax() {
		float tax = 0;
		if(capacity < 2000) {
			tax = capacity * 0.05f;
		}
		else {
			tax = capacity * 0.1f;
		}
		return tax > MAX_TAX ? MAX_TAX : tax;
	}

	@Override
	public final void start() {
		System.out.println("The car has started and runs with " + 
				speed + " km/h");
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Car copy = (Car)super.clone();
		copy.model = model;
		copy.capacity = capacity;
		return copy;
	}
}
