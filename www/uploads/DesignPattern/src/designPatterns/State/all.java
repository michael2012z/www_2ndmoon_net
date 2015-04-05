// Source code from file:  ConcreteStateA.java 

package designPatterns.State;

public class ConcreteStateA implements State {
	public void operation() {
		System.out.println("ConcreteStateA.operation()");
	}
}


// Source code from file:  ConcreteStateB.java 

package designPatterns.State;

public class ConcreteStateB implements State {
	public void operation() {
		System.out.println("ConcreteStateB.operation()");
	}
}


// Source code from file:  Context.java 

package designPatterns.State;

public class Context {
	private State state = null;
	private State stateA = null;
	private State stateB = null;
	
	public Context() {
		stateA = new ConcreteStateA();
		stateB = new ConcreteStateB();
		state = stateA;
	}
	
	public void switchState() {
		if (state == stateA)
			state = stateB;
		else
			state = stateA;
	}
	
	public void operation() {
		state.operation();
	}
	
}


// Source code from file:  State.java 

package designPatterns.State;

public interface State {
	public void operation();
}


// Source code from file:  User.java 

package designPatterns.State;

public class User {
	public static void main(String[] args) {
		Context c = new Context();
		c.operation();
		c.switchState();
		c.operation();
		c.switchState();
		c.operation();
	}
}


