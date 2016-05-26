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
