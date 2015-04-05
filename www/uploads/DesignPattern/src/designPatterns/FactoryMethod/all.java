// Source code from file:  ConcreteCreatorA.java 

package designPatterns.FactoryMethod;

public class ConcreteCreatorA implements Creator {
	public Product factory() {
		return new ConcreteProductA();
	}
}


// Source code from file:  ConcreteCreatorB.java 

package designPatterns.FactoryMethod;

public class ConcreteCreatorB implements Creator {
	public Product factory() {
		return new ConcreteProductB();
	}
}


// Source code from file:  ConcreteProductA.java 

package designPatterns.FactoryMethod;

public class ConcreteProductA implements Product {

}


// Source code from file:  ConcreteProductB.java 

package designPatterns.FactoryMethod;

public class ConcreteProductB implements Product {

}


// Source code from file:  Creator.java 

package designPatterns.FactoryMethod;

public interface Creator {
	public Product factory();
}


// Source code from file:  Product.java 

package designPatterns.FactoryMethod;

public interface Product {

}


// Source code from file:  User.java 

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


