// Source code from file:  AbstractTemplate.java 

package designPatterns.TemplateMethod;

public abstract class AbstractTemplate {
	// abstract method
	protected abstract void abstractMethod();
	
	// hook method, provide default implementation
	protected void hookMethod() {
		System.out.println("AbstractTemplate.hookMethod()");
	}
	
	// template method
	public void templateMethod() {
		abstractMethod();
		hookMethod();
	}
}


// Source code from file:  ConcreteClassA.java 

package designPatterns.TemplateMethod;

public class ConcreteClassA extends AbstractTemplate {

	protected void abstractMethod() {
		System.out.println("ConcreteClassA.abstractMethod()");
	}
	
	protected void hookMethod() {
		System.out.println("ConcreteClassA.hookMethod()");
	}

}


// Source code from file:  ConcreteClassB.java 

package designPatterns.TemplateMethod;

public class ConcreteClassB extends AbstractTemplate {

	protected void abstractMethod() {
		System.out.println("ConcreteClassB.abstractMethod()");
	}

}


// Source code from file:  User.java 

package designPatterns.TemplateMethod;

public class User {
	public static void main(String[] args) {
		AbstractTemplate a = new ConcreteClassA();
		AbstractTemplate b = new ConcreteClassB();
		a.templateMethod();
		b.templateMethod();
	}
}


