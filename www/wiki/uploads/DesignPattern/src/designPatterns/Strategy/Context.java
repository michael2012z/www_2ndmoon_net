package designPatterns.Strategy;

public class Context {
	private Strategy strategy;
	
	public void strategy(char type) {
		if ('A' == type)
			strategy = new ConcreteStrategyA();
		else if ('B' == type)
			strategy = new ConcreteStrategyB();
		strategy.strategy();
	}
}
