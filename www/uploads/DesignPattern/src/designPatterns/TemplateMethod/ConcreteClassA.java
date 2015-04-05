package designPatterns.TemplateMethod;

public class ConcreteClassA extends AbstractTemplate {

	protected void abstractMethod() {
		System.out.println("ConcreteClassA.abstractMethod()");
	}
	
	protected void hookMethod() {
		System.out.println("ConcreteClassA.hookMethod()");
	}

}
