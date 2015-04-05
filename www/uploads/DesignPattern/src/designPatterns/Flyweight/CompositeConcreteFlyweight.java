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
