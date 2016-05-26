// Source code from file:  Adapter.java 

package designPatterns.DefaultAdapter;

public class Adapter implements Target {

	public void operation1() {
	}

	public void operation2() {
	}

	public void operation3() {
	}

}


// Source code from file:  Target.java 

package designPatterns.DefaultAdapter;

public interface Target {
	public void operation1();
	public void operation2();
	public void operation3();
}


// Source code from file:  User.java 

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


