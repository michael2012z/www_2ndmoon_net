package designPatterns.Bridge;

public class RefinedAbstraction extends Abstraction {
	
	public RefinedAbstraction(String systemType) {
		if (systemType.equals("SystemA"))
			imp = new ConcreteImplementorA();
		else if (systemType.equals("SystemB"))
			imp = new ConcreteImplementorB();
	}
	
	public void operation() {
		// do some thing
		super.operation();
	}
}
