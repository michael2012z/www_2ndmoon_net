package designPatterns.FactoryMethod;

public class ConcreteCreatorB implements Creator {
	public Product factory() {
		return new ConcreteProductB();
	}
}
