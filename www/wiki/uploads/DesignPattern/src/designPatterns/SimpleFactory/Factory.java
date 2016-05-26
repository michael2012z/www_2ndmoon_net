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
