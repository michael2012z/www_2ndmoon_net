package designPatterns.Decorator;

public class Decorator implements Component {

	private Component component;
	
	public Decorator() {
		// make a default component;
		component = new ConcreteComponent();
	}
	
	public Decorator(Component component) {
		this.component = component;
	}
	
	public void sampleOperation() {
		System.out.println("Decorator.sampleOperation()");
		component.sampleOperation();
	}

}
