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
