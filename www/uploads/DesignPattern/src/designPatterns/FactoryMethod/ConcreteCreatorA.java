package designPatterns.FactoryMethod;

public class ConcreteCreatorA implements Creator {
	public Product factory() {
		return new ConcreteProductA();
	}
}
