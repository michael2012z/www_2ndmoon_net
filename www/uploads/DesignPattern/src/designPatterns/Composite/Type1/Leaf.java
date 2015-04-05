package designPatterns.Composite.Type1;

public class Leaf implements Component {

	public void sampleOperation() {
		System.out.println("Leaf.sampleOperation()");
	}
	
	public Composite getComposite() {
		return null;
	}
}
