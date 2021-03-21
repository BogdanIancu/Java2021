package ro.ase.acs.classes;

public class Car implements Cloneable, Comparable<Car> {
	private String producer;
	private String model;
	private float speed;
	private int capacity;
	
	public Car() {
		producer = "";
		model = "";
		speed = 0;
		capacity = 0;
	}
	
	public Car(String producer, String model,
			float speed, int capacity) {
		this.producer = producer;
		this.model = model;
		this.speed = speed;
		this.capacity = capacity;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Car copy = (Car)super.clone();
		copy.producer = producer;
		copy.model = model;
		copy.speed = speed;
		copy.capacity = capacity;
		return copy;
		
	}

	@Override
	public int compareTo(Car o) {
		if(this.capacity < o.capacity) {
			return -1;
		} else if(this.capacity == o.capacity) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [producer=");
		builder.append(producer);
		builder.append(", model=");
		builder.append(model);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", capacity=");
		builder.append(capacity);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((producer == null) ? 0 : producer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		return true;
	}
	
	
	
//	@Override
//	public String toString() {
//		String s = "Producer: " + producer + " ";
//		s += "Model: " + model + " ";
//		s += "Speed: " + speed + " ";
//		s += "Capacity: " + capacity;
//		return s;
//	}
	
}
