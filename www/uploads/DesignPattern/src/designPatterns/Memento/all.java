// Source code from file:  Caretaker.java 

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


// Source code from file:  ConcreteMemento.java 

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


// Source code from file:  Memento.java 

package designPatterns.Memento;

public interface Memento {

}


// Source code from file:  MementoNarrowIF.java 

package designPatterns.Memento;

public interface MementoNarrowIF extends Memento {
	public void serialize();
}


// Source code from file:  MementoWideIF.java 

package designPatterns.Memento;

public interface MementoWideIF extends Memento {
	public void setState(int state);
	public int getState();
}


// Source code from file:  Originator.java 

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


// Source code from file:  User.java 

package designPatterns.Memento;

public class User {
	public static void main(String[] args) {
		Originator o = new Originator();
		Caretaker c = new Caretaker();
		// save memento
		Memento m = o.createMemento();
		c.saveMemento(m);
		// load memento
		m = c.loadMemento();
		o.restoreMemento(m);
	}
}


