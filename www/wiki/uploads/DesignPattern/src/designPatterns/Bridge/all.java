// Source code from file:  Abstraction.java 

package designPatterns.Bridge;

public class Abstraction {
	protected Implementor imp;
	
	public void operation() {
		imp.operationImp();
	}
}


// Source code from file:  ConcreteImplementorA.java 

package designPatterns.Bridge;

public class ConcreteImplementorA implements Implementor {
	public void operationImp() {
		System.out.println("ConcreteImplementorA.operationImp()");
	}
}


// Source code from file:  ConcreteImplementorB.java 

package designPatterns.Bridge;

public class ConcreteImplementorB implements Implementor {
	public void operationImp() {
		System.out.println("ConcreteImplementorB.operationImp()");
	}
}


// Source code from file:  Implementor.java 

package designPatterns.Bridge;

public interface Implementor {
	public void operationImp();
}


// Source code from file:  RefinedAbstraction.java 

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


// Source code from file:  User.java 

package designPatterns.Bridge;

public class User {
	public static void main(String[] args) {
		Abstraction absA = new RefinedAbstraction("SystemA");
		absA.operation();
		Abstraction absB = new RefinedAbstraction("SystemB");
		absB.operation();
	}
}


