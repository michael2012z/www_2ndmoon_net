// Source code from file:  Component.java 

package designPatterns.Decorator;

public interface Component {
	public void sampleOperation();
}


// Source code from file:  ConcreteComponent.java 

package designPatterns.Decorator;

public class ConcreteComponent implements Component {

	public void sampleOperation() {
		System.out.println("ConcreteComponent.sampleOperation()");
	}

}


// Source code from file:  ConcreteDecoratorA.java 

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


// Source code from file:  ConcreteDecoratorB.java 

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


// Source code from file:  Decorator.java 

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


// Source code from file:  User.java 

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


