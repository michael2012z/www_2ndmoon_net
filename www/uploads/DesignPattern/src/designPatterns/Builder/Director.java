package designPatterns.Builder;

public class Director {

	public Product buildProduct() {
		ConcreteBuilder builder = new ConcreteBuilder();
		return builder.getProduct();
	}
	
	public static void main(String[] args) {
		Director director = new Director();
		director.buildProduct();
	}

}
