package designPatterns.AbstractFactory;

public class ConcreteCreatorB implements Creator {

	public ProductI createProductI() {
		return new ProductIB();
	}

	public ProductII createProductII() {
		return new ProductIIB();
	}

}
