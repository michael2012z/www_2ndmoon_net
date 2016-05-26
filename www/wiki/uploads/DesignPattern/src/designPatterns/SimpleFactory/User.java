package designPatterns.SimpleFactory;

public class User {
	public static void main(String[] args) {
		Product p = Factory.factory("ConcreteProduct1");
		System.out.println(p);
		p = Factory.factory("ConcreteProduct2");
		System.out.println(p);
	}
}
