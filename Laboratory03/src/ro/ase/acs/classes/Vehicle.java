package ro.ase.acs.classes;

public abstract class Vehicle implements Cloneable {
	private String producer;
	protected float speed;
	
	public Vehicle() {
		this.producer = "";
		this.speed = 0;
	}
	
	public Vehicle(String producer, float speed) {
		this();
		if(producer != null) {
			this.producer = producer;
		}
		this.speed = speed;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Vehicle copy = (Vehicle)super.clone();
		copy.producer = producer;
		copy.speed = speed;
		return copy;
	}

	public abstract void start();
}
