// Source code from file:  Adaptee.java 

package designPatterns.Adapter;

public class Adaptee {
	
	public void operation1() {
		System.out.println("operation1() in Adaptee");
	}
}


// Source code from file:  ClassAdapter.java 

package designPatterns.Adapter;

public class ClassAdapter extends Adaptee implements Target {
	
	public void operation2() {
		System.out.println("operation2() in ClassAdapter");
	}
}


// Source code from file:  ObjectAdapter.java 

package designPatterns.Adapter;

public class ObjectAdapter implements Target {

	private Adaptee adaptee = null;

	public ObjectAdapter() {
		adaptee = new Adaptee();
	}

	public void operation1() {
		System.out.println("operation1() in ClassAdapter");
		adaptee.operation1();
	}

	public void operation2() {
		System.out.println("operation2() in ClassAdapter");
	}
	
}


// Source code from file:  Target.java 

package designPatterns.Adapter;

public interface Target {
	public void operation1();
	public void operation2();
}


// Source code from file:  User.java 

package designPatterns.Adapter;

public class User {
	public static void main(String [] args) {
		Target t = null;
		// test for Class Adapter
		t = new ClassAdapter();
		t.operation1();
		t.operation2();
		// test for Object Adapter
		t = new ObjectAdapter();
		t.operation1();
		t.operation2();
	}
}


