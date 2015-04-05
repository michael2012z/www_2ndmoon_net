package designPatterns.Builder;

public class ConcreteBuilder implements Builder {
	
	public void buildPart1() {
		// do something
	}

	public void buildPart2() {
		// do something
	}

	public Product getProduct() {
		Product p = new Product();
		buildPart1();
		buildPart2();
		return p;
	}
}
