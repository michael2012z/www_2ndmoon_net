// Source code from file:  ConcreteObservable.java 

package designPatterns.Observer;

import java.util.Enumeration;
import java.util.Vector;

public class ConcreteObservable implements Observable {

	private Vector obs = new Vector();
	
	public void addObserver(Observer o) {
		obs.add(o);
	}

	public void deleteObserver(Observer o) {
		obs.remove(o);
	}

	public void notifyObservers() {
		Enumeration enumeration = ((Vector)(obs.clone())).elements();
		while(enumeration.hasMoreElements()) {
			Observer oo = (Observer)(enumeration.nextElement());
			oo.update();
//			((Observer)(enumeration.nextElement())).update();
		}
	}
	
	public void demo() {
		int count = 0;
		while (count++ < 3) {
			try {
				Thread.sleep(1000);
				notifyObservers();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}


// Source code from file:  ConcreteObserverA.java 

package designPatterns.Observer;

public class ConcreteObserverA implements Observer {
	public void update() {
		System.out.println("ConcreteObserverA got updated.");
	}
}


// Source code from file:  ConcreteObserverB.java 

package designPatterns.Observer;

public class ConcreteObserverB implements Observer {
	public void update() {
		System.out.println("ConcreteObserverB got updated.");
	}
}


// Source code from file:  Observable.java 

package designPatterns.Observer;

public interface Observable {
	public void addObserver(Observer o);
	public void deleteObserver(Observer o);
	public void notifyObservers();
}


// Source code from file:  Observer.java 

package designPatterns.Observer;

public interface Observer {
	public void update();
}


// Source code from file:  User.java 

package designPatterns.Observer;

public class User {
	public static void main(String[] args) {
		ConcreteObservable observable = new ConcreteObservable();
		observable.addObserver(new ConcreteObserverA());
		observable.addObserver(new ConcreteObserverB());
		observable.demo();
	}
}


