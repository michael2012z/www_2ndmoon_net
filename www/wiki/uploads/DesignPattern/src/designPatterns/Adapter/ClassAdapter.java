package designPatterns.Adapter;

public class ClassAdapter extends Adaptee implements Target {
	
	public void operation2() {
		System.out.println("operation2() in ClassAdapter");
	}
}
