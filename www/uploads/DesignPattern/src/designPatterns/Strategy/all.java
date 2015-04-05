// Source code from file:  ConcreteStrategyA.java 

package designPatterns.Strategy;

public class ConcreteStrategyA implements Strategy {
	public void strategy() {
		System.out.println("ConcreteStrategyA.strategy()");
	}
}


// Source code from file:  ConcreteStrategyB.java 

package designPatterns.Strategy;

public class ConcreteStrategyB implements Strategy {
	public void strategy() {
		System.out.println("ConcreteStrategyA.strategy()");
	}
}


// Source code from file:  Context.java 

package designPatterns.Strategy;

public class Context {
	private Strategy strategy;
	
	public void strategy(char type) {
		if ('A' == type)
			strategy = new ConcreteStrategyA();
		else if ('B' == type)
			strategy = new ConcreteStrategyB();
		strategy.strategy();
	}
}


// Source code from file:  Strategy.java 

package designPatterns.Strategy;

public interface Strategy {
	public void strategy();
}


// Source code from file:  User.java 

package designPatterns.Strategy;

public class User {
	public static void main(String[] args) {
		Context ctxt = new Context();
		ctxt.strategy('A');
		ctxt.strategy('B');
	}
}


