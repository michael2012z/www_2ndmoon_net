package designPatterns.Strategy;

public class User {
	public static void main(String[] args) {
		Context ctxt = new Context();
		ctxt.strategy('A');
		ctxt.strategy('B');
	}
}
