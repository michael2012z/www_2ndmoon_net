package designPatterns.Observer;

public class User {
	public static void main(String[] args) {
		ConcreteObservable observable = new ConcreteObservable();
		observable.addObserver(new ConcreteObserverA());
		observable.addObserver(new ConcreteObserverB());
		observable.demo();
	}
}
