package designPatterns.DefaultAdapter;

public class User extends Adapter {
	public void operation2() {
		System.out.println("User.operation2()");
	}
	
	public static void main(String [] args) {
		Target target = new User();
		target.operation1();
		target.operation2();
		target.operation3();
	}
}
