// Source code from file:  Builder.java 

package designPatterns.Builder;

public interface Builder {
	public void buildPart1();
	public void buildPart2();
}


// Source code from file:  ConcreteBuilder.java 

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


// Source code from file:  Director.java 

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


// Source code from file:  Product.java 

package designPatterns.Builder;

public class Product {

}


