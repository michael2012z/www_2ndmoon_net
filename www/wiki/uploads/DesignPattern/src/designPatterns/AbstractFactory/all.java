// Source code from file:  ConcreteCreatorA.java 

package designPatterns.AbstractFactory;

public class ConcreteCreatorA implements Creator {

	public ProductI createProductI() {
		return new ProductIA();
	}

	public ProductII createProductII() {
		return new ProductIIA();
	}

}


// Source code from file:  ConcreteCreatorB.java 

package designPatterns.AbstractFactory;

public class ConcreteCreatorB implements Creator {

	public ProductI createProductI() {
		return new ProductIB();
	}

	public ProductII createProductII() {
		return new ProductIIB();
	}

}


// Source code from file:  Creator.java 

package designPatterns.AbstractFactory;

public interface Creator {
	public ProductI createProductI();
	public ProductII createProductII();
}


// Source code from file:  ProductI.java 

package designPatterns.AbstractFactory;

public interface ProductI {

}


// Source code from file:  ProductIA.java 

package designPatterns.AbstractFactory;

public class ProductIA implements ProductI {

}


// Source code from file:  ProductIB.java 

package designPatterns.AbstractFactory;

public class ProductIB implements ProductI {

}


// Source code from file:  ProductII.java 

package designPatterns.AbstractFactory;

public interface ProductII {

}


// Source code from file:  ProductIIA.java 

package designPatterns.AbstractFactory;

public class ProductIIA implements ProductII {

}


// Source code from file:  ProductIIB.java 

package designPatterns.AbstractFactory;

public class ProductIIB implements ProductII {

}


// Source code from file:  User.java 

package designPatterns.AbstractFactory;

public class User {
	public static void main(String[] args) {
		Creator creator = null;
		// now create product series A
		creator = new ConcreteCreatorA();
		creator.createProductI();
		creator.createProductII();
		// now create product series B
		creator = new ConcreteCreatorB();
		creator.createProductI();
		creator.createProductII();
	}
}


