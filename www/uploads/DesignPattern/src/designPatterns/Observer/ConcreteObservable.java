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
