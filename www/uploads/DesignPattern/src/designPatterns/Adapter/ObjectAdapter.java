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
