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
