
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello, World!");
		
		Student s1 = new Student();
		System.out.println(s1.name);
		
		Student s2 = new Student("John", 9);
		System.out.println(s2.getGrade());
	}

}
