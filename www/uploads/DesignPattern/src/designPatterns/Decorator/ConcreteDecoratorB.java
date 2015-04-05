package designPatterns.Decorator;

public class ConcreteDecoratorB extends Decorator {
	
	private Decorator decorator;
	
	public ConcreteDecoratorB() {
		this.decorator = null;
	}
	
	public ConcreteDecoratorB(Decorator decorator) {
		this.decorator = decorator;
	}
	
	public void sampleOperation() {
		System.out.println("ConcreteDecoratorB.sampleOperation()");
		if (null != decorator)
			decorator.sampleOperation();
	}
}
