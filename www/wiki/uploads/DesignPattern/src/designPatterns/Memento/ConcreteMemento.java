package designPatterns.Memento;

public class ConcreteMemento implements MementoWideIF, MementoNarrowIF {

	private int state = 0;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void serialize() {
		System.out.println("Memento was serialized.");
	}

}
