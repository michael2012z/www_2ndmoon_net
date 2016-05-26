// Source code from file:  ConcreteProduct1.java 

package designPatterns.SimpleFactory;

public class ConcreteProduct1 implements Product {
	public String toString() {
		return new String("ConcreteProduct1");
	}
}


// Source code from file:  ConcreteProduct2.java 

package designPatterns.SimpleFactory;

public class ConcreteProduct2 implements Product {
	public String toString() {
		return new String("ConcreteProduct2");
	}
}


// Source code from file:  Factory.java 

package designPatterns.SimpleFactory;

public class Factory {
	
	public static Product factory(String s) {
		Product product = null;
		if (s.equals("ConcreteProduct1")) {
			product = new ConcreteProduct1();
		} else if (s.equals("ConcreteProduct2")) {
			product = new ConcreteProduct2();
		}
		return product;
	}
	
}


// Source code from file:  Product.java 

package designPatterns.SimpleFactory;

public interface Product {

}


// Source code from file:  User.java 

package designPatterns.SimpleFactory;

public class User {
	public static void main(String[] args) {
		Product p = Factory.factory("ConcreteProduct1");
		System.out.println(p);
		p = Factory.factory("ConcreteProduct2");
		System.out.println(p);
	}
}


