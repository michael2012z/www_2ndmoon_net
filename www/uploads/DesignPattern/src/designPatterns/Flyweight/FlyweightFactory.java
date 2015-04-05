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
