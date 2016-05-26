package designPatterns.Visitor;

public class User {
	public static void main(String[] args) {
		ObjectStructure obs = new ObjectStructure();
		obs.addElement(new ElementA());
		obs.addElement(new ElementB());
		obs.addElement(new ElementC());
		Visitor visitorA = new VisitorA();
		Visitor visitorB = new VisitorB();
		obs.action(visitorA);
		obs.action(visitorB);
	}
}
