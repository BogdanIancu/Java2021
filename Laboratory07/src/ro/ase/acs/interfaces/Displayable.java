package ro.ase.acs.interfaces;

@FunctionalInterface
public interface Displayable {
	public void display();
	
	default public void d(String message) {
		System.out.println(message);
	}
}
