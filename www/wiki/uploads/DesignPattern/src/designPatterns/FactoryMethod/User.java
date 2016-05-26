package designPatterns.FactoryMethod;

public class User {
	public static void main(String[] args) {
		Creator creatorA = new ConcreteCreatorA();
		Creator creatorB = new ConcreteCreatorB();
		Product product = creatorA.factory();
		System.out.println(product);
		product = creatorB.factory();
		System.out.println(product);
	}
}
