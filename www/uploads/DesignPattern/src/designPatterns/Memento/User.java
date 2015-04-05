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
