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
