package designPatterns.Memento;

public class Caretaker {
	
	public void saveMemento(Memento m) {
		MementoNarrowIF mn = (MementoNarrowIF)m;
		mn.serialize();
	}
	
	public Memento loadMemento() {
		// This is just for demo.
		// Caretaker don't know ConcreteMemento. It just get a Memento from other place.
		return new ConcreteMemento();
	}
	
}
