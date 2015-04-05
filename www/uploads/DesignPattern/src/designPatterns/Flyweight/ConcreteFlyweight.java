package designPatterns.Flyweight;

public class ConcreteFlyweight implements Flyweight {
	
	private Character internalState = null;
	
	public ConcreteFlyweight(Character state) {
		this.internalState = state;
	}

	public void operation(String state) {
		System.out.println("internal state = " + internalState + ", external state = " + state);
	}

}
