package designPatterns.Decorator;

public class ConcreteDecoratorA extends Decorator {
	
	private Decorator decorator;
	
	public ConcreteDecoratorA() {
		this.decorator = null;
	}
	
	public ConcreteDecoratorA(Decorator decorator) {
		this.decorator = decorator;
	}
	
	public void sampleOperation() {
		System.out.println("ConcreteDecoratorA.sampleOperation()");
		if (null != decorator)
			decorator.sampleOperation();
	}
}
