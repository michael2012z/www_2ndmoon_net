package designPatterns.Decorator;

public class User {
	public static void main(String[] args) {
		Component c = new ConcreteComponent();
		Decorator decorator = new Decorator(c);
		Decorator a = new ConcreteDecoratorA(decorator);
		Decorator b = new ConcreteDecoratorB(a);
		b.sampleOperation();
		
		System.out.println();
		
		// re-org
		b = new ConcreteDecoratorB(decorator);
		a = new ConcreteDecoratorA(b);
		a.sampleOperation();
	}
}
