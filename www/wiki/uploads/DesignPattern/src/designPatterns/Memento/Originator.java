package designPatterns.Memento;

public class Originator {

	private int state = 0;
	
	public Memento createMemento() {
		ConcreteMemento m = new ConcreteMemento();
		m.setState(state);
		System.out.println("create memento with state " + state);
		return m;
	}
	
	public void restoreMemento(Memento m) {
		MementoWideIF mw = (ConcreteMemento)m;
		state = mw.getState();
		System.out.println("restore memento with state " + state);
	}
}
