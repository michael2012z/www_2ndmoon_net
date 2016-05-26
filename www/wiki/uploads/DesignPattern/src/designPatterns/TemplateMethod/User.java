package designPatterns.TemplateMethod;

public class User {
	public static void main(String[] args) {
		AbstractTemplate a = new ConcreteClassA();
		AbstractTemplate b = new ConcreteClassB();
		a.templateMethod();
		b.templateMethod();
	}
}
