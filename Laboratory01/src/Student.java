
public class Student {
	public String name;
	private float grade;
	
	public Student() {
		name = "Anonymous";
		grade = 1;
	}
	
	public Student(String _name, float grade) {
		name = _name;
		this.grade = grade;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}
}
