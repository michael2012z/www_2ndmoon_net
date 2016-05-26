package designPatterns.AbstractFactory;

public class ConcreteCreatorA implements Creator {

	public ProductI createProductI() {
		return new ProductIA();
	}

	public ProductII createProductII() {
		return new ProductIIA();
	}

}
