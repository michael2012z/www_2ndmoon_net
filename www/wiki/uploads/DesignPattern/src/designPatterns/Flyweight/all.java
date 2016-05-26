// Source code from file:  CompositeConcreteFlyweight.java 

package designPatterns.Flyweight;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CompositeConcreteFlyweight implements Flyweight {

	private HashMap maps = new HashMap();
	
	public void add(Character key, Flyweight fly) {
		maps.put(key, fly);
	}
	
	public void operation(String state) {
		Flyweight fly = null;
		for (Iterator it = maps.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry e = (Map.Entry)it.next();
			fly = (Flyweight)e.getValue();
			fly.operation(state);
		}
	}

}


// Source code from file:  ConcreteFlyweight.java 

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


// Source code from file:  Flyweight.java 

package designPatterns.Flyweight;

public interface Flyweight {
	public void operation(String state);
}


// Source code from file:  FlyweightFactory.java 

package designPatterns.Flyweight;

import java.util.HashMap;

public class FlyweightFactory {

	private HashMap maps = new HashMap();

	public Flyweight factory(Character state) {
		if (maps.containsKey(state)) {
			return (Flyweight)maps.get(state);
		} else {
			Flyweight flyweight = new ConcreteFlyweight(state);
			maps.put(state, flyweight);
			return flyweight;
		}
	}
	
	public Flyweight factory(String compositeState) {
		CompositeConcreteFlyweight compositeFly = new CompositeConcreteFlyweight();
		int length = compositeState.length();
		Character state = null;
		for(int i = 0; i < length; i++) {
			state = new Character(compositeState.charAt(i));
			compositeFly.add(state, this.factory(state));
		}
		return compositeFly;
	}
}


